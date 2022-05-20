package ru.silonov.assister.authorization;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.silonov.assister.ApiClient;
import ru.silonov.assister.CallbackEnterFragment;
import ru.silonov.assister.R;
import ru.silonov.assister.client.ClientMainActivity;
import ru.silonov.assister.model.login.Client;
import ru.silonov.assister.model.login.LoginRequest;
import ru.silonov.assister.model.login.Trainer;
import ru.silonov.assister.trainer.TrainerMainActivity;

public class LoginFragment extends Fragment {

    View view;

    TextInputLayout tilRole;
    AutoCompleteTextView actRole;

    TextInputEditText login, password;
    MaterialButton btnSignIn;

    ArrayList<String> arrayListRole;
    ArrayAdapter<String> arrayAdapterRole;

    CallbackEnterFragment callbackEnterFragment;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_authorization, container, false);

        setDropDownMenu();

        login = view.findViewById(R.id.edUserLogin1);
        password = view.findViewById(R.id.edUserPassword1);


        btnSignIn = view.findViewById(R.id.btnSignIn1);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(login.getText().toString()) || TextUtils.isEmpty(password.getText().toString()) ){
                    Toast.makeText(getContext(), "Username / Password required", Toast.LENGTH_LONG).show();
                }else{
                    if (actRole.getText().toString().equals("Тренер")){
                        authoriseTrainer();
                    }else{
                        authoriseClient();
                    }
                }
            }
        });


        return view;
    }

    public void setCallbackFragment(CallbackEnterFragment callbackEnterFragment){
        this.callbackEnterFragment = callbackEnterFragment;
    }

    public void setDropDownMenu(){
        tilRole = view.findViewById(R.id.til_role);
        actRole = view.findViewById(R.id.act_role);

        arrayListRole = new ArrayList<>();
        arrayListRole.add("Тренер");
        arrayListRole.add("Клиент");

        arrayAdapterRole = new ArrayAdapter<>(this.getContext(), R.layout.dropdown_role_menu, arrayListRole);
        actRole.setAdapter(arrayAdapterRole);

        actRole.setThreshold(1);
    }

    private void authoriseTrainer(){
        LoginRequest loginRequest = new LoginRequest(login.getText().toString(), password.getText().toString());

        Call<Trainer> loginResponseCall = ApiClient.getUserService().trainerLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<Trainer>() {
            @Override
            public void onResponse(Call<Trainer> call, Response<Trainer> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(), "Welcome", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getActivity(), TrainerMainActivity.class);
                    String transfer = response.body().toString();
                    System.out.println(transfer);
                    intent.putExtra("transfer", response.body());
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Trainer> call, Throwable t) {
                Toast.makeText(getContext(), "Throwable"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    private void authoriseClient(){
        LoginRequest loginRequest = new LoginRequest(login.getText().toString(), password.getText().toString());

        Call<Client> loginResponseCall = ApiClient.getUserService().clientLogin(loginRequest);
        loginResponseCall.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(), "Welcome", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getActivity(), ClientMainActivity.class);
                    String transfer = response.body().toString();
                    System.out.println(transfer);
                    intent.putExtra("client", response.body());
                    startActivity(intent);
                }else{
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Toast.makeText(getContext(), "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
