package ar.um.edu.microblogging.controllers;

import ar.um.edu.microblogging.dto.entities.Mensaje;
import ar.um.edu.microblogging.dto.requests.NuevoMensajeDto;
import ar.um.edu.microblogging.dto.responses.BaseResponse;
import ar.um.edu.microblogging.services.MensajeService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mensajes")
public class MensajeController {

  private final MensajeService mensajeService;

  public MensajeController(MensajeService mensajeService) {
    this.mensajeService = mensajeService;
  }

  @GetMapping("/{id}")
  public BaseResponse<Mensaje> get(@PathVariable Long id) {
    Mensaje mensaje = mensajeService.getById(id);

    return new BaseResponse<>("Mensaje", mensaje);
  }

  @GetMapping
  public BaseResponse<List<Mensaje>> getAll() {
    List<Mensaje> mensajes = mensajeService.getAll();
    return new BaseResponse<>("Se encontraron los siguientes mensajes", mensajes);
  }

  @PostMapping
  public BaseResponse<Mensaje> post(@RequestBody NuevoMensajeDto nuevoMensaje) {
    Mensaje mensaje = mensajeService.guardarMensaje(nuevoMensaje);
    return new BaseResponse<>("Mensaje", mensaje);
  }

  @PutMapping("/{id}")
  public BaseResponse<Mensaje> put(
      @PathVariable Long id, @RequestBody NuevoMensajeDto modificacion) {
    Mensaje mensaje = mensajeService.update(id, modificacion);
    return new BaseResponse<>("Mensaje", mensaje);
  }

  @DeleteMapping("/{id}")
  public BaseResponse<String> delete(@PathVariable Long id) {
    boolean deleted = mensajeService.delete(id);
    return new BaseResponse<>("Mensaje", deleted ? "Deleted" : "Error deleting");
  }
}
