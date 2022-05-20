package ru.silonov.assister.client;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.silonov.assister.ApiClient;
import ru.silonov.assister.R;
import ru.silonov.assister.model.entity.Program;
import ru.silonov.assister.trainer.ClientPageActivity;

public class MyProgramsActivity extends AppCompatActivity implements ProgramAdapter.OnProgramListener {

    int clientId;

    private RecyclerView recyclerView;
    private ProgramAdapter adapter;
    private List<Program> programList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_programs);

        clientId = (int) getIntent().getSerializableExtra("clientId");

        recyclerView = findViewById(R.id.recyclerViewPrograms);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<List<Program>> programsByClient = ApiClient.getUserService().getProgramsByClient(clientId);
        programsByClient.enqueue(new Callback<List<Program>>() {
            @Override
            public void onResponse(Call<List<Program>> call, Response<List<Program>> response) {
                programList = response.body();
                adapter = new ProgramAdapter(MyProgramsActivity.this, programList,
                        MyProgramsActivity.this::onProgramClick);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Program>> call, Throwable t) {
                Toast.makeText(MyProgramsActivity.this,
                        "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    @Override
    public void onProgramClick(int position) {
        Intent intent = new Intent(this, ClientWorkoutsActivity.class);
        intent.putExtra("programId", programList.get(position).getId());
        startActivity(intent);

    }
}