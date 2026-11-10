package ar.edu.utn.ba.ddsi.climalert.notificacion;

import java.util.List;

public interface ProveedorEmail {

    void enviar(List<String> destinatarios, String asunto, String cuerpoHtml);
}
