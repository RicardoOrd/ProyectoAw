package com.roc.proyectofinalapp01.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.roc.proyectofinalapp01.dao.ParticipanteDAO;
import com.roc.proyectofinalapp01.model.Participante;
import jakarta.ws.rs.*; // Importa todas las anotaciones REST
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

// Ruta específica para este recurso: /api/participantes
@Path("/participantes")
public class ParticipanteResource {

    private final ParticipanteDAO dao = new ParticipanteDAO();
    
    // Configuración de GSON (Copiada de tu código original para mantener compatibilidad con fechas)
    private final Gson gson = new GsonBuilder()
        .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, typeOfSrc, context) -> 
            new JsonPrimitive(src.toString()))
        .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context) -> 
            LocalDate.parse(json.getAsString()))
        .create();

    // 1. GET - Obtener Todos
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerTodos() {
        try {
            List<Participante> lista = dao.obtenerTodos();
            String json = gson.toJson(lista);
            return Response.ok(json).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                           .entity("{\"error\": \"Error interno\"}").build();
        }
    }

    // 2. GET - Obtener por ID
    @GET
    @Path("/{id}") // El {id} se extrae automáticamente de la URL
    @Produces(MediaType.APPLICATION_JSON)
    public Response obtenerPorId(@PathParam("id") int id) {
        Participante p = dao.obtenerPorId(id);
        if (p != null) {
            return Response.ok(gson.toJson(p)).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("{}").build();
        }
    }

    // 3. POST - Crear nuevo
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response crear(String body) {
        try {
            // Convertimos el JSON que llega (body) a Objeto
            Participante nuevo = gson.fromJson(body, Participante.class);
            dao.insertar(nuevo);
            
            return Response.status(Response.Status.CREATED)
                           .entity("{\"mensaje\": \"Registrado correctamente\"}")
                           .build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("{\"error\": \"Datos inválidos\"}")
                           .build();
        }
    }

    // 4. PUT - Actualizar
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response actualizar(@PathParam("id") int id, String body) {
        try {
            Participante p = gson.fromJson(body, Participante.class);
            p.setId(id); // Forzamos el ID de la URL
            
            boolean exito = dao.actualizar(p);
            
            if (exito) {
                return Response.ok("{\"mensaje\": \"Actualizado correctamente\"}").build();
            } else {
                return Response.status(Response.Status.NOT_FOUND)
                               .entity("{\"error\": \"No existe ese ID\"}")
                               .build();
            }
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 5. DELETE - Eliminar
    @DELETE
    @Path("/{id}")
    public Response eliminar(@PathParam("id") int id) {
        if (dao.eliminar(id)) {
            return Response.ok().build(); // 200 OK
        } else {
            return Response.status(Response.Status.NOT_FOUND).build(); // 404 Not Found
        }
    }
}