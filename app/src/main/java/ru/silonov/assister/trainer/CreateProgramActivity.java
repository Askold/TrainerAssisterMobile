package ru.silonov.assister.trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.silonov.assister.ApiClient;
import ru.silonov.assister.R;
import ru.silonov.assister.model.entity.Program;
import ru.silonov.assister.model.login.Client;

public class CreateProgramActivity extends AppCompatActivity {

    TextInputEditText edTextLength, edTextWeek, edTextDescription;
    MaterialButton btnAddWorkouts;

    TextInputLayout tilTraining;
    AutoCompleteTextView actTraining;

    ArrayList<String> arrayListTraining;
    ArrayAdapter<String> arrayAdapterTraining;

    Client client;
    int trainerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_program);

        client = (Client) getIntent().getSerializableExtra("client");
        trainerId = (int) getIntent().getSerializableExtra("trainerId");

        setDropDownMenu();

        edTextLength = findViewById(R.id.edTextLength);
        edTextWeek = findViewById(R.id.edTextWeek);
        edTextDescription = findViewById(R.id.edTextDescription);

        btnAddWorkouts = findViewById(R.id.btnCreateProgram);
        btnAddWorkouts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Program program = new Program();
                program.setClient(client.getUserId());
                program.setDurationInWeeks(Integer.parseInt(edTextLength.getText().toString()));
                program.setWorkoutsPerWeek(Integer.parseInt(edTextWeek.getText().toString()));
                program.setDescription(edTextDescription.getText().toString());
                program.setType(actTraining.getText().toString());

                Call<Program> addNewProgram = ApiClient.getUserService().addNewProgram(program);
                addNewProgram.enqueue(new Callback<Program>() {
                    @Override
                    public void onResponse(Call<Program> call, Response<Program> response) {
                        Intent intent = new Intent(CreateProgramActivity.this, AddWorkoutsActivity.class);
                        intent.putExtra("trainerId", trainerId);
                        intent.putExtra("programId", response.body().getId());
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Program> call, Throwable t) {
                        Toast.makeText(CreateProgramActivity.this, "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });

//                Call<List<Client>> addClientResponseCall = ApiClient.getUserService().addClient(addClientRequest);
//                addClientResponseCall.enqueue(new Callback<List<Client>>()
            }
        });

    }

    public void setDropDownMenu(){
        tilTraining = findViewById(R.id.til_training);
        actTraining = findViewById(R.id.act_training);

        arrayListTraining = new ArrayList<>();
        arrayListTraining.add("Силовая");
        arrayListTraining.add("Кардио");
        arrayListTraining.add("Гипертрофийная");
        arrayListTraining.add("На выносливость");

        arrayAdapterTraining = new ArrayAdapter<>(this, R.layout.dropdown_role_menu, arrayListTraining);
        actTraining.setAdapter(arrayAdapterTraining);

        actTraining.setThreshold(1);


    }
}