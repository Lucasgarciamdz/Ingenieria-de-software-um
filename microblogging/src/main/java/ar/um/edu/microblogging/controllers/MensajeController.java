package ar.um.edu.microblogging.controllers;


import ar.um.edu.microblogging.dto.entities.Etiqueta;
import ar.um.edu.microblogging.dto.entities.Mensaje;
import ar.um.edu.microblogging.dto.responses.BaseResponse;
import ar.um.edu.microblogging.services.EtiquetaService;
import ar.um.edu.microblogging.services.MensajeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mensajes")
public class MensajeController implements BaseController<Mensaje>{


    private final MensajeService mensajeService;

    public MensajeController(MensajeService mensajeService){
        this.mensajeService = mensajeService;
    }


    @Override
    @GetMapping("/{id}")
    public BaseResponse<Mensaje> get(@PathVariable Long id) {
        return null;
    }

    @Override
    @GetMapping
    public List<BaseResponse<Mensaje>> getAll() {
        return List.of();
    }

    @Override
    @PostMapping("/{id}")
    public BaseResponse<Mensaje> post(@PathVariable Long id, @RequestBody Mensaje body) {
        return null;
    }

    @Override
    @PutMapping("/{id}")
    public BaseResponse<Mensaje> put(@PathVariable Long id, @RequestBody Mensaje modificacion) {
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public BaseResponse<String> delete(@PathVariable Long id) {
        return null;
    }
}
