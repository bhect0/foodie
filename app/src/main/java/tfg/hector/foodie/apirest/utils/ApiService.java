package tfg.hector.foodie.apirest.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import tfg.hector.foodie.apirest.model.Receta;

public interface ApiService { // interfaz para consumir el servicio web

    /*@GET("listar/")
    Call<List<Receta>> getRecetas();*/

    String JSON_FILE = "foodie/recetas.json";

    @GET(JSON_FILE)
    Call<JsonArray> getData();
}
