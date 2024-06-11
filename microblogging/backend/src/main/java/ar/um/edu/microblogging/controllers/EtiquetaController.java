package ar.um.edu.microblogging.controllers;


import ar.um.edu.microblogging.dto.entities.Etiqueta;
import ar.um.edu.microblogging.dto.responses.BaseResponse;
import org.springframework.web.bind.annotation.*;
import ar.um.edu.microblogging.services.EtiquetaService;

import java.util.List;

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
        return null;
    }

    @Override
    @GetMapping
    public List<BaseResponse<Etiqueta>> getAll() {
        return List.of();
    }

    @Override
    @PostMapping
    public BaseResponse<Etiqueta> post(@RequestBody Etiqueta body) {
        return null;
    }

    @Override
    @PutMapping("/{id}")
    public BaseResponse<Etiqueta> put(@PathVariable Long id, @RequestBody Etiqueta modificacion) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public BaseResponse<String> delete(@PathVariable Long id) {
        return null;
    }

}
