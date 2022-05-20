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
import ru.silonov.assister.model.entity.Workout;
import ru.silonov.assister.trainer.AddWorkoutsActivity;
import ru.silonov.assister.trainer.WorkoutsAdapter;

public class ClientWorkoutsActivity extends AppCompatActivity implements ProgramAdapter.OnProgramListener {

    int programId;

    private RecyclerView recyclerView;
    private ClickableWorkoutsAdapter adapter;
    private List<Workout> workoutList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_workouts);

        programId = (int) getIntent().getSerializableExtra("programId");

        recyclerView = findViewById(R.id.recyclerViewWorkouts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<List<Workout>> getWorkouts = ApiClient.getUserService().getWorkoutsByProgram(programId);
        getWorkouts.enqueue(new Callback<List<Workout>>() {
            @Override
            public void onResponse(Call<List<Workout>> call, Response<List<Workout>> response) {
                System.out.println(response.body());
                workoutList = response.body();
                adapter = new ClickableWorkoutsAdapter(ClientWorkoutsActivity.this,
                        workoutList, ClientWorkoutsActivity.this::onProgramClick);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Workout>> call, Throwable t) {
                Toast.makeText(ClientWorkoutsActivity.this,
                        "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });


    }

    @Override
    public void onProgramClick(int position) {
        Intent intent = new Intent(ClientWorkoutsActivity.this,
                ClientViewExercisesFromWorkout.class);
        intent.putExtra("workoutId", workoutList.get(position).getId());
        intent.putExtra("programId", programId);
        startActivity(intent);
    }
}