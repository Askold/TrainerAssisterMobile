package ru.silonov.assister.trainer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import ru.silonov.assister.ApiClient;
import ru.silonov.assister.R;
import ru.silonov.assister.model.login.Client;
import ru.silonov.assister.model.login.Trainer;
import ru.silonov.assister.model.request.AddClientRequest;
import ru.silonov.assister.response.DefaultResponse;

public class MyClientsActivity extends AppCompatActivity implements ClientsAdapter.OnClientListener {

    private RecyclerView recyclerView;
    private ClientsAdapter adapter;
    private List<Client> clientList;
    MaterialButton btnAddClient;
    int id;
    RelativeLayout root;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_clients);

        id = (int) getIntent().getSerializableExtra("id");

        root = findViewById(R.id.relativeClients);
        btnAddClient = findViewById(R.id.btnAddClient);
        recyclerView = findViewById(R.id.recyclerMyClients);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<List<Client>> call = ApiClient.getUserService().getTrainerClients(id);
        call.enqueue(new Callback<List<Client>>() {
            @Override
            public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                clientList = response.body();
                System.out.println(clientList);
                adapter = new ClientsAdapter(MyClientsActivity.this, clientList, MyClientsActivity.this::onClientClick);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Client>> call, Throwable t) {
                Toast.makeText(MyClientsActivity.this, "Throwable"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        btnAddClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddClientWindow();
            }
        });


    }

    public void showAddClientWindow(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Поиск пользователя");
        dialog.setMessage("Введите логин пользователя");
        LayoutInflater inflater = LayoutInflater.from(this);
        View addClientWindow = inflater.inflate(R.layout.window_add_client, null);
        dialog.setView(addClientWindow);

        TextInputEditText edLogin = addClientWindow.findViewById(R.id.edAddClientLogin);

        dialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Найти", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (TextUtils.isEmpty(edLogin.getText().toString())){
                    Snackbar.make(root, "Введите логин пользователя", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                AddClientRequest addClientRequest = new AddClientRequest(id, edLogin.getText().toString());

                Call<List<Client>> addClientResponseCall = ApiClient.getUserService().addClient(addClientRequest);
                addClientResponseCall.enqueue(new Callback<List<Client>>() {
                    @Override
                    public void onResponse(Call<List<Client>> call, Response<List<Client>> response) {
                        Toast.makeText(MyClientsActivity.this, "Successfully added", Toast.LENGTH_LONG).show();
                        clientList.clear();
                        assert response.body() != null;
                        clientList.addAll(response.body());
                        System.out.println(clientList);
                        adapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Client>> call, Throwable t) {
                        Toast.makeText(MyClientsActivity.this, "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        dialog.show();
    }

    @Override
    public void onClientClick(int position) {
        Intent intent = new Intent(this, ClientPageActivity.class);
        intent.putExtra("trainerId", id);
        intent.putExtra("client", clientList.get(position));
        startActivity(intent) ;
    }
}