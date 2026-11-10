package ar.edu.utn.ba.ddsi.climalert.dominio;

import java.time.LocalDateTime;

// fechaHora es el momento de nuestra consulta, no el de la medición del proveedor.
public record RegistroClima(
        String ubicacion,
        double temperatura,
        int humedad,
        double sensacionTermica,
        double vientoKph,
        String descripcion,
        LocalDateTime fechaHora
) {
}
