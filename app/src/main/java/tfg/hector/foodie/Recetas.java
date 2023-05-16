package tfg.hector.foodie;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.gson.JsonObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tfg.hector.foodie.apirest.utils.ApiService;
import tfg.hector.foodie.apirest.utils.Apis;

public class Recetas extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment TODO: documentacion de para que sirve inflate
        View view = inflater.inflate(R.layout.recetas, container, false);

        TextView tc_debug = view.findViewById(R.id.debug);

        /*Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.44/") // ip de mi pc
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);*/

        ApiService as = Apis.getApiRecetas();
        getRecetas(tc_debug, as);
        return view;

    }

    private void getRecetas(TextView tc_debug, ApiService as) {
        Call<JsonObject> call = as.getData();
        call.enqueue(new Callback<JsonObject>() {
            @Override
            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                if (response.isSuccessful()) {
                    JsonObject data = response.body();
                    tc_debug.setText("data: " + data);
                } else {
                    tc_debug.setText("error !isSuccessful");
                    // Maneja la respuesta de error
                }
            }

            @Override
            public void onFailure(Call<JsonObject> call, Throwable t) {
                tc_debug.setText(t.getMessage());
            }
        });
    }

}