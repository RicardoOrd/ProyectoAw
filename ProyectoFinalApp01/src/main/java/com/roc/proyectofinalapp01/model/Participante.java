package com.roc.proyectofinalapp01.model;

import java.time.LocalDate; 


public class Participante {

    private int id; 
    private String nombre;
    private String email;
    private LocalDate fechaNacimiento; 
    private String experiencia;
    private String[] intereses; 
    private boolean aceptoTerminos; 
    private String comentarios;

    // --- Constructores ---
    
    /**
     * Constructor vacío 
     */
    public Participante() {
    }

    /**
     * Constructor para insertar nuevos participantes 
     */
    public Participante(String nombre, String email, LocalDate fechaNacimiento, String experiencia, String[] intereses, boolean aceptoTerminos, String comentarios) {
        this.nombre = nombre;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.experiencia = experiencia;
        this.intereses = intereses;
        this.aceptoTerminos = aceptoTerminos;
        this.comentarios = comentarios;
    }
    
 
    public Participante(int id, String nombre, String email, LocalDate fechaNacimiento, 
           String experiencia, String[] intereses, boolean aceptoTerminos, String comentarios) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.experiencia = experiencia;
        this.intereses = intereses;
        this.aceptoTerminos = aceptoTerminos;
        this.comentarios = comentarios;
    }

    // --- Getters y Setters ---
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(String experiencia) {
        this.experiencia = experiencia;
    }

    public String[] getIntereses() {
        return intereses;
    }

    public void setIntereses(String[] intereses) {
        this.intereses = intereses;
    }

    public boolean isAceptoTerminos() {
        return aceptoTerminos;
    }

    public void setAceptoTerminos(boolean aceptoTerminos) {
        this.aceptoTerminos = aceptoTerminos;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
}