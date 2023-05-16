package tfg.hector.foodie.apirest.utils;

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import tfg.hector.foodie.apirest.model.Receta;

public interface ApiService { // interfaz para consumir el servicio web

    /*@GET("listar/")
    Call<List<Receta>> getRecetas();*/

    String JSON_FILE = "pruebas/recetas.json";

    @GET(JSON_FILE)
    Call<JsonObject> getData();
}
