package com.sistema.login.builder;

import com.sistema.login.model.Usuario;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class UsuarioBuilder {

    private int id;
    private String username;
    private String correo;
    private String passwordHash;
    private LocalDateTime fechaRegistro;
    private String nombreCompleto;
    private LocalDate fechaNacimiento;

    // Constructor privado forzando el metodo crear()
    private UsuarioBuilder() {
        // Valores por defecto
        this.fechaRegistro = LocalDateTime.now();
    }

    //Inicia la construcción de un nuevo usuario
    public static UsuarioBuilder crear() {
        return new UsuarioBuilder();
    }

    //Establece el ID del usuario
    public UsuarioBuilder conId(int id) {
        this.id = id;
        return this;
    }

    //Establece el username
    public UsuarioBuilder conUsername(String username) {
        this.username = username;
        return this;
    }

    //El correo electrónico
    public UsuarioBuilder conCorreo(String correo) {
        this.correo = correo;
        return this;
    }

    //Establece el hash de la contraseña
    public UsuarioBuilder conPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
        return this;
    }

    //La fecha de registro
    public UsuarioBuilder conFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
        return this;
    }

    //Nombre completo
    public UsuarioBuilder conNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
        return this;
    }

    //Fecha de nacimiento
    public UsuarioBuilder conFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
        return this;
    }

    //Construye y retorna el objeto Usuario y hace validaciones de campos obligatorios
    public Usuario build() {
        validarCamposObligatorios();

        if (id > 0) {
            // Usuario existente con ID
            return new Usuario(id, username, correo, passwordHash,
                    fechaRegistro, nombreCompleto, fechaNacimiento);
        } else {
            // Nuevo usuario sin ID
            return new Usuario(username, correo, passwordHash,
                    nombreCompleto, fechaNacimiento);
        }
    }

    //Validar campos obligatorios se cumplan
    private void validarCamposObligatorios() {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalStateException("El username es obligatorio");
        }
        if (correo == null || correo.trim().isEmpty()) {
            throw new IllegalStateException("El correo es obligatorio");
        }
        if (passwordHash == null || passwordHash.trim().isEmpty()) {
            throw new IllegalStateException("El passwordHash es obligatorio");
        }
        if (nombreCompleto == null || nombreCompleto.trim().isEmpty()) {
            throw new IllegalStateException("El nombre completo es obligatorio");
        }
        if (fechaNacimiento == null) {
            throw new IllegalStateException("La fecha de nacimiento es obligatoria");
        }
    }
}