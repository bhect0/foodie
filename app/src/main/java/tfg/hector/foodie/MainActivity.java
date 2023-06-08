package tfg.hector.foodie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class MainActivity extends AppCompatActivity { // pantalla de login

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    EditText et_usuario;
    EditText et_contrasena;
    Button btn_login;
    TextView olvido_credenciales;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        et_usuario = findViewById(R.id.et_usuario);
        et_contrasena = findViewById(R.id.et_contrasena);

        btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(v -> {
            if (et_usuario.getText().toString().equals("profesor") &&
                    et_contrasena.getText().toString().equals("Profesor123")) {
                Intent intent = new Intent(this, Aplicacion.class);
                startActivity(intent);
            } else {
                olvido_credenciales.performClick();
            }
        });

        LinearLayout google_btn = findViewById(R.id.google_btn);
        olvido_credenciales = findViewById(R.id.olvido_credenciales);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        olvido_credenciales.setOnClickListener(v -> {
            Toast.makeText(getApplicationContext(), "Las creedenciales especiales para profesores son:\n \n Usuario: profesor \n Contraseña: Profesor123", Toast.LENGTH_SHORT).show();
        });

        google_btn.setOnClickListener(v -> {
            signIn();
        });


    }

    private void signIn() {
        Intent signIntent = gsc.getSignInIntent();
        startActivityForResult(signIntent, 1000);
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1000){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);

            try {
                task.getResult(ApiException.class);
                comenzarApp();
            } catch (ApiException e){
                Toast.makeText(getApplicationContext(), "Algo ha ido mal en el inicio de sesión", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void comenzarApp() {
        finish();
        Intent i = new Intent(MainActivity.this, Aplicacion.class);
        startActivity(i);
    }

}