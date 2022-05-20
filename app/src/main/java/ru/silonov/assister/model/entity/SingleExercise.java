package ru.silonov.assister.model.entity;

public class SingleExercise {
    private int id = 0;
    private int workoutId;
    private String exercise;
    private int weight;
    private int repetitions;
    private int rounds;

    public SingleExercise(int id, int workoutId, String exercise, int weight, int repetitions, int rounds) {
        this.id = id;
        this.workoutId = workoutId;
        this.exercise = exercise;
        this.weight = weight;
        this.repetitions = repetitions;
        this.rounds = rounds;
    }

    public SingleExercise() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkoutId() {
        return workoutId;
    }

    public void setWorkoutId(int workoutId) {
        this.workoutId = workoutId;
    }

    public String getExercise() {
        return exercise;
    }

    public void setExercise(String exercise) {
        this.exercise = exercise;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getRounds() {
        return rounds;
    }

    public void setRounds(int rounds) {
        this.rounds = rounds;
    }

    @Override
    public String toString() {
        return "SingleExercise{" +
                "id=" + id +
                ", workoutId=" + workoutId +
                ", exercise='" + exercise + '\'' +
                ", weight=" + weight +
                ", repetitions=" + repetitions +
                ", rounds=" + rounds +
                '}';
    }
}
