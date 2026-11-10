package ar.edu.utn.ba.ddsi.climalert.weatherapi;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RespuestaWeatherApi(Location location, Current current) {

    public record Location(String name) {}

    public record Current(
            @JsonProperty("temp_c") double tempC,
            int humidity,
            @JsonProperty("feelslike_c") double feelslikeC,
            @JsonProperty("wind_kph") double windKph,
            Condition condition
    ) {}

    public record Condition(String text) {}
}
