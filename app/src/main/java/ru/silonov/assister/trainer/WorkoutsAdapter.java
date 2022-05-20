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
import ru.silonov.assister.model.entity.Workout;
import ru.silonov.assister.model.login.Client;

public class WorkoutsAdapter extends RecyclerView.Adapter<WorkoutsAdapter.WorkoutsViewHolder>{

    private Context mContext;
    private List<Workout> workoutList;

    public WorkoutsAdapter(Context mContext, List<Workout> workoutList) {
        this.mContext = mContext;
        this.workoutList = workoutList;
    }

    @NonNull
    @Override
    public WorkoutsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_workouts, parent, false);
        return new WorkoutsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WorkoutsViewHolder holder, int position) {
        Workout workout = workoutList.get(position);

        holder.textViewWorkout.setText(workout.getName());
    }

    @Override
    public int getItemCount() {
        return workoutList.size();
    }

    class WorkoutsViewHolder extends RecyclerView.ViewHolder{

        MaterialTextView textViewWorkout;

        public WorkoutsViewHolder(@NonNull View itemView) {
            super(itemView);

            textViewWorkout = itemView.findViewById(R.id.vWorkoutName);
        }
    }
}
