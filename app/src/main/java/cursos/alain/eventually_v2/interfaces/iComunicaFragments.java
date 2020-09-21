package cursos.alain.eventually_v2.interfaces;

import cursos.alain.eventually_v2.entidades.Buscar_Grupos;

public interface iComunicaFragments {

    public void iniciarMisgrupos();
    public void iniciarPerfil();
    public void iniciarBuscarGrupos();
    public void enviarEtiqueta(Buscar_Grupos buscar_grupos);
}
