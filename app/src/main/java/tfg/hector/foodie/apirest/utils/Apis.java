package tfg.hector.foodie.apirest.utils;

public class Apis {
    public static final String URL_001 = "https://www.hectorsnb.com/";

    public static ApiService getApiRecetas(){
        return ClienteApi.getCliente(URL_001).create(ApiService.class);
    }

}
