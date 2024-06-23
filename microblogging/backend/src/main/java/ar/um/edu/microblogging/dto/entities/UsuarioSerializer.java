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
  public void serialize(Usuario usuario, JsonGenerator gen, SerializerProvider provider) throws IOException {
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
    // Handle seguido, mensajes, seguidores, seguidos, mensajePrivado as needed
    // For example, to include seguido if not null:
//    if (usuario.getSeguido() != null) {
//      gen.writeBooleanField("seguido", usuario.getSeguido());
//    }
    // Similar for other fields, considering appropriate handling for collections
    gen.writeEndObject();
  }
}