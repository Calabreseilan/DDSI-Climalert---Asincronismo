package ar.edu.utn.ba.ddsi.climalert.notificacion;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResendAdapter implements ProveedorEmail {

    private final Resend resend;
    private final String origen;

    public ResendAdapter(
            @Value("${resend.api-key}") String apiKey,
            @Value("${climalert.notificaciones.origen}") String origen
    ) {
        this.resend = new Resend(apiKey);
        this.origen = origen;
    }

    @Override
    public void enviar(List<String> destinatarios, String asunto, String cuerpoHtml) {
        CreateEmailOptions mail = CreateEmailOptions.builder()
                .from(origen)
                .to(destinatarios)
                .subject(asunto)
                .html(cuerpoHtml)
                .build();

        try {
            resend.emails().send(mail);
            System.out.println("Mail de alerta enviado a " + destinatarios);
        } catch (ResendException e) {
            throw new IllegalStateException(
                    "No se pudo enviar el mail de alerta: " + e.getMessage(), e);
        }
    }
}
