package tfg.hector.foodie;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

public class Match extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.match, container, false);
        view.setBackgroundColor(Color.BLUE); // Establecer el color de fondo a azul

        // Mostrar un mensaje en pantalla
        Toast.makeText(getActivity(), "Se muestra el segundo fragmento", Toast.LENGTH_SHORT).show();

        return view;
    }







}