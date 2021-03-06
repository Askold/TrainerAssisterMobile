package ru.silonov.assister.model.registration;

public class RegistrationClientRequest extends User{
    private int age;
    private int height;
    private int weight;

    public RegistrationClientRequest(String name, String surname, String login, String password, int age, int height, int weight) {
        super(name, surname, login, password);
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public RegistrationClientRequest() {
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "RegistrationClientRequest{" +
                "age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
