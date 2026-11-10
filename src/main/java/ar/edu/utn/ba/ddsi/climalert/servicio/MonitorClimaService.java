package ar.edu.utn.ba.ddsi.climalert.servicio;

import ar.edu.utn.ba.ddsi.climalert.dominio.ProveedorClima;
import ar.edu.utn.ba.ddsi.climalert.dominio.RegistroClima;
import ar.edu.utn.ba.ddsi.climalert.repositorio.RepositorioClima;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MonitorClimaService {

    private final ProveedorClima proveedorClima;
    private final RepositorioClima repositorioClima;

    public MonitorClimaService(ProveedorClima proveedorClima, RepositorioClima repositorioClima) {
        this.proveedorClima = proveedorClima;
        this.repositorioClima = repositorioClima;
    }

    @Scheduled(fixedRateString = "${climalert.monitoreo.intervalo-ms}")
    public void registrarClimaActual() {
        RegistroClima registro = proveedorClima.obtenerClimaActual();
        repositorioClima.guardar(registro);
        System.out.println("Clima registrado: " + registro);
    }
}
