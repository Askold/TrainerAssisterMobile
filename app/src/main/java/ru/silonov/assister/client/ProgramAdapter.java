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
import ru.silonov.assister.model.entity.Program;
import ru.silonov.assister.model.entity.Workout;
import ru.silonov.assister.trainer.WorkoutsAdapter;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ProgramViewHolder>{

    private Context mContext;
    private List<Program> programList;
    private OnProgramListener mOnProgramListener;

    public ProgramAdapter(Context mContext, List<Program> programList, OnProgramListener onProgramListener) {
        this.mContext = mContext;
        this.programList = programList;
        this.mOnProgramListener = onProgramListener;
    }

    @NonNull
    @Override
    public ProgramAdapter.ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyclerview_programs, parent, false);
        return new ProgramViewHolder(view, mOnProgramListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramAdapter.ProgramViewHolder holder, int position) {
        Program program = programList.get(position);

        String durationString = "Недели: " + program.getDurationInWeeks();
        String workoutsString = "Тренировок в неделю:" + program.getWorkoutsPerWeek();

        holder.programType.setText(program.getType());
        holder.duration.setText(durationString);
        holder.workouts.setText(workoutsString);
        holder.description.setText(program.getDescription());
    }

    @Override
    public int getItemCount() {
        return programList.size();
    }

    class ProgramViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        MaterialTextView programType, duration, workouts, description;
        OnProgramListener onProgramListener;

        public ProgramViewHolder(@NonNull View itemView, OnProgramListener onProgramListener) {
            super(itemView);

            programType = itemView.findViewById(R.id.vProgramType);
            duration = itemView.findViewById(R.id.vProgramDuration);
            workouts = itemView.findViewById(R.id.vProgramWorkouts);
            description = itemView.findViewById(R.id.vProgramDescription);

            this.onProgramListener = onProgramListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onProgramListener.onProgramClick(getAbsoluteAdapterPosition());
        }
    }

    public interface OnProgramListener{
        void onProgramClick(int position);
    }
}
