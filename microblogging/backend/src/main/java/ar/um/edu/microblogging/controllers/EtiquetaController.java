package ar.um.edu.microblogging.controllers;

import ar.um.edu.microblogging.dto.dtos.EtiquetaDTO;
import ar.um.edu.microblogging.dto.responses.BaseResponse;
import ar.um.edu.microblogging.services.EtiquetaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/etiquetas")
public class EtiquetaController {

    private final EtiquetaService etiquetaService;

    public EtiquetaController(EtiquetaService etiquetaService) {
        this.etiquetaService = etiquetaService;
    }

    @GetMapping("/{id}")
    public BaseResponse<EtiquetaDTO> get(@PathVariable Long id) {
        EtiquetaDTO dto = etiquetaService.getById(id);
        return new BaseResponse<>("Etiqueta", dto);
    }

    @GetMapping
    public List<BaseResponse<EtiquetaDTO>> getAll() {
        List<EtiquetaDTO> etiquetas = etiquetaService.getAll();
        return etiquetas.stream()
                .map(dto -> new BaseResponse<>("Etiqueta", dto))
                .collect(Collectors.toList());
    }

    @PostMapping
    public BaseResponse<EtiquetaDTO> post(@RequestBody EtiquetaDTO body) {
        EtiquetaDTO dto = etiquetaService.save(body);
        return new BaseResponse<>("Etiqueta", dto);
    }

    @PutMapping("/{id}")
    public BaseResponse<EtiquetaDTO> put(@PathVariable Long id, @RequestBody EtiquetaDTO modificacion) {
        modificacion.setId(id);
        EtiquetaDTO dto = etiquetaService.update(modificacion);
        return new BaseResponse<>("Etiqueta", dto);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<String> delete(@PathVariable Long id) {
        boolean deleted = etiquetaService.delete(id);
        return new BaseResponse<>("Etiqueta", deleted ? "Deleted" : "Error deleting");
    }
}
