package id.viviaazh.todolist.services;

import id.viviaazh.todolist.api.models.Envelope;
import id.viviaazh.todolist.api.models.LoginRequest;
import id.viviaazh.todolist.api.models.LoginResponse;
import id.viviaazh.todolist.api.models.RegisterRequest;
import id.viviaazh.todolist.api.models.RegisterResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterface {
    @POST("/v1/users")
    Call<Envelope<RegisterResponse>> doRegister(@Body RegisterRequest registerRequest);

    @POST("/v1/auth/token")
    Call<LoginResponse> doLogin(@Body LoginRequest loginRequest);
}
