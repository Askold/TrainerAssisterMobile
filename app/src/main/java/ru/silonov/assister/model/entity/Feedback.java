package ru.silonov.assister.model.entity;

public class Feedback {
    private int id = 0;
    private String date = "qwe";
    private String estimate;
    private String comment;
    private int client;

    public Feedback() {
    }

    public Feedback(int id, String date, String estimate, String comment) {
        this.id = id;
        this.date = date;
        this.estimate = estimate;
        this.comment = comment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getEstimate() {
        return estimate;
    }

    public void setEstimate(String estimate) {
        this.estimate = estimate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", estimate='" + estimate + '\'' +
                ", comment='" + comment + '\'' +
                '}';
    }
}
