package com.sistema.login.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Usuario {

    private int id;
    private String username;
    private String correo;
    private String passwordHash;
    private LocalDateTime fechaRegistro;
    private String nombreCompleto;
    private LocalDate fechaNacimiento;

    // Constructor vacío
    public Usuario() {}

    // Constructor completo (sin ID, para nuevos registros)
    public Usuario(String username, String correo, String passwordHash,
                   String nombreCompleto, LocalDate fechaNacimiento) {
        this.username = username;
        this.correo = correo;
        this.passwordHash = passwordHash;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Constructor completo (con ID, para usuarios existentes)
    public Usuario(int id, String username, String correo, String passwordHash,
                   LocalDateTime fechaRegistro, String nombreCompleto, LocalDate fechaNacimiento) {
        this.id = id;
        this.username = username;
        this.correo = correo;
        this.passwordHash = passwordHash;
        this.fechaRegistro = fechaRegistro;
        this.nombreCompleto = nombreCompleto;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public String getCorreo() {
        return correo;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", correo='" + correo + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaRegistro=" + fechaRegistro +
                '}';
    }
}