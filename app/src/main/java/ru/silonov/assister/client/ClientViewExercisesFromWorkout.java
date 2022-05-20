package ru.silonov.assister.client;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.android.material.textview.MaterialTextView;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.silonov.assister.ApiClient;
import ru.silonov.assister.R;
import ru.silonov.assister.model.entity.Exercise;
import ru.silonov.assister.model.entity.Feedback;
import ru.silonov.assister.model.entity.SingleExercise;
import ru.silonov.assister.trainer.ExerciseAdapter;
import ru.silonov.assister.trainer.SelectExercisesActivity;

public class ClientViewExercisesFromWorkout extends AppCompatActivity implements ClickableExerciseAdapter.OnExerciseListener {

    int workoutId;
    RecyclerView recyclerView;
    ClickableExerciseAdapter adapter;
    List<SingleExercise> singleExercises;
    MaterialButton button;
    RelativeLayout root;
    TextInputLayout tilEstimate;
    AutoCompleteTextView actEstimate;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapterEstimate;
    TextInputEditText edFeedbackComment;
    int programId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_view_exercises_from_workout);

        workoutId = (int) getIntent().getSerializableExtra("workoutId");
        programId = (int) getIntent().getSerializableExtra("programId");

        root = findViewById(R.id.clientExercises);
        button = findViewById(R.id.btnCreateFeedback);
        recyclerView = findViewById(R.id.recyclerViewExercises);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Call<List<SingleExercise>> getSingleExercises = ApiClient.getUserService().getExercisesByWorkout(77);
        getSingleExercises.enqueue(new Callback<List<SingleExercise>>() {
            @Override
            public void onResponse(Call<List<SingleExercise>> call, Response<List<SingleExercise>> response) {
                singleExercises = response.body();
                adapter = new ClickableExerciseAdapter(ClientViewExercisesFromWorkout.this, singleExercises,
                        ClientViewExercisesFromWorkout.this::onExerciseClick);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<SingleExercise>> call, Throwable t) {
                Toast.makeText(ClientViewExercisesFromWorkout.this,
                        "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCreateFeedbackWindow();
            }
        });


    }

    private void showCreateFeedbackWindow() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Оставьте отзыв о занятии");
        dialog.setMessage("Как прошло занятие?");
        LayoutInflater inflater = LayoutInflater.from(this);
        View addClientWindow = inflater.inflate(R.layout.window_create_feedback, null);
        dialog.setView(addClientWindow);

        edFeedbackComment = addClientWindow.findViewById(R.id.edFeedbackComment);
        tilEstimate = addClientWindow.findViewById(R.id.tilEstimate);
        actEstimate = addClientWindow.findViewById(R.id.actEstimate);

        arrayList = new ArrayList<>();
        arrayList.add("Очень легко");
        arrayList.add("Легко");
        arrayList.add("Средне");
        arrayList.add("Тяжело");
        arrayList.add("Очень тяжело");
        arrayList.add("Невыполнимо");

        arrayAdapterEstimate = new ArrayAdapter<>(ClientViewExercisesFromWorkout.this,
                R.layout.dropdown_role_menu, arrayList);
        actEstimate.setAdapter(arrayAdapterEstimate);

        actEstimate.setThreshold(1);

        dialog.setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.setPositiveButton("Создать", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                Feedback feedback = new Feedback();
                feedback.setComment(edFeedbackComment.getText().toString());
                feedback.setComment(actEstimate.getText().toString());

               Call<Feedback> feedbackCall = ApiClient.getUserService().insertFeedback(feedback);
               feedbackCall.enqueue(new Callback<Feedback>() {
                   @Override
                   public void onResponse(Call<Feedback> call, Response<Feedback> response) {
                       Toast.makeText(ClientViewExercisesFromWorkout.this, "Success", Toast.LENGTH_LONG).show();
                       Intent intent = new Intent(ClientViewExercisesFromWorkout.this, ClientWorkoutsActivity.class);
                       intent.putExtra("programId", programId);
                       startActivity(intent);
                   }

                   @Override
                   public void onFailure(Call<Feedback> call, Throwable t) {
                       Toast.makeText(ClientViewExercisesFromWorkout.this, "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                   }
               });
            }
        });
        dialog.show();
    }

    @Override
    public void onExerciseClick(int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Информация об упражнении");
        dialog.setMessage("Упражнение");
        LayoutInflater inflater = LayoutInflater.from(this);
        View addClientWindow = inflater.inflate(R.layout.window_exercise_description, null);
        dialog.setView(addClientWindow);

        MaterialTextView vDescription = addClientWindow.findViewById(R.id.vExerciseDescription);

        Call<Exercise> getExercise = ApiClient.getUserService().getExercise(singleExercises.get(position).getExercise());
        getExercise.enqueue(new Callback<Exercise>() {
            @Override
            public void onResponse(Call<Exercise> call, Response<Exercise> response) {
                System.out.println(response.body());
                assert response.body() != null;
                 vDescription.setText(response.body().getDescription());
            }

            @Override
            public void onFailure(Call<Exercise> call, Throwable t) {
                Toast.makeText(ClientViewExercisesFromWorkout.this,
                        "Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

        dialog.setNegativeButton("Закрыть", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        dialog.show();
    }


}