package ar.edu.utn.frba.ddsi.climalert.dtos;

import lombok.Data;

@Data
public class WeatherApiResponseDTO {
  private LocationDto location;
  private CurrentDto current;
}
