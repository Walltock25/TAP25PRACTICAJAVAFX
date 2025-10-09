package com.sistema.login.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Clase que representa un usuario del sistema
 * Cada instancia almacena los datos de un usuario
 */
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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