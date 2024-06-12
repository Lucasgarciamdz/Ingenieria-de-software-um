package ar.um.edu.microblogging.controllers;


import ar.um.edu.microblogging.dto.entities.Etiqueta;
import ar.um.edu.microblogging.dto.entities.Mensaje;
import ar.um.edu.microblogging.dto.entities.Usuario;
import ar.um.edu.microblogging.dto.responses.BaseResponse;
import ar.um.edu.microblogging.services.EtiquetaService;
import ar.um.edu.microblogging.services.MensajeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        Mensaje mensaje = mensajeService.getById(id);
        return new BaseResponse<>("Mensaje", mensaje);
    }



    @Override
    @GetMapping
    public List<BaseResponse<Mensaje>> getAll() {
        List<Mensaje> mensajes = mensajeService.getAll();
        return mensajes.stream()
                .map(mensaje -> new BaseResponse<>("Mensaje", mensaje))
                .collect(Collectors.toList());

    }



    @Override
    @PostMapping
    public BaseResponse<Mensaje> post(@RequestBody Mensaje body) {
        Mensaje mensaje = mensajeService.save(body);
        return new BaseResponse<>("Mensaje", mensaje);}


    @Override
    @PutMapping("/{id}")
    public BaseResponse<Mensaje> put(@PathVariable Long id, @RequestBody Mensaje modificacion) {
        modificacion.setId(id);
        Mensaje mensaje = mensajeService.update(modificacion);
        return new BaseResponse<>("Mensaje", mensaje);
    }

    @Override
    @DeleteMapping("/{id}")
    public BaseResponse<String> delete(@PathVariable Long id) {
        boolean deleted = mensajeService.delete(id);
        return new BaseResponse<>("Mensaje", deleted ? "Deleted" : "Error deleting");
}
}
