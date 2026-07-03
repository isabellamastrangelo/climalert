package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.dtos.WeatherApiResponseDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Data
@Component
public class WeatherApiClient {
  private final RestTemplate restTemplate;

  @Value("${weather.api.url}")
  private String apiUrl;

  @Value("${weather.api.key}")
  private String apiKey;

  @Value("${weather.api.location}")
  private String location;

  public WeatherApiResponseDTO obtener() {
    String url = apiUrl + "/current.json?key={key}&q={q}&aqi={aqi}";
    Map<String, String> parametros = Map.of(
        "key", apiKey,
        "q", location,
        "aqi", "no"
    );

    return restTemplate.getForObject(
        url,
        WeatherApiResponseDTO.class,
        parametros
    );
  }
}

