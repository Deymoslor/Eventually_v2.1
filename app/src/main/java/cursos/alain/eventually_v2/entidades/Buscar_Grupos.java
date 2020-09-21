package cursos.alain.eventually_v2.entidades;

import java.io.Serializable;

public class Buscar_Grupos implements Serializable {

    private String Etiqueta;


    public String getEtiqueta() {
        return Etiqueta;
    }

    public void setEtiqueta(String etiqueta) {
        Etiqueta = etiqueta;
    }

}
