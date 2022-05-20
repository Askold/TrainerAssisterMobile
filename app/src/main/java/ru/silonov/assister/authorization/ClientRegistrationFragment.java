package ru.silonov.assister.authorization;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.silonov.assister.ApiClient;
import ru.silonov.assister.CallbackEnterFragment;
import ru.silonov.assister.R;
import ru.silonov.assister.client.ClientMainActivity;
import ru.silonov.assister.model.login.Client;
import ru.silonov.assister.model.registration.RegistrationClientRequest;


public class ClientRegistrationFragment extends Fragment {

    View view;

    TextInputEditText age, weight, height;

    MaterialButton btnSignUp;

    CallbackEnterFragment callbackEnterFragment;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    public void onAttach(@NonNull Context context) {
        sharedPreferences = context.getSharedPreferences("userFile", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        super.onAttach(context);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_client_registration, container, false);

        age = view.findViewById(R.id.edUserAge);
        weight = view.findViewById(R.id.edUserWeight);
        height = view.findViewById(R.id.edUserHeight);

        btnSignUp = view.findViewById(R.id.btnSignUp3);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                authorize();
            }
        });

        return view;
    }

    public void authorize(){
        RegistrationClientRequest clientRequest = new RegistrationClientRequest();
        clientRequest.setName(sharedPreferences.getString("name", ""));
        clientRequest.setSurname(sharedPreferences.getString("surname", null));
        clientRequest.setLogin(sharedPreferences.getString("login", null));
        clientRequest.setPassword(sharedPreferences.getString("password", null));
        clientRequest.setAge(Integer.parseInt(age.getText().toString()));
        clientRequest.setWeight(Integer.parseInt(weight.getText().toString()));
        clientRequest.setHeight(Integer.parseInt(height.getText().toString()));

        Call<Client> ClientResponseCall = ApiClient.getUserService().clientRegister(clientRequest);
        ClientResponseCall.enqueue(new Callback<Client>() {
            @Override
            public void onResponse(Call<Client> call, Response<Client> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(), "Registered", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getActivity(), ClientMainActivity.class);
                    String transfer = response.body().toString();
                    System.out.println(transfer);
                    intent.putExtra("transfer", response.body());
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Client> call, Throwable t) {
                Toast.makeText(getContext(), "Throwable"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    public void setCallbackFragment(CallbackEnterFragment callbackEnterFragment){
        this.callbackEnterFragment = callbackEnterFragment;
    }
}