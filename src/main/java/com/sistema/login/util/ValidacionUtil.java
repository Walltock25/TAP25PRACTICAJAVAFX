package com.sistema.login.util;

import java.util.regex.Pattern;

/**
 * Clase de utilidad para validaciones de datos
 * Contiene métodos para validar formatos de correo, campos vacíos, etc.
 */
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

    /**
     * Válida si un correo electrónico tiene formato válido
     *
     * @param correo el correo a validar
     * @return true si el formato es válido, false en caso contrario
     */
    public static boolean esCorreoValido(String correo) {
        if (correo == null || correo.trim().isEmpty()) {
            return false;
        }
        return EMAIL_PATTERN.matcher(correo).matches();
    }

    /**
     * Válida si un campo de texto está vacío o solo contiene espacios
     *
     * @param texto el texto a validar
     * @return true si está vacío o es null, false si tiene contenido
     */
    public static boolean estaVacio(String texto) {
        return texto == null || texto.trim().isEmpty();
    }

    /**
     * Válida si un username tiene formato válido
     * Requisitos:
     * - Entre 3 y 20 caracteres
     * - Solo letras, números, guiones y guiones bajos
     * - Debe comenzar con una letra
     *
     * @param username el nombre de usuario a validar
     * @return true si es válido, false en caso contrario
     */
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

    /**
     * Obtiene un mensaje descriptivo del error de username
     *
     * @param username el username a evaluar
     * @return mensaje de error o null si es válido
     */
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

    /**
     * Válida si un nombre completo tiene formato válido
     * Requisitos:
     * - No estar vacío
     * - Contener al menos 2 palabras (nombre y apellido)
     * - Solo letras y espacios
     *
     * @param nombreCompleto el nombre a validar
     * @return true si es válido, false en caso contrario
     */
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

    /**
     * Valida que dos contraseñas coincidan
     *
     * @param password1 primera contraseña
     * @param password2 segunda contraseña (confirmación)
     * @return true si coinciden, false en caso contrario
     */
    public static boolean passwordsCoinciden(String password1, String password2) {
        if (password1 == null || password2 == null) {
            return false;
        }
        return password1.equals(password2);
    }

    /**
     * Limpia un string eliminando espacios al inicio y final
     *
     * @param texto el texto a limpiar
     * @return el texto limpio o string vacío si es null
     */
    public static String limpiarTexto(String texto) {
        return texto == null ? "" : texto.trim();
    }

    /**
     * Válida múltiples campos y retorna un mensaje de error consolidado
     *
     * @param campos array de strings con los valores de los campos
     * @param nombres array de strings con los nombres de los campos
     * @return mensaje de error o null si todo es válido
     */
    public static String validarCamposVacios(String[] campos, String[] nombres) {
        StringBuilder errores = new StringBuilder();

        for (int i = 0; i < campos.length; i++) {
            if (estaVacio(campos[i])) {
                errores.append("• ").append(nombres[i]).append(" no puede estar vacío\n");
            }
        }

        return errores.length() > 0 ? errores.toString() : null;
    }
}