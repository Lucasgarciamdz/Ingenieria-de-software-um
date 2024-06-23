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
    gen.writeStringField("id", String.valueOf(usuario.getId()));
    gen.writeStringField("nombreUsuario", usuario.getNombreUsuario());
    gen.writeStringField("email", usuario.getEmail());
    if (usuario.getSeguido() != null) {
      gen.writeBooleanField("seguido", usuario.getSeguido());
    } else {
      gen.writeBooleanField("seguido", false);
    }
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

    // Corrected serialization for mensajes with depth 1 including missing attributes

    if (usuario.getMensajes() != null) {
      gen.writeArrayFieldStart("mensajes");
      for (Mensaje mensaje : usuario.getMensajes()) {
        gen.writeStartObject();
        gen.writeStringField("id", mensaje.getId().toString());
        gen.writeStringField("texto", mensaje.getTexto());
        gen.writeStringField("fechaPublicacion", mensaje.getFechaPublicacion().toString());
        if (mensaje.getAutor() != null) {
          gen.writeObjectFieldStart("autor");
          gen.writeStringField("nombreUsuario", mensaje.getAutor().getNombreUsuario());
          gen.writeStringField("email", mensaje.getAutor().getEmail());
          gen.writeEndObject();
        }
        if (mensaje.getUsuarioDestinatario() != null) {
          gen.writeObjectFieldStart("usuarioDestinatario");
          gen.writeStringField(
              "nombreUsuario", mensaje.getUsuarioDestinatario().getNombreUsuario());
          gen.writeStringField("email", mensaje.getUsuarioDestinatario().getEmail());
          gen.writeEndObject();
        }
        if (!mensaje.getReposts().isEmpty()) {
          gen.writeArrayFieldStart("reposts");
          for (Usuario repost : mensaje.getReposts()) {
            gen.writeStartObject();
            gen.writeStringField("nombreUsuario", repost.getNombreUsuario());
            gen.writeStringField("email", repost.getEmail());
            gen.writeEndObject();
          }
          gen.writeEndArray();
        }
        if (!mensaje.getEtiquetas().isEmpty()) {
          gen.writeArrayFieldStart("etiquetas");
          for (Etiqueta etiqueta : mensaje.getEtiquetas()) {
            gen.writeStartObject();
            gen.writeStringField("nombre", etiqueta.getNombre());
            gen.writeEndObject();
          }
          gen.writeEndArray();
        }
        gen.writeEndObject();
      }
      gen.writeEndArray();
    }

    gen.writeEndObject();
  }
}
