package ar.edu.utn.ba.ddsi.climalert.dominio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EvaluadorAlerta {

    private final double umbralTemperatura;
    private final int umbralHumedad;

    public EvaluadorAlerta(
            @Value("${climalert.alertas.umbral-temperatura}") double umbralTemperatura,
            @Value("${climalert.alertas.umbral-humedad}") int umbralHumedad
    ) {
        this.umbralTemperatura = umbralTemperatura;
        this.umbralHumedad = umbralHumedad;
    }

    public boolean esAlerta(RegistroClima registro) {
        return registro.temperatura() > umbralTemperatura
                && registro.humedad() > umbralHumedad;
    }
}
