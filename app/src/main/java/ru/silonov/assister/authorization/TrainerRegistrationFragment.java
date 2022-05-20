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
import ru.silonov.assister.model.login.Trainer;
import ru.silonov.assister.model.registration.RegistrationTrainerRequest;
import ru.silonov.assister.trainer.TrainerMainActivity;


public class TrainerRegistrationFragment extends Fragment {

    View view;

    TextInputEditText experience;

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
        view =  inflater.inflate(R.layout.fragment_trainer_registration, container, false);

        experience = view.findViewById(R.id.edUserExperience);
        btnSignUp = view.findViewById(R.id.btnSignUp2);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });


        return view;
    }

    public void register(){
        RegistrationTrainerRequest trainerRequest = new RegistrationTrainerRequest();
        trainerRequest.setName(sharedPreferences.getString("name", ""));
        trainerRequest.setSurname(sharedPreferences.getString("surname", null));
        trainerRequest.setLogin(sharedPreferences.getString("login", null));
        trainerRequest.setPassword(sharedPreferences.getString("password", null));
        trainerRequest.setExperience(Integer.parseInt(experience.getText().toString()));

        Call<Trainer> TrainerResponseCall = ApiClient.getUserService().trainerRegister(trainerRequest);
        TrainerResponseCall.enqueue(new Callback<Trainer>() {
            @Override
            public void onResponse(Call<Trainer> call, Response<Trainer> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(), "Registered", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getActivity(), TrainerMainActivity.class);
                    String transfer = response.body().toString();
                    System.out.println(transfer);
                    intent.putExtra("transfer", response.body());
                    startActivity(intent);
                }else {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Trainer> call, Throwable t) {
                Toast.makeText(getContext(), "Throwable"+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }


    public void setCallbackFragment(CallbackEnterFragment callbackEnterFragment){
        this.callbackEnterFragment = callbackEnterFragment;
    }
}