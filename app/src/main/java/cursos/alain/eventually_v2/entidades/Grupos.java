package cursos.alain.eventually_v2.entidades;

import java.io.Serializable;

public class Grupos implements Serializable {
    private String nombre, intereses;
    private int imagenid;

    public Grupos (){}

    public Grupos(String nombre, String intereses, int imagenid) {
        this.nombre = nombre;
        this.intereses = intereses;
        this.imagenid = imagenid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getIntereses() {
        return intereses;
    }

    public void setIntereses(String intereses) {
        this.intereses = intereses;
    }

    public int getImagenid() {
        return imagenid;
    }

    public void setImagenid(int imagenid) {
        this.imagenid = imagenid;
    }
}
