package tfg.hector.foodie.apirest.utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import tfg.hector.foodie.apirest.model.Receta;

public interface RecetaService { // interfaz para consumir el servicio web

    @GET("listar/")
    Call<List<Receta>> getRecetas();

}
