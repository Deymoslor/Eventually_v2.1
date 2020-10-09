package cursos.alain.eventually_v2.entidades;

import java.io.Serializable;

public class Grupos1 implements Serializable {

    private String Nombre_Grupo;
    private String Etiqueta;
    private String Descripcion;
    private String IdGrupo;

    public String getNombre_Grupo() {
        return Nombre_Grupo;
    }

    public void setNombre_Grupo(String nombre_Grupo) {
        Nombre_Grupo = nombre_Grupo;
    }

    public String getEtiqueta() {
        return Etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        Etiqueta = etiqueta;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getIdGrupo() {
        return IdGrupo;
    }

    public void setIdGrupo(String idGrupo) {
        IdGrupo = idGrupo;
    }
}
