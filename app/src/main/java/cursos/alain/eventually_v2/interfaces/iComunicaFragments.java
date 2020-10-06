package cursos.alain.eventually_v2.interfaces;

import cursos.alain.eventually_v2.entidades.Buscar_Grupos;
import cursos.alain.eventually_v2.entidades.Grupos1;

public interface iComunicaFragments {

    public void iniciarMisgrupos();

    public void iniciarPerfil();

    public void iniciarBuscarGrupos();

    public void iniciarViewPager();

    public void enviarEtiqueta(Buscar_Grupos buscar_grupos);

    public void enviarGrupos1(Grupos1 grupos1);
}
