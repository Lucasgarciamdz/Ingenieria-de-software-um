package ar.um.edu.microblogging.services;

import static org.junit.jupiter.api.Assertions.*;

import ar.um.edu.microblogging.dto.entities.Etiqueta;
import ar.um.edu.microblogging.repositories.EtiquetaRepository;
import ar.um.edu.microblogging.repositories.MensajeRepository;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
public class EtiquetaServiceTest {
  @Autowired private EtiquetaService etiquetaService;

  @MockBean private EtiquetaRepository etiquetaRepository;

  @MockBean private MensajeRepository mensajeRepository;

  @Test
  public void getAll() {
    List<Etiqueta> expected = new ArrayList<>();
    List<Etiqueta> actual = etiquetaService.getAll();

    assertEquals(expected, actual);
  }
}
