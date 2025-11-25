package com.roc.proyectofinalapp01.dao;

import com.roc.proyectofinalapp01.model.Participante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ParticipanteDAO {

    public void insertar(Participante p) {
        String sql = "INSERT INTO participantes(nombre, email, fecha_nacimiento, experiencia, "
                   + "intereses, acepto_terminos, comentarios) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, p.getNombre());
            pstmt.setString(2, p.getEmail());
            pstmt.setString(3, p.getFechaNacimiento().toString());
            pstmt.setString(4, p.getExperiencia());
            
            String interesesStr = String.join(",", p.getIntereses());
            pstmt.setString(5, interesesStr);
            
            pstmt.setBoolean(6, p.isAceptoTerminos());
            pstmt.setString(7, p.getComentarios());

            pstmt.executeUpdate();
            System.out.println("DAO: Participante insertado exitosamente.");

        } catch (SQLException e) {
            System.err.println("Error al insertar participante: " + e.getMessage());
        }
    }
    
    public List<Participante> obtenerTodos() {
        List<Participante> participantes = new ArrayList<>();
        String sql = "SELECT * FROM participantes";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Participante p = new Participante();
                p.setId(rs.getInt("id"));
                p.setNombre(rs.getString("nombre"));
                p.setEmail(rs.getString("email"));
                
                p.setFechaNacimiento(LocalDate.parse(rs.getString("fecha_nacimiento")));
                
                p.setExperiencia(rs.getString("experiencia"));
                
                String[] intereses = rs.getString("intereses").split(",");
                p.setIntereses(intereses);
                
                p.setAceptoTerminos(rs.getBoolean("acepto_terminos"));
                p.setComentarios(rs.getString("comentarios"));
                
                participantes.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener participantes: " + e.getMessage());
        }
        
        System.out.println("DAO: Se obtuvieron " + participantes.size() + " participantes.");
        return participantes;
    }
}