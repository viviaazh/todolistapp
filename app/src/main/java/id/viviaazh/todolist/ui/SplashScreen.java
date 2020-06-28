package id.viviaazh.todolist.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import id.viviaazh.todolist.R;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Thread thread = new Thread() {
            public void run(){
                try {
                    sleep(1200);
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    Intent intent = new Intent (SplashScreen.this, MainActivity.class);
                    startActivity(intent);

                    finish();
                }
            }
        };
        thread.start();

    }
}
