package ar.um.edu.microblogging.controllers;

import ar.um.edu.microblogging.dto.entities.Etiqueta;
import ar.um.edu.microblogging.dto.requests.NuevaEtiquetaDto;
import ar.um.edu.microblogging.dto.responses.BaseResponse;
import ar.um.edu.microblogging.services.EtiquetaService;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/etiquetas")
public class EtiquetaController {

  private final EtiquetaService etiquetaService;

  public EtiquetaController(EtiquetaService etiquetaService) {
    this.etiquetaService = etiquetaService;
  }

  @GetMapping("/{id}")
  public BaseResponse<Etiqueta> get(@PathVariable Long id) {
    Etiqueta etiqueta = etiquetaService.getById(id);
    return new BaseResponse<>("Etiqueta", etiqueta);
  }

  @GetMapping
  public BaseResponse<List<Etiqueta>> getAll() {
    List<Etiqueta> etiquetas = etiquetaService.getAll();
    return new BaseResponse<>("estas son las etiquetas", etiquetas);
  }

  @PostMapping
  public BaseResponse<Etiqueta> post(@RequestBody NuevaEtiquetaDto body) {
    Etiqueta etiqueta = etiquetaService.create(body);
    return new BaseResponse<>("Etiqueta creada", etiqueta);
  }

  @PutMapping("/{id}")
  public BaseResponse<Etiqueta> put(@PathVariable Long id, @RequestBody NuevaEtiquetaDto modificacion) {
    Etiqueta etiqueta = etiquetaService.update(id, modificacion);
    return new BaseResponse<>("Etiqueta actualizada", etiqueta);
  }

  @DeleteMapping("/{id}")
  public BaseResponse<String> delete(@PathVariable Long id) {
    boolean deleted = etiquetaService.delete(id);
    return new BaseResponse<>("Etiqueta", deleted ? "Deleted" : "Error deleting");
  }
}
