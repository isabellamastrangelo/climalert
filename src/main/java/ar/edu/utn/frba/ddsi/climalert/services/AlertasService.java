package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.eventos.AlertaMeteorologicaEvent;
import ar.edu.utn.frba.ddsi.climalert.models.entities.RegistroClimatico;
import ar.edu.utn.frba.ddsi.climalert.models.repositories.RegistrosClimaticosRepository;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;

import java.util.logging.Logger;

public class AlertasService {
  private final RegistrosClimaticosRepository repository;
  private final ApplicationEventPublisher eventPublisher;

  public AlertasService(RegistrosClimaticosRepository repository, ApplicationEventPublisher eventPublisher) {
    this.repository = repository;
    this.eventPublisher = eventPublisher;
  }

  public void evaluarCondicionesCriticas() {
    System.out.print("Iniciando evaluación de alertas meteorológicas...");

    RegistroClimatico ultimoRegistro = repository.obtenerUltimo();
    if (esCondicionCritica(ultimoRegistro)) {
      System.out.print("Condición crítica detectada - Temp: " + ultimoRegistro.getTemperatura() + "- Humedad: "+ ultimoRegistro.getHumedad());

      AlertaMeteorologicaEvent evento = new AlertaMeteorologicaEvent(this, ultimoRegistro);
      eventPublisher.publishEvent(evento);
    } else {
      System.out.print("Clima estable. No se requieren acciones.");
    }

  }

  private boolean esCondicionCritica(RegistroClimatico registro) {
    return registro.getTemperatura() > 35.0 && registro.getHumedad() > 60;
  }
}
