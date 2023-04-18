package tfg.hector.foodie;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class Recetas extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.recetas, container, false);
        view.setBackgroundColor(Color.BLUE); // Establecer el color de fondo a azul

        // Mostrar un mensaje en pantalla
        Toast.makeText(getActivity(), "Recetas", Toast.LENGTH_SHORT).show();

        return view;
    }

}