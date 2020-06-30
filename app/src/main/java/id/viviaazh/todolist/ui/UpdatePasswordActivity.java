package id.viviaazh.todolist.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import id.viviaazh.todolist.R;
import id.viviaazh.todolist.api.helper.ServiceGenerator;
import id.viviaazh.todolist.api.models.ApiError;
import id.viviaazh.todolist.api.models.Envelope;
import id.viviaazh.todolist.api.models.ErrorUtils;
import id.viviaazh.todolist.api.models.UpdatePasswordRequest;
import id.viviaazh.todolist.api.models.UpdatePasswordResponse;
import id.viviaazh.todolist.services.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdatePasswordActivity extends AppCompatActivity {
    private EditText mCurrentPass;
    private EditText mNewPass;
    private UpdatePasswordRequest updatePasswordRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);
        mCurrentPass = findViewById(R.id.currentTxt);
        mNewPass = findViewById(R.id.newPassTxt);
    }

    public void updatePassword(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class, "Bearer" + preferences.getString("token", null));
        Call<Envelope<UpdatePasswordResponse>> call = service.updatePassword(updatePasswordRequest);
        call.enqueue(new Callback<Envelope<UpdatePasswordResponse>>() {
            @Override
            public void onResponse(Call<Envelope<UpdatePasswordResponse>> call, Response<Envelope<UpdatePasswordResponse>> response) {
                if(response.isSuccessful()){
                    Intent intent = new Intent(UpdatePasswordActivity.this, ProfileActivity.class);
                    startActivity(intent);
                }
                else{
                    ApiError error = ErrorUtils.parseError(response);
                    if(error.getError().getCurrentPassword() != null){
                        Toast.makeText(UpdatePasswordActivity.this, error.getError().getCurrentPassword().get(0), Toast.LENGTH_SHORT).show();
                    }
                    else if(error.getError().getNewPassword() != null){
                        Toast.makeText(UpdatePasswordActivity.this, error.getError().getNewPassword().get(0), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<Envelope<UpdatePasswordResponse>> call, Throwable t) {
                Toast.makeText(UpdatePasswordActivity.this, "Error Request", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void handleUpdatePassword(View view) {
        String currentPassword = mCurrentPass.getText().toString();
        String password = mNewPass.getText().toString();
        updatePasswordRequest = new UpdatePasswordRequest(currentPassword, password);
        updatePassword();
    }
}
