package ar.edu.utn.frba.ddsi.climalert.models;

import ar.edu.utn.frba.ddsi.climalert.dtos.WeatherApiResponseDTO;
import ar.edu.utn.frba.ddsi.climalert.models.entities.RegistroClimatico;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class RegistroClimaticoMapper {

  private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

  public RegistroClimatico toEntity(WeatherApiResponseDTO dto) {
    if (dto == null) {
      return null;
    }

    RegistroClimatico registro = new RegistroClimatico();

    if (dto.getLocation() != null) {
      registro.setCiudad(dto.getLocation().getName());
    }

    if (dto.getCurrent() != null) {
      registro.setTemperatura(dto.getCurrent().getTempC());
      registro.setHumedad(dto.getCurrent().getHumidity());
      registro.setSensacionTermica(dto.getCurrent().getFeelsLikeC());

      if (dto.getCurrent().getLastUpdated() != null) {
        registro.setFechaLecturaProvider(
            LocalDateTime.parse(dto.getCurrent().getLastUpdated(), formatter)
        );
      }
    }

    registro.setFechaRegistroSistema(LocalDateTime.now());

    return registro;
  }
}
