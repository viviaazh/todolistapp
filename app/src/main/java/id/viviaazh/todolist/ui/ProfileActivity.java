package id.viviaazh.todolist.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import id.viviaazh.todolist.R;
import id.viviaazh.todolist.api.helper.ServiceGenerator;
import id.viviaazh.todolist.api.models.Envelope;
import id.viviaazh.todolist.api.models.UserInfo;
import id.viviaazh.todolist.services.ApiInterface;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    public Context context;
    private TextView mNameTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        context = getApplicationContext();
        getMe();
        mNameTxt = findViewById(R.id.nameTxt);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1 && resultCode == 1) {
            getMe();
            return;
        }
    }

    private void getMe(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        Toast.makeText(context, preferences.getString("token", null), Toast.LENGTH_SHORT).show();
        ApiInterface service = ServiceGenerator.createService(ApiInterface.class);
        Call<Envelope<UserInfo>> call = service.me("Bearer" + " " + preferences.getString("token", null));
        call.enqueue(new Callback<Envelope<UserInfo>>() {
            @Override
            public void onResponse(Call<Envelope<UserInfo>> call, Response<Envelope<UserInfo>> response) {
                mNameTxt.setText(response.body().getData().getName());
            }

            @Override
            public void onFailure(Call<Envelope<UserInfo>> call, Throwable t) {
                Toast.makeText(ProfileActivity.this, "Error Request", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void handleChangePassword(View view) {
        Intent intent = new Intent(this, UpdatePasswordActivity.class);
        startActivity(intent);
    }
}
