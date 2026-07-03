package ar.edu.utn.frba.ddsi.climalert.models.repositories;

import ar.edu.utn.frba.ddsi.climalert.models.entities.RegistroClimatico;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RegistrosClimaticosRepository {
  private List<RegistroClimatico> registros = new ArrayList<>();
}
