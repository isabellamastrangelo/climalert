package ar.edu.utn.frba.ddsi.climalert.schedulers;

import ar.edu.utn.frba.ddsi.climalert.services.AlertasService;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AlertaScheduler {
  private final AlertasService service;

  @Scheduled(cron = "0 */1 * * * *") // cada 5 min
  public void buscarAlertaClimatica() {
    System.out.println("cron task de alertas");
    service.evaluarCondicionesCriticas();
  }
}
