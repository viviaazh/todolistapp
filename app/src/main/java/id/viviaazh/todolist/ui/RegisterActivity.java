package id.viviaazh.todolist.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.viviaazh.todolist.R;
import id.viviaazh.todolist.api.helper.ServiceGenerator;
import id.viviaazh.todolist.api.models.ApiError;
import id.viviaazh.todolist.api.models.Envelope;
import id.viviaazh.todolist.api.models.ErrorUtils;
import id.viviaazh.todolist.api.models.RegisterRequest;
import id.viviaazh.todolist.api.models.RegisterResponse;
import id.viviaazh.todolist.services.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    public RegisterRequest registerRequest;
    private EditText username;
    private EditText name;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        username = findViewById(R.id.reg_username);
        name = findViewById(R.id.reg_name);
        password = findViewById(R.id.reg_pass);
    }

    public void register(){
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<Envelope<RegisterResponse>> call = service.doRegister(registerRequest);
        call.enqueue(new Callback<Envelope<RegisterResponse>>() {
            @Override
            public void onResponse(Call<Envelope<RegisterResponse>> call, Response<Envelope<RegisterResponse>> response) {
                if(response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Register Successfull", Toast.LENGTH_SHORT).show();
                    finish();
                }else {
                    ApiError error = ErrorUtils.parseError(response);
                    if(error.getError().getUsername()!=null){
                        Toast.makeText(RegisterActivity.this, error.getError().getUsername().get(0), Toast.LENGTH_SHORT).show();
                    }
                    else if(error.getError().getName()!=null){
                        Toast.makeText(RegisterActivity.this, error.getError().getName().get(0), Toast.LENGTH_SHORT).show();
                    }
                    else if(error.getError().getPassword()!=null){
                        Toast.makeText(RegisterActivity.this, error.getError().getPassword().get(0), Toast.LENGTH_SHORT).show();
                    }
//                    else if(error.getError().getPassword()!=null){
//                        for(int k=0; k<error.getError().getPassword().size(); k++){
//                            Toast.makeText(RegisterActivity.this, error.getError().getPassword().get(k), Toast.LENGTH_SHORT).show();
//                        }
//                    }
                }
            }

            @Override
            public void onFailure(Call<Envelope<RegisterResponse>> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "Error Request", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void handleRegister(View view) {
        String rusername = username.getText().toString();
        String rname = name.getText().toString();
        String rpassword = password.getText().toString();
        registerRequest = new RegisterRequest(rusername, rname, rpassword);
        register();
    }
}
