package ru.silonov.assister.model.login;

import java.io.Serializable;

import ru.silonov.assister.model.registration.User;

public class Trainer extends User implements Serializable {

    private int experience;

    public Trainer(String name, String surname, String login, String password, int experience) {
        super(name, surname, login, password);
        this.experience = experience;
    }

    public Trainer() {
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "Trainer{" +
                "experience=" + experience +
                "} " + super.toString();
    }
}
