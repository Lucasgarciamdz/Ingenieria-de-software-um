package ar.um.edu.microblogging.controllers;

import ar.um.edu.microblogging.dto.dtos.MensajeDTO;
import ar.um.edu.microblogging.dto.entities.Mensaje;
import ar.um.edu.microblogging.dto.responses.BaseResponse;
import ar.um.edu.microblogging.services.MensajeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mensajes")
public class MensajeController {

    private final MensajeService mensajeService;

    public MensajeController(MensajeService mensajeService) {
        this.mensajeService = mensajeService;
    }

    @GetMapping("/{id}")
    public BaseResponse<MensajeDTO> get(@PathVariable Long id) {
        MensajeDTO dto = mensajeService.getById(id);
        return new BaseResponse<>("Mensaje", dto);
    }

    @GetMapping
    public BaseResponse<Set<Mensaje>> getAll() {
        Set<Mensaje> dtos = mensajeService.getAll();
        return new BaseResponse<>("Se encontraron los siguientes mensajes", dtos);
    }

    @PostMapping
    public BaseResponse<MensajeDTO> post(@RequestBody MensajeDTO body) {
        MensajeDTO dto = mensajeService.save(body);
        return new BaseResponse<>("Mensaje", dto);
    }

    @PutMapping("/{id}")
    public BaseResponse<MensajeDTO> put(@PathVariable Long id, @RequestBody MensajeDTO modificacion) {
        modificacion.setId(id);
        MensajeDTO dto = mensajeService.update(modificacion);
        return new BaseResponse<>("Mensaje", dto);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<String> delete(@PathVariable Long id) {
        boolean deleted = mensajeService.delete(id);
        return new BaseResponse<>("Mensaje", deleted ? "Deleted" : "Error deleting");
    }
}
