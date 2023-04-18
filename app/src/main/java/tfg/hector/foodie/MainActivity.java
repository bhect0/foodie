package tfg.hector.foodie;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity { // pantalla de login

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        Button btnLogin = findViewById(R.id.loginButton);
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, Aplicacion.class);
            startActivity(intent);
        });





    }

}