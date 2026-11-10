package ar.edu.utn.ba.ddsi.climalert.repositorio;

import ar.edu.utn.ba.ddsi.climalert.dominio.RegistroClima;

import java.util.List;
import java.util.Optional;

public interface RepositorioClima {

    void guardar(RegistroClima registro);

    Optional<RegistroClima> ultimo();

    List<RegistroClima> historial();
}
