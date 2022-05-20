package ru.silonov.assister.model.login;

import java.io.Serializable;

import ru.silonov.assister.model.registration.User;

public class Client extends User implements Serializable {
    private int age;
    private int height;
    private int weight;

    public Client(String name, String surname, String login, String password, int age, int height, int weight) {
        super(name, surname, login, password);
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public Client() {
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
        return "Client{" +
                "age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
