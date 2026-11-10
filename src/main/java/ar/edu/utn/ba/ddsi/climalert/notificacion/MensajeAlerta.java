package ar.edu.utn.ba.ddsi.climalert.notificacion;

import ar.edu.utn.ba.ddsi.climalert.dominio.RegistroClima;

import java.time.format.DateTimeFormatter;

public final class MensajeAlerta {

    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    private MensajeAlerta() {
    }

    public static String asunto(RegistroClima registro) {
        return "Alerta climática en " + registro.ubicacion();
    }

    public static String cuerpoHtml(RegistroClima registro) {
        return """
                <h2>Alerta climática en %s</h2>
                <p>Se detectaron condiciones climáticas peligrosas.</p>
                <ul>
                  <li><b>Temperatura:</b> %.1f °C</li>
                  <li><b>Humedad:</b> %d %%</li>
                  <li><b>Sensación térmica:</b> %.1f °C</li>
                  <li><b>Viento:</b> %.1f km/h</li>
                  <li><b>Condición:</b> %s</li>
                  <li><b>Registrado:</b> %s</li>
                </ul>
                """.formatted(
                registro.ubicacion(),
                registro.temperatura(),
                registro.humedad(),
                registro.sensacionTermica(),
                registro.vientoKph(),
                registro.descripcion(),
                registro.fechaHora().format(FORMATO_FECHA)
        );
    }
}
