package ar.edu.utn.ba.ddsi.climalert.repositorio;

import ar.edu.utn.ba.ddsi.climalert.dominio.RegistroClima;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
public class RepositorioClimaEnMemoria implements RepositorioClima {

    // CopyOnWriteArrayList y no ArrayList: las dos tareas programadas acceden a la lista.
    private final List<RegistroClima> registros = new CopyOnWriteArrayList<>();

    @Override
    public void guardar(RegistroClima registro) {
        registros.add(registro);
    }

    @Override
    public Optional<RegistroClima> ultimo() {
        return registros.isEmpty()
                ? Optional.empty()
                : Optional.of(registros.get(registros.size() - 1));
    }

    @Override
    public List<RegistroClima> historial() {
        return List.copyOf(registros);
    }
}
