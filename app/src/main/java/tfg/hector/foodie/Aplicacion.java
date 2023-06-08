package tfg.hector.foodie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class Aplicacion extends FragmentActivity {

    // Fragmentos
    private Recetas recetas;
    private Match match;
    private Perfil perfil;
    private VerReceta verReceta;
    public static Button boton4;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aplicacion);

        recetas = new Recetas();
        match = new Match();
        perfil = new Perfil();
        verReceta = new VerReceta();

        // Configurar los listeners de los botones
        LinearLayout boton1 = findViewById(R.id.boton1);
        LinearLayout boton2 = findViewById(R.id.boton2);
        LinearLayout boton3 = findViewById(R.id.boton3);
        boton4 = findViewById(R.id.boton4);

        // Cargar el fragment inicial en el contenedor
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_fragments, recetas).commit();
        //boton1.setTextColor(getResources().getColor(R.color.colorPrimario));

        boton1.setOnClickListener(v -> {
            //boton1.setTextColor(getResources().getColor(R.color.colorPrimario));
            //boton2.setTextColor(getResources().getColor(R.color.black));
            //boton3.setTextColor(getResources().getColor(R.color.black));
            cambiarFragment(recetas);
        });
        boton2.setOnClickListener(v -> {
            //boton2.setTextColor(getResources().getColor(R.color.colorPrimario));
            //boton1.setTextColor(getResources().getColor(R.color.black));
            //boton3.setTextColor(getResources().getColor(R.color.black));
            cambiarFragment(match);
        });
        boton3.setOnClickListener(v -> {
            //boton3.setTextColor(getResources().getColor(R.color.colorPrimario));
            //boton2.setTextColor(getResources().getColor(R.color.black));
            //boton1.setTextColor(getResources().getColor(R.color.black));
            cambiarFragment(perfil);
        });
        //boton4.setOnClickListener(v -> cambiarFragment(verReceta));

        boton1.setBackgroundResource(R.drawable.boton_borde_superior);
        boton2.setBackgroundResource(R.drawable.boton_borde_superior);
        boton3.setBackgroundResource(R.drawable.boton_borde_superior);

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String nombreUsuario = acct.getDisplayName();
            String emailUsuario = acct.getEmail();
            Uri fotoUsuario = acct.getPhotoUrl();
            Perfil.setDatosUsuario(nombreUsuario, emailUsuario, fotoUsuario);
        }

        /*btn_cerrar_sesion.setOnClickListener(v -> {
            signOut();
        });*/
    }

    public void so() {
        finish();
        startActivity(new Intent(this, MainActivity.class));
    }

    public void cambiarFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_fragments, fragment).commit();
    }

}