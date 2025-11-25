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

    // 1. CREAR (INSERT) - Ya lo tenías, se mantiene igual
    public void insertar(Participante p) {
        String sql = "INSERT INTO participantes(nombre, email, fecha_nacimiento, experiencia, "
                   + "intereses, acepto_terminos, comentarios) VALUES(?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, p.getNombre());
            pstmt.setString(2, p.getEmail());
            pstmt.setString(3, p.getFechaNacimiento().toString());
            pstmt.setString(4, p.getExperiencia());
            
            String interesesStr = (p.getIntereses() != null) ? String.join(",", p.getIntereses()) : "";
            pstmt.setString(5, interesesStr);
            
            pstmt.setBoolean(6, p.isAceptoTerminos());
            pstmt.setString(7, p.getComentarios());

            pstmt.executeUpdate();
            System.out.println("DAO: Participante insertado exitosamente.");

        } catch (SQLException e) {
            System.err.println("Error al insertar participante: " + e.getMessage());
        }
    }
    
    // 2. LEER TODOS (SELECT ALL) - Ya lo tenías
    public List<Participante> obtenerTodos() {
        List<Participante> participantes = new ArrayList<>();
        String sql = "SELECT * FROM participantes";

        try (Connection conn = ConexionDB.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                participantes.add(mapearParticipante(rs));
            }

        } catch (SQLException e) {
            System.err.println("Error al obtener participantes: " + e.getMessage());
        }
        return participantes;
    }

    // 3. LEER UNO POR ID (SELECT ONE) - ¡NUEVO!
    public Participante obtenerPorId(int id) {
        Participante p = null;
        String sql = "SELECT * FROM participantes WHERE id = ?";

        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    p = mapearParticipante(rs);
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al obtener participante por ID: " + e.getMessage());
        }
        return p;
    }

    // 4. ACTUALIZAR (UPDATE) - ¡NUEVO!
    public boolean actualizar(Participante p) {
        String sql = "UPDATE participantes SET nombre=?, email=?, fecha_nacimiento=?, experiencia=?, "
                   + "intereses=?, acepto_terminos=?, comentarios=? WHERE id=?";
        
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, p.getNombre());
            pstmt.setString(2, p.getEmail());
            pstmt.setString(3, p.getFechaNacimiento().toString());
            pstmt.setString(4, p.getExperiencia());
            
            String interesesStr = (p.getIntereses() != null) ? String.join(",", p.getIntereses()) : "";
            pstmt.setString(5, interesesStr);
            
            pstmt.setBoolean(6, p.isAceptoTerminos());
            pstmt.setString(7, p.getComentarios());
            pstmt.setInt(8, p.getId()); // Importante: el ID va al final en el WHERE

            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar participante: " + e.getMessage());
            return false;
        }
    }

    // 5. ELIMINAR (DELETE) - ¡NUEVO!
    public boolean eliminar(int id) {
        String sql = "DELETE FROM participantes WHERE id = ?";
        try (Connection conn = ConexionDB.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, id);
            int filasAfectadas = pstmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al eliminar participante: " + e.getMessage());
            return false;
        }
    }

    // Método auxiliar para no repetir código al leer del ResultSet
    private Participante mapearParticipante(ResultSet rs) throws SQLException {
        Participante p = new Participante();
        p.setId(rs.getInt("id"));
        p.setNombre(rs.getString("nombre"));
        p.setEmail(rs.getString("email"));
        
        // Manejo seguro de fechas
        String fechaStr = rs.getString("fecha_nacimiento");
        if (fechaStr != null && !fechaStr.isEmpty()) {
            p.setFechaNacimiento(LocalDate.parse(fechaStr));
        }

        p.setExperiencia(rs.getString("experiencia"));
        
        String interesesStr = rs.getString("intereses");
        if (interesesStr != null && !interesesStr.isEmpty()) {
            p.setIntereses(interesesStr.split(","));
        } else {
            p.setIntereses(new String[0]);
        }

        p.setAceptoTerminos(rs.getBoolean("acepto_terminos"));
        p.setComentarios(rs.getString("comentarios"));
        return p;
    }
}