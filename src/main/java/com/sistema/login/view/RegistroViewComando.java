package com.sistema.login.view;

import com.sistema.login.dao.UsuarioDAO;
import com.sistema.login.model.Usuario;
import com.sistema.login.util.PasswordUtil;
import com.sistema.login.util.ValidacionUtil;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class RegistroViewComando {
    private String NombreCompleto;
    private LocalDate FechaNacimiento;
    private String Username;
    private String Correo;
    private String Password;
    private String ConfirmarPassword;
    private UsuarioDAO usuarioDAO;

    public RegistroViewComando() {
        usuarioDAO = new UsuarioDAO();
    }

    public void show() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Crear Cuenta");
        System.out.println("Completa todos los campos para registrarte");

        // Campo Nombre Completo
        System.out.println("Ingrese el nombre completo del usuario");
        NombreCompleto = sc.nextLine().trim();

        // Campo Fecha de Nacimiento (validación de formato)
        LocalDate fecha = null;
        while (fecha == null) {
            System.out.println("Ingrese la fecha de nacimiento (YYYY-MM-DD):");
            String entrada = sc.nextLine().trim();
            try {
                fecha = LocalDate.parse(entrada);
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Use el formato YYYY-MM-DD.");
            }
        }
        FechaNacimiento = fecha;

        // Campo Username
        System.out.println("Ingrese el nombre corto para identificar");
        Username = sc.nextLine().trim();

        // Campo Correo
        System.out.println("Ingrese la correo del usuario");
        Correo = sc.nextLine().trim();

        // Campo Contraseña
        System.out.println("Ingrese el password");

        // Info contraseña
        String InfoPass = "Debe contener: mayúsculas, minúsculas, números y caracteres especiales";
        System.out.println(InfoPass);

        Password = sc.nextLine();

        // Campo Confirmar Contraseña
        System.out.println("Ingrese el confirmar password");
        ConfirmarPassword = sc.nextLine();

        // Llamar al proceso de registro
        realizarRegistro();
    }

    //Realiza el proceso de registro
    private void realizarRegistro() {
        // Obtener datos
        String nombreCompleto = ValidacionUtil.limpiarTexto(NombreCompleto);
        LocalDate fechaNacimiento = FechaNacimiento;
        String username = ValidacionUtil.limpiarTexto(Username);
        String correo = ValidacionUtil.limpiarTexto(Correo);
        String password = Password;
        String confirmarPassword = ConfirmarPassword;

        // Validar campos vacíos
        if (ValidacionUtil.estaVacio(nombreCompleto) || fechaNacimiento == null ||
                ValidacionUtil.estaVacio(username) || ValidacionUtil.estaVacio(correo) ||
                ValidacionUtil.estaVacio(password) || ValidacionUtil.estaVacio(confirmarPassword)) {
            System.out.println("Campos Incompletos, Por favor completa todos los campos");
            return;
        }

        // Validar nombre completo
        if (!ValidacionUtil.esNombreCompletoValido(nombreCompleto)) {
            System.out.println("Nombre Inválido, El nombre debe contener al menos nombre y apellido,\\nsolo letras y espacios");
            return;
        }

        // Validar username
        String errorUsername = ValidacionUtil.obtenerMensajeErrorUsername(username);
        if (errorUsername != null) {
            System.out.println("Username Inválido, " + errorUsername);
            return;
        }

        // Validar correo
        if (!ValidacionUtil.esCorreoValido(correo)) {
            System.out.println("Correo Inválido, Por favor ingresa un correo válido");
            return;
        }

        // Validar que las contraseñas coincidan
        if (!ValidacionUtil.passwordsCoinciden(password, confirmarPassword)) {
            System.out.println("Contraseñas no Coinciden, Las contraseñas deben ser idénticas");
            return;
        }

        // Validar fortaleza de contraseña
        if (!PasswordUtil.esPasswordFuerte(password)) {
            System.out.println("Contraseña Débil, " + PasswordUtil.obtenerMensajeValidacion(password));
            return;
        }

        // Verificar si el username ya existe
        if (usuarioDAO.existeUsername(username)) {
            System.out.println("Username en Uso, Este nombre de usuario ya está registrado");
            return;
        }

        // Verificar si el correo ya existe
        if (usuarioDAO.existeCorreo(correo)) {
            System.out.println("Correo en Uso, Este correo electrónico ya está registrado");
            return;
        }

        // Encriptar contraseña
        String passwordHash = PasswordUtil.encriptarPassword(password);

        // Crear usuario
        Usuario nuevoUsuario = new Usuario(username, correo, passwordHash,
                nombreCompleto, fechaNacimiento);

        // Insertar en base de datos
        boolean exito = usuarioDAO.insertarUsuario(nuevoUsuario);

        if (exito) {
            System.out.println("Registrado Correctamente,Tu cuenta ha sido creada.\\nAhora puedes iniciar sesión.");
            volverLogin();
        } else {
            System.out.println("Error, Hubo un problema al crear tu cuenta.\\nIntenta nuevamente.");
        }
    }
    private void volverLogin() {
        LoginViewComando loginViewComando = new LoginViewComando();
        loginViewComando.m_ingrDatos();
    }
}
