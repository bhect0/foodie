package tfg.hector.foodie;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class Perfil extends Fragment { // extends fragment
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    TextView nombre;
    TextView email;
    ImageView iv_uImagen;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.perfil, container, false);

        nombre = view.findViewById(R.id.nombre);
        email = view.findViewById(R.id.email);
        iv_uImagen = view.findViewById(R.id.iv_uImagen);
        //iv_uImagen = view.findViewById(R.id.imageView);

        setDatosUsuario();
        return view;
    }


   public void setDatosUsuario() {
        if (Aplicacion.nombreUsuario != null) {
            nombre.setText(Aplicacion.nombreUsuario);
            email.setText(Aplicacion.emailUsuario);
            Picasso.get()
                    .load(Aplicacion.fotoUsuario)
                    .transform(new CropCircleTransformation())
                    .into(iv_uImagen);
        } else {
            nombre.setText(R.string.nombre);
            email.setText(R.string.email);
            Picasso.get()
                    .load(R.drawable.perfil_defecto)
                    .into(iv_uImagen);
        }

    }

    public void signOut() {
        gsc.signOut().addOnCompleteListener(task -> {
            //Aplicacion.so();
        });
    }

}