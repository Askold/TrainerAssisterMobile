package ru.silonov.assister.trainer;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.PixelCopy;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.silonov.assister.ApiClient;
import ru.silonov.assister.R;
import ru.silonov.assister.model.entity.Exercise;
import ru.silonov.assister.model.entity.SingleExercise;
import ru.silonov.assister.model.login.Client;

public class SelectExercisesActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    ExerciseAdapter adapter;
    MaterialButton btnSelectExercise, btnCreateExercise, btnDone;
    TextInputLayout tilExercise;
    AutoCompleteTextView actExercise;
    RelativeLayout root;

    List<SingleExercise> singleExercises = new ArrayList<>();
    List<String> arrayListExercise = new ArrayList<>();
    ArrayAdapter<String> arrayAdapterExercise;

    int workoutId;
    int programId;
    int trainerId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_exercises);

        workoutId = (int) getIntent().getSerializableExtra("workoutId");
        programId = (int) getIntent().getSerializableExtra("programId");
        trainerId = (int) getIntent().getSerializableExtra("trainerId");

        root = findViewById(R.id.relativeExercisesPage);

        recyclerView = findViewById(R.id.recyclerExercises);

        btnCreateExercise = findViewById(R.id.btnCreateExercise);
        btnSelectExercise = findViewById(R.id.btnSelectExercise);
        btnDone = findViewById(R.id.btnAWorkoutDone);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new ExerciseAdapter(SelectExercisesActivity.this, singleExercises);
        recyclerView.setAdapter(adapter);

        btnCreateExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreateExerciseWindow();
            }
        });


        btnSelectExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSelectExerciseWindow();
            }
        });

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertAllSingleExercises();
            }
        });
    }

    private void insertAllSingleExercises() {
        Call<SingleExercise> insertExercises =
                ApiClient.getUserService().insertAllExercises(singleExercises);
        insertExercises.enqueue(new Callback<SingleExercise>() {
            @Override
            public void onResponse(Call<SingleExercise> call, Response<SingleExercise> response) {
                Toast.makeText(SelectExercisesActivity.this,
                        "Successfully added", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(SelectExercisesActivity.this,
                        AddWorkoutsActivity.class);
                intent.putExtra("programId", programId);
                intent.putExtra("trainerId", trainerId);
                startActivity(intent);
            }

            @Override
            public void onFailure(Call<SingleExercise> call, Throwable t) {
                Toast.makeText(SelectExercisesActivity.this,
                        "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showSelectExerciseWindow(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Выберите упражнение");
        dialog.setMessage("Укажите информацию об упражнении");
        LayoutInflater inflater = LayoutInflater.from(this);
        View addClientWindow = inflater.inflate(R.layout.window_select_exercise, null);
        dialog.setView(addClientWindow);

        tilExercise = addClientWindow.findViewById(R.id.tilSelectExercise);
        actExercise = addClientWindow.findViewById(R.id.actSelectExercise);

        Call<List<String>> getExercises = ApiClient.getUserService().getAllExercises();
        getExercises.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                System.out.println(response.body());
                arrayListExercise = response.body();
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Toast.makeText(SelectExercisesActivity.this, "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        setDropDownMenu();

        TextInputEditText edWeight = addClientWindow.findViewById(R.id.edAddExerciseWeight);
        TextInputEditText edRepetitions = addClientWindow.findViewById(R.id.edAddExerciseRepetitions);
        TextInputEditText edRounds = addClientWindow.findViewById(R.id.edAddExerciseRounds);

        dialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Создать", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (TextUtils.isEmpty(edWeight.getText().toString())){
                    Snackbar.make(root, "Введите рабочий вес", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(edRepetitions.getText().toString())){
                    Snackbar.make(root, "Введите количество повторений", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(edRounds.getText().toString())){
                    Snackbar.make(root, "Введите количество подходов", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                SingleExercise singleExercise = new SingleExercise();
                singleExercise.setWorkoutId(workoutId);
                singleExercise.setExercise(actExercise.getText().toString());
                singleExercise.setWeight(Integer.parseInt(edWeight.getText().toString()));
                singleExercise.setRepetitions(Integer.parseInt(edRepetitions.getText().toString()));
                singleExercise.setRounds(Integer.parseInt(edRounds.getText().toString()));

                singleExercises.add(singleExercise);
                adapter.notifyDataSetChanged();

            }
        });
        dialog.show();
    }

    public void showCreateExerciseWindow(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Внесение упражнения в базу");
        dialog.setMessage("Укажите информацию об упражнении");
        LayoutInflater inflater = LayoutInflater.from(this);
        View addClientWindow = inflater.inflate(R.layout.window_create_exercise, null);
        dialog.setView(addClientWindow);

        TextInputEditText edName = addClientWindow.findViewById(R.id.edAddExerciseName);
        TextInputEditText edDescription = addClientWindow.findViewById(R.id.edAddExerciseDescription);
        TextInputEditText edVideo = addClientWindow.findViewById(R.id.edAddExerciseVideo);

        dialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Создать", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (TextUtils.isEmpty(edName.getText().toString())){
                    Snackbar.make(root, "Введите название тренировки", Snackbar.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(edDescription.getText().toString())){
                    Snackbar.make(root, "Введите описание тренировки", Snackbar.LENGTH_SHORT).show();
                    return;
                }

                Exercise exercise = new Exercise();
                exercise.setName(edName.getText().toString());
                exercise.setDescription(edDescription.getText().toString());
                exercise.setVideo(edVideo.getText().toString());

                Call<Exercise> createExercise = ApiClient.getUserService().addNewExercise(exercise);
                createExercise.enqueue(new Callback<Exercise>() {
                    @Override
                    public void onResponse(Call<Exercise> call, Response<Exercise> response) {
                        Toast.makeText(SelectExercisesActivity.this, "Successfully added", Toast.LENGTH_LONG).show();

                    }

                    @Override
                    public void onFailure(Call<Exercise> call, Throwable t) {
                        Toast.makeText(SelectExercisesActivity.this, "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });

            }
        });
        dialog.show();
    }

    public void setDropDownMenu(){
        arrayAdapterExercise = new ArrayAdapter<>(SelectExercisesActivity.this, R.layout.dropdown_role_menu, arrayListExercise);
        actExercise.setAdapter(arrayAdapterExercise);
        actExercise.setThreshold(1);
    }


}