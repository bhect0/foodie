package tfg.hector.foodie;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

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

        ImageButton boton1 = findViewById(R.id.boton1);
        ImageButton boton2 = findViewById(R.id.boton2);
        ImageButton boton3 = findViewById(R.id.boton3);
        boton4 = findViewById(R.id.boton4);

        LinearLayout l1 = findViewById(R.id.layout1);
        LinearLayout l2 = findViewById(R.id.layout2);
        LinearLayout l3 = findViewById(R.id.layout3);

        TextView tv1 = findViewById(R.id.tv1);
        TextView tv2 = findViewById(R.id.tv2);
        TextView tv3 = findViewById(R.id.tv3);

        tv1.setTextColor(getResources().getColor(R.color.colorPrimario));
        tv2.setTextColor(getResources().getColor(R.color.black));
        tv3.setTextColor(getResources().getColor(R.color.black));
        l1.setBackgroundResource(R.drawable.boton_borde_superior);
        l2.setBackgroundResource(R.drawable.nada);
        l3.setBackgroundResource(R.drawable.nada);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_fragments, recetas).commit();

        l1.setOnClickListener(v -> boton1.performClick());
        l2.setOnClickListener(v -> boton2.performClick());
        l3.setOnClickListener(v -> boton3.performClick());

        boton1.setOnClickListener(v -> {
            tv1.setTextColor(getResources().getColor(R.color.colorPrimario));
            tv2.setTextColor(getResources().getColor(R.color.black));
            tv3.setTextColor(getResources().getColor(R.color.black));
            l1.setBackgroundResource(R.drawable.boton_borde_superior);
            l2.setBackgroundResource(R.drawable.nada);
            l3.setBackgroundResource(R.drawable.nada);
            cambiarFragment(recetas);
        });
        boton2.setOnClickListener(v -> {
            tv2.setTextColor(getResources().getColor(R.color.colorPrimario));
            tv1.setTextColor(getResources().getColor(R.color.black));
            tv3.setTextColor(getResources().getColor(R.color.black));
            l2.setBackgroundResource(R.drawable.boton_borde_superior);
            l1.setBackgroundResource(R.drawable.nada);
            l3.setBackgroundResource(R.drawable.nada);
            cambiarFragment(match);
        });
        boton3.setOnClickListener(v -> {
            tv3.setTextColor(getResources().getColor(R.color.colorPrimario));
            tv2.setTextColor(getResources().getColor(R.color.black));
            tv1.setTextColor(getResources().getColor(R.color.black));
            l3.setBackgroundResource(R.drawable.boton_borde_superior);
            l2.setBackgroundResource(R.drawable.nada);
            l1.setBackgroundResource(R.drawable.nada);
            cambiarFragment(perfil);
        });
        //boton4.setOnClickListener(v -> cambiarFragment(verReceta));



        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);

        /*GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String nombreUsuario = acct.getDisplayName();
            String emailUsuario = acct.getEmail();
            Uri fotoUsuario = acct.getPhotoUrl();
            //Perfil.setDatosUsuario(nombreUsuario, emailUsuario, fotoUsuario);
        }*/

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