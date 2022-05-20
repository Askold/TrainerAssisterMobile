package ru.silonov.assister.model.entity;

public class Workout {
    private int id;
    private String name;
    private int program;

    public Workout() {
    }

    public Workout(int id, String name, int program) {
        this.id = id;
        this.name = name;
        this.program = program;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProgram() {
        return program;
    }

    public void setProgram(int program) {
        this.program = program;
    }

    @Override
    public String toString() {
        return "Workout{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", workout=" + program +
                '}';
    }
}
