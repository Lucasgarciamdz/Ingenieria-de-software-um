package ar.um.edu.microblogging.controllers;

import ar.um.edu.microblogging.dto.dtos.EtiquetaDto;
import ar.um.edu.microblogging.dto.entities.Etiqueta;
import ar.um.edu.microblogging.dto.responses.BaseResponse;
import ar.um.edu.microblogging.services.EtiquetaService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/etiquetas")
public class EtiquetaController implements BaseController<Etiqueta, EtiquetaDto> {

  private final EtiquetaService etiquetaService;

  public EtiquetaController(EtiquetaService etiquetaService) {
    this.etiquetaService = etiquetaService;
  }

  @Override
  @GetMapping("/{id}")
  public BaseResponse<Etiqueta> get(@PathVariable Long id) {
    Etiqueta etiqueta = etiquetaService.getById(id);
    return new BaseResponse<>("Etiqueta", etiqueta);
  }

  @Override
  @GetMapping
  public BaseResponse<List<Etiqueta>> getAll() {
    List<Etiqueta> etiquetas = etiquetaService.getAll();
    return new BaseResponse<>("estas son las etiquetas", etiquetas);
  }

  @Override
  @PostMapping()
  public BaseResponse<Etiqueta> post(@RequestBody EtiquetaDto body) {
    Etiqueta etiqueta = etiquetaService.save(body);
    return new BaseResponse<>("Etiqueta creada con exito", etiqueta);
  }

  @Override
  @PutMapping("/{id}")
  public BaseResponse<Etiqueta> put(@PathVariable Long id, @RequestBody EtiquetaDto modificacion) {
    Etiqueta etiquetaActualizada = etiquetaService.update(id, modificacion);
    return new BaseResponse<>("Etiqueta", etiquetaActualizada);
  }

  @Override
  @DeleteMapping("/{id}")
  public BaseResponse<String> delete(@PathVariable Long id) {
    boolean deleted = etiquetaService.delete(id);
    return new BaseResponse<>("Etiqueta", deleted ? "Deleted" : "Error deleting");
  }

  @GetMapping("/temasMomento")
  public BaseResponse<List<Etiqueta>> getTemasMomento(
      @RequestParam(required = false) Integer cantidad) {
    List<Etiqueta> etiquetas = etiquetaService.getTemasMomento(cantidad);
    return new BaseResponse<>("Estos son los temas del momento", etiquetas);
  }
}
