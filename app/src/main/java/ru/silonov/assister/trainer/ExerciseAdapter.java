package ru.silonov.assister.trainer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import ru.silonov.assister.R;
import ru.silonov.assister.model.entity.Exercise;
import ru.silonov.assister.model.entity.SingleExercise;
import ru.silonov.assister.model.login.Client;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExercsiseViewHolder> {

    private Context context;
    private List<SingleExercise> singleExercises;

    public ExerciseAdapter(Context context, List<SingleExercise> singleExercises) {
        this.context = context;
        this.singleExercises = singleExercises;
    }

    @NonNull
    @Override
    public ExercsiseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_exercises, parent, false);
        return new ExercsiseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercsiseViewHolder holder, int position) {
        SingleExercise singleExercise = singleExercises.get(position);

        holder.textViewName.setText(String.valueOf(singleExercise.getExercise()));
        holder.textViewWeight.setText(String.valueOf(singleExercise.getWeight()));
        holder.textViewRepetitions.setText(String.valueOf(singleExercise.getRepetitions()));
        holder.textViewRounds.setText(String.valueOf(singleExercise.getRounds()));
    }

    @Override
    public int getItemCount() {
        return singleExercises.size();
    }

    class ExercsiseViewHolder extends RecyclerView.ViewHolder{

        MaterialTextView textViewName, textViewRepetitions, textViewRounds, textViewWeight;

        public ExercsiseViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.vExerciseName);
            textViewRepetitions = itemView.findViewById(R.id.vExerciseRepetitions);
            textViewRounds = itemView.findViewById(R.id.vExerciseRounds);
            textViewWeight = itemView.findViewById(R.id.vExerciseWeight);

        }

    }
}
