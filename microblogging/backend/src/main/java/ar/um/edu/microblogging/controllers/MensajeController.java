package ar.um.edu.microblogging.controllers;

import ar.um.edu.microblogging.dto.dtos.MensajeDto;
import ar.um.edu.microblogging.dto.entities.Mensaje;
import ar.um.edu.microblogging.dto.requests.RepostMensajeDto;
import ar.um.edu.microblogging.dto.responses.BaseResponse;
import ar.um.edu.microblogging.services.MensajeService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/mensajes")
public class MensajeController implements BaseController<Mensaje, MensajeDto> {

  private final MensajeService mensajeService;

  public MensajeController(MensajeService mensajeService) {
    this.mensajeService = mensajeService;
  }

  @Override
  @GetMapping("/{id}")
  public BaseResponse<Mensaje> get(@PathVariable Long id) {
    Mensaje mensaje = mensajeService.getById(id);

    return new BaseResponse<>("Mensaje", mensaje);
  }

  @Override
  @GetMapping
  public BaseResponse<List<Mensaje>> getAll() {
    List<Mensaje> mensajes = mensajeService.getAll();
    return new BaseResponse<>("Se encontraron los siguientes mensajes", mensajes);
  }

  @Override
  @PostMapping
  public BaseResponse<Mensaje> post(@RequestBody MensajeDto nuevoMensaje) {
    Mensaje mensaje = mensajeService.save(nuevoMensaje);
    return new BaseResponse<>("Mensaje", mensaje);
  }

  @Override
  @PutMapping("/{id}")
  public BaseResponse<Mensaje> put(@PathVariable Long id, @RequestBody MensajeDto modificacion) {
    Mensaje mensaje = mensajeService.update(id, modificacion);
    return new BaseResponse<>("Mensaje", mensaje);
  }

  @Override
  @DeleteMapping("/{id}")
  public BaseResponse<String> delete(@PathVariable Long id) {
    boolean deleted = mensajeService.delete(id);
    return new BaseResponse<>("Mensaje", deleted ? "Deleted" : "Error deleting");
  }

  @GetMapping("/usuario/{id}")
  public BaseResponse<List<Mensaje>> getMensajesByUsuario(@PathVariable Long id) {
    List<Mensaje> mensajes = mensajeService.getMensajesByUsuario(id);
    mensajes.addAll(mensajeService.getMensajesPrivados(id));
    return new BaseResponse<>("Se encontraron los siguientes mensajes", mensajes);
  }

  @PostMapping("/repost")
  public BaseResponse<Mensaje> post(@RequestBody RepostMensajeDto repostMensaje) {
    Mensaje mensaje = mensajeService.repost(repostMensaje);
    return new BaseResponse<>("Mensaje", mensaje);
  }

  @GetMapping("/etiqueta/{nombre}")
  public BaseResponse<List<Mensaje>> getMensajesByEtiqueta(@PathVariable String nombre) {
    List<Mensaje> mensajes = mensajeService.getMensajesByEtiqueta("#"+nombre);
    return new BaseResponse<>("Se encontraron los siguientes mensajes", mensajes);
  }

  @GetMapping("/followers/{id}")
  public BaseResponse<List<Mensaje>> getMensajesDeFollowers(@PathVariable Long id) {
    List<Mensaje> mensajes = mensajeService.getMensajesDeFollowers(id);
    return new BaseResponse<>("Mensaje de followers", mensajes);
  }

  @GetMapping("/privados/{id}")
  public BaseResponse<List<Mensaje>> getMensajesPrivados(@PathVariable Long id) {
    List<Mensaje> mensajes = mensajeService.getMensajesPrivados(id);
    return new BaseResponse<>("Mensajes privados", mensajes);
  }
}
