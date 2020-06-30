package id.viviaazh.todolist.services;

import java.util.List;

import id.viviaazh.todolist.api.models.Envelope;
import id.viviaazh.todolist.api.models.LoginRequest;
import id.viviaazh.todolist.api.models.LoginResponse;
import id.viviaazh.todolist.api.models.RegisterRequest;
import id.viviaazh.todolist.api.models.RegisterResponse;
import id.viviaazh.todolist.api.models.ToDoListRequest;
import id.viviaazh.todolist.api.models.ToDoListResponse;
import id.viviaazh.todolist.api.models.UpdatePasswordRequest;
import id.viviaazh.todolist.api.models.UpdatePasswordResponse;
import id.viviaazh.todolist.api.models.UserInfo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface ApiInterface {
    @POST("/v1/users")
    Call<Envelope<RegisterResponse>> doRegister(@Body RegisterRequest registerRequest);

    @POST("/v1/auth/token")
    Call<LoginResponse> doLogin(@Body LoginRequest loginRequest);

    @POST("/v1/todos")
    Call<Envelope<ToDoListResponse>> postToDoList(@Header("authorization") String token, @Body ToDoListRequest todo);

    @GET("/v1/todos")
    Call<Envelope<List<ToDoListResponse>>> getNextAllTodo(@Header("authorization")String token, @Query("page") int page);

    @PUT("/v1/users/{id}")
    Call<Envelope<UserInfo>> me (@Header("Authorization") String token);

    @POST("/v1/users/password")
    Call<Envelope<UpdatePasswordResponse>> updatePassword(@Body UpdatePasswordRequest updatePasswordRequest);
}
