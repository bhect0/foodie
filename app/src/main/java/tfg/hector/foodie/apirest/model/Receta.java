package tfg.hector.foodie.apirest.model;

import java.util.List;

public class Receta {

    private String titulo;
    private String descripcion;
    private String foto;
    private List<String> pasos;
    private List<String> ingredientes;
    private String tiempoEstimado;





    public String getNombre() {
        return titulo;
    }

    public void setNombre(String nombre) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
