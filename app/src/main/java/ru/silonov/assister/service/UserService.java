package ru.silonov.assister.service;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import ru.silonov.assister.model.entity.Exercise;
import ru.silonov.assister.model.entity.Feedback;
import ru.silonov.assister.model.entity.Program;
import ru.silonov.assister.model.entity.SingleExercise;
import ru.silonov.assister.model.entity.Workout;
import ru.silonov.assister.model.login.Client;
import ru.silonov.assister.model.login.LoginRequest;
import ru.silonov.assister.model.login.Trainer;
import ru.silonov.assister.model.registration.RegistrationClientRequest;
import ru.silonov.assister.model.registration.RegistrationTrainerRequest;
import ru.silonov.assister.model.request.AddClientRequest;
import ru.silonov.assister.response.DefaultResponse;

public interface UserService {

    @POST("/trainer/login")
    Call<Trainer> trainerLogin(@Body LoginRequest loginRequest);

    @POST("/client/login")
    Call<Client> clientLogin(@Body LoginRequest loginRequest);

    @POST("/trainer/register")
    Call<Trainer> trainerRegister(@Body RegistrationTrainerRequest trainerRequest);

    @POST("/client/register")
    Call<Client> clientRegister(@Body RegistrationClientRequest clientRequest);

    @GET("trainer/allclients/{id}")
    Call<List<Client>> getTrainerClients(@Path("id") int id);

    @PATCH("trainer/addclient")
    Call<List<Client>> addClient(@Body AddClientRequest addClientRequest);

    @POST("/program/new")
    Call<Program> addNewProgram(@Body Program program);

    @POST("/workout/new")
    Call<Workout> addNewWorkout(@Body Workout workout);

    @POST("/exercise/new")
    Call<Exercise> addNewExercise(@Body Exercise exercise);

    @GET("/exercise/get/{name}")
    Call<Exercise> getExercise(@Path("name") String name);

    @GET("exercise/all")
    Call<List<String>> getAllExercises();

    @POST("singleexercise/new")
    Call<SingleExercise> addNewSingleExercise(@Body SingleExercise exercise);

    @GET("singleexercise/workout/{id}")
    Call<List<SingleExercise>> getExercisesByWorkout(@Path("id") int id);

    @GET("workout/program/{id}")
    Call<List<Workout>> getWorkoutsByProgram(@Path("id") int id);

    @POST("singleexercise/addall")
    Call<SingleExercise> insertAllExercises(@Body List<SingleExercise> exercises);

    @GET("program/client/{id}")
    Call<List<Program>> getProgramsByClient(@Path("id") int id);

    @POST("feedback/new")
    Call<Feedback> insertFeedback(@Body Feedback feedback);

}
