package ru.silonov.assister.trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.silonov.assister.ApiClient;
import ru.silonov.assister.R;
import ru.silonov.assister.model.entity.Program;
import ru.silonov.assister.model.entity.Workout;

public class CreateWorkoutActivity extends AppCompatActivity {

    MaterialButton button;
    TextInputEditText editText;

    int programId;
    int trainerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_workout);



        programId = (int) getIntent().getSerializableExtra("programId");
        trainerId = (int) getIntent().getSerializableExtra("trainerId");

        editText = findViewById(R.id.edWorkoutName);
        button = findViewById(R.id.btnAddExercise);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Workout workout = new Workout();
                workout.setName(editText.getText().toString());
                workout.setProgram(programId);

                Call<Workout> addNewWorkout = ApiClient.getUserService().addNewWorkout(workout);
                addNewWorkout.enqueue(new Callback<Workout>() {
                    @Override
                    public void onResponse(Call<Workout> call, Response<Workout> response) {
                        Intent intent = new Intent(CreateWorkoutActivity.this,
                                SelectExercisesActivity.class);
                        intent.putExtra("workoutId", response.body().getId());
                        intent.putExtra("programId", programId);
                        intent.putExtra("trainerId", trainerId);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<Workout> call, Throwable t) {
                        Toast.makeText(CreateWorkoutActivity.this,
                                "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

    }
}