package ar.um.edu.microblogging.dto.entities;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;

public class UsuarioSerializer extends StdSerializer<Usuario> {

  public UsuarioSerializer() {
    this(null);
  }

  public UsuarioSerializer(Class<Usuario> t) {
    super(t);
  }

  @Override
  public void serialize(Usuario usuario, JsonGenerator gen, SerializerProvider provider)
      throws IOException {
    gen.writeStartObject();
    gen.writeStringField("identity", usuario.getId() + "_" + usuario.getNombreUsuario());
    gen.writeStringField("nombreUsuario", usuario.getNombreUsuario());
    gen.writeStringField("email", usuario.getEmail());
    // Optionally serialize foto as a base64 encoded string
    // if (usuario.getFoto() != null) {
    //     gen.writeStringField("foto", Base64.getEncoder().encodeToString(usuario.getFoto()));
    // }
    gen.writeStringField("nombreCompleto", usuario.getNombreCompleto());
    gen.writeStringField("descripcion", usuario.getDescripcion());

    // Serialize seguidores with depth 1
    if (usuario.getSeguidores() != null) {
      gen.writeArrayFieldStart("seguidores");
      for (Usuario seguidor : usuario.getSeguidores()) {
        gen.writeStartObject();
        gen.writeStringField("nombreUsuario", seguidor.getNombreUsuario());
        gen.writeStringField("email", seguidor.getEmail());
        // Add any other fields you want to include for each seguidor
        gen.writeEndObject();
      }
      gen.writeEndArray();
    }

    // Serialize seguidos with depth 1
    if (usuario.getSeguidos() != null) {
      gen.writeArrayFieldStart("seguidos");
      for (Usuario seguido : usuario.getSeguidos()) {
        gen.writeStartObject();
        gen.writeStringField("nombreUsuario", seguido.getNombreUsuario());
        gen.writeStringField("email", seguido.getEmail());
        // Add any other fields you want to include for each seguido
        gen.writeEndObject();
      }
      gen.writeEndArray();
    }

    gen.writeEndObject();
  }
}
