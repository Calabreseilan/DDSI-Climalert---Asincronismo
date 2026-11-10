package ar.edu.utn.ba.ddsi.climalert.servicio;

import ar.edu.utn.ba.ddsi.climalert.dominio.EvaluadorAlerta;
import ar.edu.utn.ba.ddsi.climalert.dominio.RegistroClima;
import ar.edu.utn.ba.ddsi.climalert.notificacion.MensajeAlerta;
import ar.edu.utn.ba.ddsi.climalert.notificacion.ProveedorEmail;
import ar.edu.utn.ba.ddsi.climalert.repositorio.RepositorioClima;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestorAlertasService {

    private final RepositorioClima repositorioClima;
    private final EvaluadorAlerta evaluadorAlerta;
    private final ProveedorEmail proveedorEmail;
    private final List<String> destinatarios;

    public GestorAlertasService(
            RepositorioClima repositorioClima,
            EvaluadorAlerta evaluadorAlerta,
            ProveedorEmail proveedorEmail,
            @Value("${climalert.notificaciones.destinatarios}") List<String> destinatarios
    ) {
        this.repositorioClima = repositorioClima;
        this.evaluadorAlerta = evaluadorAlerta;
        this.proveedorEmail = proveedorEmail;
        this.destinatarios = destinatarios;
    }

    @Scheduled(fixedRateString = "${climalert.alertas.intervalo-ms}")
    public void procesarAlertas() {
        Optional<RegistroClima> ultimo = repositorioClima.ultimo();

        if (ultimo.isEmpty()) {
            return;
        }

        RegistroClima registro = ultimo.get();

        if (!evaluadorAlerta.esAlerta(registro)) {
            return;
        }

        System.out.println("ALERTA detectada: " + registro);
        proveedorEmail.enviar(
                destinatarios,
                MensajeAlerta.asunto(registro),
                MensajeAlerta.cuerpoHtml(registro)
        );
    }
}
