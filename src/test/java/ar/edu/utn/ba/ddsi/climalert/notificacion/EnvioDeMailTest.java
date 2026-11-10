package ar.edu.utn.ba.ddsi.climalert.notificacion;

import ar.edu.utn.ba.ddsi.climalert.dominio.RegistroClima;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

// Manda un mail real, por eso solo corre si se pide a propósito:
//   ./mvnw test -Dtest=EnvioDeMailTest -Dclimalert.test.mail=true
@SpringBootTest
@EnabledIfSystemProperty(named = "climalert.test.mail", matches = "true")
class EnvioDeMailTest {

    @Autowired
    private ProveedorEmail proveedorEmail;

    @Value("${climalert.notificaciones.destinatarios}")
    private List<String> destinatarios;

    @Test
    void enviaUnMailDeAlertaReal() {
        RegistroClima registro = new RegistroClima(
                "Buenos Aires", 38.5, 75, 42.0, 12.0, "Sunny", LocalDateTime.now());

        assertDoesNotThrow(() -> proveedorEmail.enviar(
                destinatarios,
                "[PRUEBA] " + MensajeAlerta.asunto(registro),
                MensajeAlerta.cuerpoHtml(registro)));
    }
}
