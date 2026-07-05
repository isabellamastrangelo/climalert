package ar.edu.utn.frba.ddsi.climalert.models.entities;

import ar.edu.utn.frba.ddsi.climalert.eventos.AlertaMeteorologicaEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.mail.javamail.JavaMailSender;

@AllArgsConstructor
public class NotificacionListener {
  private final String[] destinatarios = {"admin@clima.com", "emergencias@clima.com", "meteorologia@clima.com"};
  private final JavaMailSender mailSender;

  @EventListener
  public void onAlertaMeteorologica(AlertaMeteorologicaEvent event) {
    RegistroClimatico registro = event.getRegistroCritico();

    System.out.println("Recibí el evento de alerta. Enviando mails");

    String cuerpoMensaje = armarCuerpoMensaje(registro);

    for (String email : destinatarios) {
      mailSender.send(armarMail(email, cuerpoMensaje));
      System.out.println("Enviando mail a " + email + " con mensaje " + cuerpoMensaje);
    }
  }

  private String armarCuerpoMensaje(RegistroClimatico r) {
    return String.format("ALERTA CABA: Temperatura extrema de %.1f°C con %d%% de humedad.",
        r.getTemperatura(), r.getHumedad());
  }
}
