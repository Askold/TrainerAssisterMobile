package ru.silonov.assister.trainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.silonov.assister.ApiClient;
import ru.silonov.assister.R;
import ru.silonov.assister.model.entity.Workout;

public class AddWorkoutsActivity extends AppCompatActivity {

    MaterialButton btnCreateWorkout, btnProgramDone;
    int programId;
    int trainerId;

    private RecyclerView recyclerView;
    private WorkoutsAdapter adapter;
    private List<Workout> workoutList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workouts);

        programId = (int) getIntent().getSerializableExtra("programId");
        trainerId = (int) getIntent().getSerializableExtra("trainerId");


        btnCreateWorkout = findViewById(R.id.btnAddWorkout);
        btnProgramDone = findViewById(R.id.btnProgramDone);
        recyclerView = findViewById(R.id.recyclerAddWorkouts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<List<Workout>> getWorkouts = ApiClient.getUserService().getWorkoutsByProgram(programId);
        getWorkouts.enqueue(new Callback<List<Workout>>() {
            @Override
            public void onResponse(Call<List<Workout>> call, Response<List<Workout>> response) {
                System.out.println(response.body());
                workoutList = response.body();
                adapter = new WorkoutsAdapter(AddWorkoutsActivity.this, workoutList);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Workout>> call, Throwable t) {
                Toast.makeText(AddWorkoutsActivity.this,
                        "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        btnCreateWorkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddWorkoutsActivity.this,
                        CreateWorkoutActivity.class);
                intent.putExtra("programId", programId);
                intent.putExtra("trainerId", trainerId);
                startActivity(intent);
            }
        });

        btnProgramDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddWorkoutsActivity.this, MyClientsActivity.class);
                intent.putExtra("id", trainerId);
                startActivity(intent);
            }
        });
    }
}