package ru.silonov.assister.model.registration;

public class RegistrationTrainerRequest extends User{
    private int experience;

    public RegistrationTrainerRequest(String name, String surname, String login, String password, int experience) {
        super(name, surname, login, password);
        this.experience = experience;
    }

    public RegistrationTrainerRequest() {
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    @Override
    public String toString() {
        return "RegistrationTrainerRequest{" +
                "experience=" + experience +
                '}';
    }
}
