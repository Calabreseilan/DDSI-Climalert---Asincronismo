package ar.edu.utn.ba.ddsi.climalert.weatherapi;

import ar.edu.utn.ba.ddsi.climalert.dominio.ProveedorClima;
import ar.edu.utn.ba.ddsi.climalert.dominio.RegistroClima;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.time.LocalDateTime;

@Component
public class WeatherApiAdapter implements ProveedorClima {

    private final RestClient restClient;
    private final String apiKey;
    private final String ubicacion;

    public WeatherApiAdapter(
            @Value("${weatherapi.base-url}") String baseUrl,
            @Value("${weatherapi.api-key}") String apiKey,
            @Value("${weatherapi.ubicacion}") String ubicacion
    ) {
        this.restClient = RestClient.create(baseUrl);
        this.apiKey = apiKey;
        this.ubicacion = ubicacion;
    }

    @Override
    public RegistroClima obtenerClimaActual() {
        RespuestaWeatherApi respuesta = restClient.get()
                .uri(uri -> uri.path("/current.json")
                        .queryParam("key", apiKey)
                        .queryParam("q", ubicacion)
                        .build())
                .retrieve()
                .body(RespuestaWeatherApi.class);

        return new RegistroClima(
                respuesta.location().name(),
                respuesta.current().tempC(),
                respuesta.current().humidity(),
                respuesta.current().feelslikeC(),
                respuesta.current().windKph(),
                respuesta.current().condition().text(),
                LocalDateTime.now()
        );
    }
}
