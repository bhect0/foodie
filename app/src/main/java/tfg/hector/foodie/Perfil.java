package tfg.hector.foodie;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
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
import com.squareup.picasso.Picasso;

public class Perfil extends Fragment { // extends fragment
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    static TextView nombre;
    static TextView email;
    static ImageView iv_uImagen;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.perfil, container, false);

        nombre = view.findViewById(R.id.nombre);
        email = view.findViewById(R.id.email);
        iv_uImagen = view.findViewById(R.id.iv_uImagen);
        //view.find...
        //String u_nombre = gsc.getApiOptions().getAccount().name;
        //String u_mail = gsc.getApiOptions().get;

        return view;
    }


   /* public static void setDatosUsuario(String nombreUsuario, String emailUsuario, Uri fotoUsuario) {
        nombre.setText(nombreUsuario);
        email.setText(emailUsuario);
        /*Picasso.get()
                .load(fotoUsuario)
                .into(iv_uImagen);*/
    //}

    public void signOut() {
        gsc.signOut().addOnCompleteListener(task -> {
            //Aplicacion.so();
        });
    }

}