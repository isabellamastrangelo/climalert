package ar.edu.utn.frba.ddsi.climalert.eventos;

import ar.edu.utn.frba.ddsi.climalert.models.entities.RegistroClimatico;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.EventListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class NotificacionListener {
  private final List<String> destinatarios = List.of("imastrangelo@frba.utn.edu.ar", "isabellamastrangelo05@gmail.com");//{"admin@clima.com", "emergencias@clima.com", "meteorologia@clima.com"};
  private final JavaMailSender mailSender;
  private String ubicacion;

  public NotificacionListener(JavaMailSender mailSender, @Value("${weather.api.location}") String ubicacion) {
    this.mailSender = mailSender;
    this.ubicacion = ubicacion;
  }

  @EventListener
  public void onAlertaMeteorologica(AlertaMeteorologicaEvent event) {
    RegistroClimatico registro = event.getRegistroCritico();

    System.out.println("Recibí el evento de alerta. Enviando mails");

    for (String email : destinatarios) {
      mailSender.send(armarMail(email, registro));
      System.out.println("Enviando mail a " + email + " con mensaje " + registro);
    }
  }

  private String armarCuerpoMensaje(RegistroClimatico r) {
    return String.format("ALERTA %s: Temperatura extrema de %.1f°C con %d%% de humedad.", ubicacion,
        r.getTemperatura(), r.getHumedad());
  }

  private SimpleMailMessage armarMail(String destinatario, RegistroClimatico registro) {
    SimpleMailMessage mensaje = new SimpleMailMessage();
    String cuerpo = armarCuerpoMensaje(registro);

    mensaje.setFrom("${spring.mail.username}");
    mensaje.setTo(destinatario);
    mensaje.setSubject("ALERTA METEOROLÓGICA CRÍTICA");
    mensaje.setText(cuerpo);

    return mensaje;
  }
}
