package ar.edu.utn.frba.ddsi.climalert.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CurrentDto {
  @JsonProperty("temp_c")
  private Double tempC;
  private Integer humidity;
  @JsonProperty("last_updated")
  private String lastUpdated;
  @JsonProperty("feelslike_c")
  private Double feelsLikeC;
}
