package ar.edu.utn.frba.ddsi.climalert.models.repositories;

import ar.edu.utn.frba.ddsi.climalert.models.entities.RegistroClimatico;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class RegistrosClimaticosRepository {
  private List<RegistroClimatico> registros = new ArrayList<>();

  public RegistroClimatico obtenerUltimo(){
    if(registros.isEmpty()) return null;
    return registros.getLast();
  }

  public void guardar(RegistroClimatico registroNuevo){
    registros.add(registroNuevo);
  }

  public List<RegistroClimatico> obtenerTodos(){
    return registros;
  }
}
