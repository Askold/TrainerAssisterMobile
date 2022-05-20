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
import ru.silonov.assister.model.entity.Workout;
import ru.silonov.assister.trainer.WorkoutsAdapter;

public class ClickableWorkoutsAdapter extends RecyclerView.Adapter<ClickableWorkoutsAdapter.WorkoutsViewHolder> {


    private Context mContext;
    private List<Workout> workoutList;
    private OnWorkoutListener mOnWorkoutListener;

    public ClickableWorkoutsAdapter(Context mContext, List<Workout> workoutList, OnWorkoutListener onWorkoutListener) {
        this.mContext = mContext;
        this.workoutList = workoutList;
        this.mOnWorkoutListener = onWorkoutListener;
    }

    @NonNull
    @Override
    public ClickableWorkoutsAdapter.WorkoutsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_workouts, parent, false);
        return new ClickableWorkoutsAdapter.WorkoutsViewHolder(view, mOnWorkoutListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClickableWorkoutsAdapter.WorkoutsViewHolder holder, int position) {
        Workout workout = workoutList.get(position);
        holder.textViewWorkout.setText(workout.getName());
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }


    class WorkoutsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        MaterialTextView textViewWorkout;
        OnWorkoutListener onWorkoutListener;

        public WorkoutsViewHolder(@NonNull View itemView, OnWorkoutListener onWorkoutListener) {
            super(itemView);

            textViewWorkout = itemView.findViewById(R.id.vWorkoutName);

            this.onWorkoutListener = onWorkoutListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onWorkoutListener.onWorkoutClick(getAbsoluteAdapterPosition());
        }
    }

    public interface OnWorkoutListener{
        void onWorkoutClick(int position);
    }
}
