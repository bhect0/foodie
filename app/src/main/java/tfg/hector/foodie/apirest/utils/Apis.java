package tfg.hector.foodie.apirest.utils;

import tfg.hector.foodie.apirest.model.Receta;

public class Apis {
    public static final String URL_001 = "http://localhost";

    public static RecetaService getRecetaService(){
        return Cliente.getCliente(URL_001).create(RecetaService.class);
    }

}
