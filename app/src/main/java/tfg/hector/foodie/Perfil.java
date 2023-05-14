package tfg.hector.foodie;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class Perfil extends Fragment { // extends fragment
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    TextView nombre;
    TextView email;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.perfil, container, false);

        //nombre = (TextView) getView().findViewById(R.id.nombre);
        //email = (TextView) getView().findViewById(R.id.email);
        //view.find...

        return view;
    }


    public void setDatosUsuario(String nombreUsuario, String emailUsuario) {
        nombre.setText(nombreUsuario);
        email.setText(emailUsuario);
    }

}