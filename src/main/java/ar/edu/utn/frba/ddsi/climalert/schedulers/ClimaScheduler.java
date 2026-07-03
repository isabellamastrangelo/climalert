package ar.edu.utn.frba.ddsi.climalert.schedulers;

import ar.edu.utn.frba.ddsi.climalert.services.WeatherApiService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ClimaScheduler {
  private final WeatherApiService service;

  @Scheduled(cron = "0 */2 * * * *") // cada 5 min
  public void obtenerDatosClimaticos() {
    System.out.println("cron task");
    service.obtenerYGuardar();
  }

}
