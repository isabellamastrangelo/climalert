package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.eventos.AlertaMeteorologicaEvent;
import ar.edu.utn.frba.ddsi.climalert.models.entities.RegistroClimatico;
import ar.edu.utn.frba.ddsi.climalert.models.repositories.RegistrosClimaticosRepository;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class AlertasService {
  private final RegistrosClimaticosRepository repository;
  private final ApplicationEventPublisher eventPublisher;

  public AlertasService(RegistrosClimaticosRepository repository, ApplicationEventPublisher eventPublisher) {
    this.repository = repository;
    this.eventPublisher = eventPublisher;
  }

  public void evaluarCondicionesCriticas() {
    System.out.println("Iniciando evaluación de alertas meteorológicas...");

    RegistroClimatico ultimoRegistro = repository.obtenerUltimo();
    if (ultimoRegistro != null && esCondicionCritica(ultimoRegistro)) {
      System.out.println("Condición crítica detectada - Temp: " + ultimoRegistro.getTemperatura() + "- Humedad: "+ ultimoRegistro.getHumedad());

      AlertaMeteorologicaEvent evento = new AlertaMeteorologicaEvent(this, ultimoRegistro);
      eventPublisher.publishEvent(evento);
    } else {
      System.out.println("Clima estable. No se requieren acciones.");
    }

  }

  private boolean esCondicionCritica(RegistroClimatico registro) {
    return registro.getTemperatura() > 5/*35.0*/ && registro.getHumedad() > 60;
  }
}
