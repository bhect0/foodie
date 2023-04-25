package tfg.hector.foodie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class Aplicacion extends FragmentActivity {

    // Fragmentos
    private Recetas recetas;
    private Match match;
    private Perfil perfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aplicacion);

        recetas = new Recetas();
        match = new Match();
        perfil = new Perfil();

        // Cargar el fragment inicial en el contenedor
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_fragments, recetas).commit();

        // Configurar los listeners de los botones
        Button boton1 = findViewById(R.id.boton1);
        Button boton2 = findViewById(R.id.boton2);
        Button boton3 = findViewById(R.id.boton3);

        boton1.setOnClickListener(v -> cambiarFragment(recetas, boton1));
        boton2.setOnClickListener(v -> cambiarFragment(match, boton2));
        boton3.setOnClickListener(v -> cambiarFragment(perfil, boton3));

        boton1.setBackgroundResource(R.drawable.boton_borde_superior);
        boton2.setBackgroundResource(R.drawable.boton_borde_superior);
        boton3.setBackgroundResource(R.drawable.boton_borde_superior);
    }

    private void cambiarFragment(Fragment fragment, Button boton) {
        //boton.setBackgroundResource(R.drawable.boton_borde_superior);
        getSupportFragmentManager().beginTransaction().replace(R.id.contenedor_fragments, fragment).commit();
    }

}