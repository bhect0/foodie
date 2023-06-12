package tfg.hector.foodie.apirest.utils;

import com.google.gson.JsonArray;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    String JSON_FILE = "foodie/recetas.json";

    @GET(JSON_FILE)
    Call<JsonArray> getData();
}
