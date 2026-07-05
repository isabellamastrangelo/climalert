package ar.edu.utn.frba.ddsi.climalert.services;

import ar.edu.utn.frba.ddsi.climalert.eventos.AlertaMeteorologicaEvent;
import ar.edu.utn.frba.ddsi.climalert.models.entities.RegistroClimatico;
import ar.edu.utn.frba.ddsi.climalert.models.repositories.RegistrosClimaticosRepository;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AlertasService {
  private final RegistrosClimaticosRepository repository;
  private final ApplicationEventPublisher eventPublisher;
  private LocalDateTime fechaUltimaAlerta;

  public AlertasService(RegistrosClimaticosRepository repository, ApplicationEventPublisher eventPublisher) {
    this.repository = repository;
    this.eventPublisher = eventPublisher;
  }

  public void evaluarCondicionesCriticas() {
    System.out.println("Iniciando evaluación de alertas meteorológicas...");
    RegistroClimatico ultimoRegistro = repository.obtenerUltimo();
    if (ultimoRegistro != null && esCondicionCritica(ultimoRegistro)) {
      System.out.println("Condición crítica detectada - Temp: " + ultimoRegistro.getTemperatura() + "- Humedad: "+ ultimoRegistro.getHumedad());

      if (fechaUltimaAlerta != null && fechaUltimaAlerta.equals(ultimoRegistro.getFechaRegistroSistema())) {
        System.out.println("El registro actual ya fue alertado. Ignorando..."); // Para no procesar varias veces el mismo registro
      }
      else {
        fechaUltimaAlerta = ultimoRegistro.getFechaRegistroSistema();
        AlertaMeteorologicaEvent evento = new AlertaMeteorologicaEvent(this, ultimoRegistro);
        eventPublisher.publishEvent(evento);
      }
    } else {
      System.out.println("Clima estable (o no hay datos que analizar)");
    }

  }

  private boolean esCondicionCritica(RegistroClimatico registro) {
    return registro.getTemperatura() > 35.0 && registro.getHumedad() > 60;
  }
}
