package ru.silonov.assister.client;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textview.MaterialTextView;

import java.util.List;

import ru.silonov.assister.R;
import ru.silonov.assister.model.entity.SingleExercise;
import ru.silonov.assister.trainer.ExerciseAdapter;

public class ClickableExerciseAdapter extends RecyclerView.Adapter<ClickableExerciseAdapter.ExercsiseViewHolder>{
    private Context context;
    private List<SingleExercise> singleExercises;
    OnExerciseListener mOnExerciseListener;

    public ClickableExerciseAdapter(Context context, List<SingleExercise> singleExercises, OnExerciseListener onExerciseListener) {
        this.context = context;
        this.singleExercises = singleExercises;
        this.mOnExerciseListener = onExerciseListener;
    }

    @NonNull
    @Override
    public ClickableExerciseAdapter.ExercsiseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_exercises, parent, false);
        return new ClickableExerciseAdapter.ExercsiseViewHolder(view, mOnExerciseListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClickableExerciseAdapter.ExercsiseViewHolder holder, int position) {
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

    class ExercsiseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        MaterialTextView textViewName, textViewRepetitions, textViewRounds, textViewWeight;
        OnExerciseListener onExerciseListener;

        public ExercsiseViewHolder(@NonNull View itemView, OnExerciseListener onExerciseListener) {
            super(itemView);

            textViewName = itemView.findViewById(R.id.vExerciseName);
            textViewRepetitions = itemView.findViewById(R.id.vExerciseRepetitions);
            textViewRounds = itemView.findViewById(R.id.vExerciseRounds);
            textViewWeight = itemView.findViewById(R.id.vExerciseWeight);

            this.onExerciseListener = onExerciseListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onExerciseListener.onExerciseClick(getAbsoluteAdapterPosition());
        }
    }

    public interface OnExerciseListener{
        void onExerciseClick(int position);
    }
}
