package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.dtos.WeatherApiResponseDTO;
import ar.edu.utn.frba.ddsi.climalert.models.RegistroClimaticoMapper;
import ar.edu.utn.frba.ddsi.climalert.models.entities.RegistroClimatico;
import ar.edu.utn.frba.ddsi.climalert.models.repositories.RegistrosClimaticosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class WeatherApiService {
  private final WeatherApiClient weatherApiClient;
  private final RegistroClimaticoMapper climaMapper;
  private final RegistrosClimaticosRepository climaRepository;

  public void obtenerYGuardar(){
    WeatherApiResponseDTO dto = weatherApiClient.obtener();
    RegistroClimatico entidad = climaMapper.toEntity(dto);
    System.out.println(entidad);
    climaRepository.guardar(entidad);
  }
}
