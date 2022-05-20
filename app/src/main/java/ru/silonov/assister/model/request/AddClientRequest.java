package ru.silonov.assister.model.request;

public class AddClientRequest {
    private int trainerId;
    private String clientLogin;

    public AddClientRequest(int trainerId, String clientLogin) {
        this.trainerId = trainerId;
        this.clientLogin = clientLogin;
    }

    public AddClientRequest() {
    }

    public int getTrainerId() {
        return trainerId;
    }

    public String getClientLogin() {
        return clientLogin;
    }

    @Override
    public String toString() {
        return "AddClientRequest{" +
                "trainerId=" + trainerId +
                ", userLogin='" + clientLogin + '\'' +
                '}';
    }
}
