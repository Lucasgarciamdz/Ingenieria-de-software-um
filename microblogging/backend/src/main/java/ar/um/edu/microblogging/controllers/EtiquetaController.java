package ar.um.edu.microblogging.controllers;

import ar.um.edu.microblogging.dto.entities.Etiqueta;
import ar.um.edu.microblogging.dto.responses.BaseResponse;
import ar.um.edu.microblogging.services.EtiquetaService;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/etiquetas")
public class EtiquetaController implements BaseController<Etiqueta> {

    private final EtiquetaService etiquetaService;

    public EtiquetaController(EtiquetaService etiquetaService){
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
    public List<BaseResponse<Etiqueta>> getAll() {
        List<Etiqueta> etiquetas = etiquetaService.getAll();
        return etiquetas.stream()
                        .map(etiqueta -> new BaseResponse<Etiqueta>("Etiqueta", etiqueta))
                        .collect(Collectors.toList());
    }

    @Override
    @PostMapping
    public BaseResponse<Etiqueta> post(@RequestBody Etiqueta body) {
        Etiqueta etiqueta = etiquetaService.save(body);
        return new BaseResponse<>("Etiqueta", etiqueta);
    }

 @Override
@PutMapping("/{id}")
public BaseResponse<Etiqueta> put(@PathVariable Long id, @RequestBody Etiqueta modificacion) {
  Etiqueta etiquetaExistente = etiquetaService.getById(id);
  if (etiquetaExistente == null) {
    return new BaseResponse<>("Etiqueta no encontrada", null);
  }

  // Actualizar solo los campos que no son nulos
  if (modificacion.getNombre() != null) {
    etiquetaExistente.setNombre(modificacion.getNombre());
  }
  if (modificacion.getDelMomento() != null) {
    etiquetaExistente.setDelMomento(modificacion.getDelMomento());
  }

  Etiqueta etiquetaActualizada = etiquetaService.update(etiquetaExistente);
  return new BaseResponse<>("Etiqueta", etiquetaActualizada);
}


    @Override
    @DeleteMapping("/{id}")
    public BaseResponse<String> delete(@PathVariable Long id) {
        boolean deleted = etiquetaService.delete(id);
        return new BaseResponse<>("Etiqueta", deleted ? "Deleted" : "Error deleting");
    }
}