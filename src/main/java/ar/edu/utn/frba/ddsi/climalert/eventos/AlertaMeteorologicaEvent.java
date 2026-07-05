package ar.edu.utn.frba.ddsi.climalert.eventos;

import ar.edu.utn.frba.ddsi.climalert.models.entities.RegistroClimatico;
import org.springframework.context.ApplicationEvent;

public class AlertaMeteorologicaEvent extends ApplicationEvent {

  private final RegistroClimatico registroCritico;

  public AlertaMeteorologicaEvent(Object source, RegistroClimatico registroCritico) {
    super(source);
    this.registroCritico = registroCritico;
  }

  public RegistroClimatico getRegistroCritico() {
    return registroCritico;
  }
}
