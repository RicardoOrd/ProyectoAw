package com.roc.proyectofinalapp01.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexionDB {

    private static final String URL = "jdbc:sqlite:C:/sqlite_db/devsummit.db";

    /**
     * Método estático para obtener una conexión a la base de datos.
     * * @return un objeto Connection
     */
    public static Connection getConnection() {
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            
            conn = DriverManager.getConnection(URL);
            
            System.out.println("Conexion a SQLite establecida exitosamente.");
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontro el driver de SQLite.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos SQLite.");
            e.printStackTrace();
        }
        return conn;
    }
}