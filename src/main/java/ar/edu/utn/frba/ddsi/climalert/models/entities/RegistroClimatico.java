package ar.edu.utn.frba.ddsi.climalert.models.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RegistroClimatico {
  private String ciudad;
  private Double temperatura;
  private Integer humedad;
  private Double sensacionTermica;

  private LocalDateTime fechaLecturaProvider; // La fecha en la que el proveedor midió el clima
  private LocalDateTime fechaRegistroSistema; // Fecha y hora de cuándo tu sistema guardó el registro
}
