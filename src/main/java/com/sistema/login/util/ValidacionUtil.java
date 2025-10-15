package com.sistema.login.util;

import java.util.regex.Pattern;

public class ValidacionUtil {

    // Patrón regex para validar formato de correo electrónico
    // Explicación del patrón:
    // ^[A-Za-z0-9+_.-]+ : inicio con letras, números o caracteres permitidos
    // @ : debe contener arroba
    // [A-Za-z0-9.-]+ : dominio
    // \.[A-Z|a-z]{2,}$ : punto y extensión de al menos 2 caracteres

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Z|a-z]{2,}$"
    );

    public static boolean esCorreoValido(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(correo).matches();
    }

    public static boolean estaVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    public static boolean esUsernameValido(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }

        // Verificar longitud
        if (username.length() < 3 || username.length() > 20) {
            return false;
        }

        // Verificar que comience con letra
        if (!Character.isLetter(username.charAt(0))) {
            return false;
        }

        // Verificar que solo contenga caracteres permitidos
        return username.matches("^[A-Za-z][A-Za-z0-9_-]*$");
    }
    public static String obtenerMensajeErrorUsername(String username) {
        if (estaVacio(username)) {
            return "El nombre de usuario no puede estar vacío";
        }

        if (username.length() < 3) {
            return "El nombre de usuario debe tener al menos 3 caracteres";
        }

        if (username.length() > 20) {
            return "El nombre de usuario no puede tener más de 20 caracteres";
        }

        if (!Character.isLetter(username.charAt(0))) {
            return "El nombre de usuario debe comenzar con una letra";
        }

        if (!username.matches("^[A-Za-z][A-Za-z0-9_-]*$")) {
            return "El nombre de usuario solo puede contener letras, números, guiones y guiones bajos";
        }

        return null; // Es válido
    }

    public static boolean esNombreCompletoValido(String nombreCompleto) {
        if (estaVacio(nombreCompleto)) {
            return false;
        }

        // Eliminar espacios extras
        String nombre = nombreCompleto.trim().replaceAll("\\s+", " ");

        // Verificar que tenga al menos 2 palabras
        String[] palabras = nombre.split(" ");
        if (palabras.length < 2) {
            return false;
        }

        // Verificar que solo contenga letras y espacios
        return nombre.matches("^[A-Za-záéíóúÁÉÍÓÚñÑ ]+$");
    }

    public static boolean passwordsCoinciden(String password1, String password2) {
        if (password1 == null || password2 == null) {
            return false;
        }
        return password1.equals(password2);
    }

    public static String limpiarTexto(String texto) {
        return texto == null ? "" : texto.trim();
    }
}