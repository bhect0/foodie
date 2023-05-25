package tfg.hector.foodie.apirest.utils;

import tfg.hector.foodie.apirest.model.Receta;

public class Apis { // obtener servicio de apis
    public static final String URL_001 = "https://www.hectorsnb.com/";

    public static ApiService getApiRecetas(){
        return ClienteApi.getCliente(URL_001).create(ApiService.class);
    }

}
