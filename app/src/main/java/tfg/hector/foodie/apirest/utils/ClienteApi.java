package tfg.hector.foodie.apirest.utils;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClienteApi {

    public static Retrofit getCliente(String url) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    }


    //GitHubService service = retrofit.create(GitHubService.class);

}
