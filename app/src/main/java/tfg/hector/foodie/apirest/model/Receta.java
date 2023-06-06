package tfg.hector.foodie.apirest.model;

import java.io.Serializable;
import java.util.List;

public class Receta implements Serializable {

    private String titulo;
    private String descripcion;
    private String foto;
    private List<String> pasos;
    private List<String> ingredientes;
    private String tiempo_estimado;

    public Receta(String titulo, String descripcion, String foto, List<String> pasos, List<String> ingredientes, String tiempo_estimado) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.pasos = pasos;
        this.ingredientes = ingredientes;
        this.tiempo_estimado = tiempo_estimado;
    }


    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<String> getPasos() {
        return pasos;
    }

    public void setPasos(List<String> pasos) {
        this.pasos = pasos;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<String> getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(List<String> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getTiempoEstimado() {
        return tiempo_estimado;
    }

    public void setTiempoEstimado(String tiempo_estimado) {
        this.tiempo_estimado = tiempo_estimado;
    }

}
