package com.roc.proyectofinalapp01.servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import com.roc.proyectofinalapp01.dao.ParticipanteDAO;
import com.roc.proyectofinalapp01.model.Participante;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet("/api/participantes/*")
public class ParticipanteAPI extends HttpServlet {

    private ParticipanteDAO dao;
    private Gson gson;

    @Override
    public void init() {
        dao = new ParticipanteDAO();
        
        // --- AQUÍ ESTÁ EL ARREGLO MÁGICO ---
        // Le enseñamos a Gson a manejar LocalDate como texto (String)
        gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, (JsonSerializer<LocalDate>) (src, typeOfSrc, context) -> 
                new JsonPrimitive(src.toString())) // Convertir Fecha -> Texto
            .registerTypeAdapter(LocalDate.class, (JsonDeserializer<LocalDate>) (json, typeOfT, context) -> 
                LocalDate.parse(json.getAsString())) // Convertir Texto -> Fecha
            .create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        String pathInfo = req.getPathInfo();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<Participante> lista = dao.obtenerTodos();
                String json = gson.toJson(lista);
                resp.getWriter().print(json);
            } else {
                String idStr = pathInfo.substring(1);
                int id = Integer.parseInt(idStr);
                Participante p = dao.obtenerPorId(id);
                
                if (p != null) {
                    resp.getWriter().print(gson.toJson(p));
                } else {
                    resp.setStatus(404);
                    resp.getWriter().print("{}");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            resp.getWriter().print("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        
        if (pathInfo != null && pathInfo.length() > 1) {
            try {
                int id = Integer.parseInt(pathInfo.substring(1));
                if (dao.eliminar(id)) { // Asegúrate que tu DAO tenga este método
                    resp.setStatus(200);
                } else {
                    resp.setStatus(404);
                }
            } catch (Exception e) {
                resp.setStatus(400);
            }
        } else {
            resp.setStatus(400);
        }
    }
    
    // Agrega doPost y doPut aquí si los necesitas para el registro y edición
}