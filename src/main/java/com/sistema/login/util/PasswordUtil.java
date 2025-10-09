package com.sistema.login.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Clase de utilidad para el manejo seguro de contraseñas
 * Usa BCrypt para encriptación (hashing)
 */
public class PasswordUtil {

    /**
     * Encripta una contraseña usando BCrypt
     * BCrypt genera automáticamente un "salt" único para cada contraseña
     *
     * @param password la contraseña en texto plano
     * @return el hash de la contraseña
     */
    public static String encriptarPassword(String password) {
        // BCrypt.hashpw genera un hash seguro con salt automático
        // El número 12 es el "costo" (work factor) - más alto = más seguro pero más lento
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    /**
     * Verifica si una contraseña coincide con un hash almacenado
     *
     * @param password la contraseña en texto plano a verificar
     * @param hashedPassword el hash almacenado en la base de datos
     * @return true si la contraseña es correcta, false en caso contrario
     */
    public static boolean verificarPassword(String password, String hashedPassword) {
        try {
            // BCrypt.checkpw compara de forma segura la contraseña con el hash
            return BCrypt.checkpw(password, hashedPassword);
        } catch (Exception e) {
            System.err.println("Error al verificar contraseña: " + e.getMessage());
            return false;
        }
    }

    /**
     * Valida la fortaleza de una contraseña
     * Requisitos:
     * - Mínimo 8 caracteres
     * - Al menos una mayúscula
     * - Al menos una minúscula
     * - Al menos un número
     * - Al menos un carácter especial
     *
     * @param password la contraseña a validar
     * @return true si cumple todos los requisitos, false en caso contrario
     */
    public static boolean esPasswordFuerte(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean tieneMayuscula = false;
        boolean tieneMinuscula = false;
        boolean tieneNumero = false;
        boolean tieneEspecial = false;

        // Caracteres especiales permitidos
        String caracteresEspeciales = "!@#$%^&*()_+-=[]{}|;:,.<>?";

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                tieneMayuscula = true;
            } else if (Character.isLowerCase(c)) {
                tieneMinuscula = true;
            } else if (Character.isDigit(c)) {
                tieneNumero = true;
            } else if (caracteresEspeciales.indexOf(c) >= 0) {
                tieneEspecial = true;
            }
        }

        return tieneMayuscula && tieneMinuscula && tieneNumero && tieneEspecial;
    }

    /**
     * Obtiene un mensaje descriptivo de los requisitos de contraseña faltantes
     *
     * @param password la contraseña a evaluar
     * @return mensaje con los requisitos faltantes
     */
    public static String obtenerMensajeValidacion(String password) {
        if (password == null || password.isEmpty()) {
            return "La contraseña no puede estar vacía";
        }

        StringBuilder mensaje = new StringBuilder("La contraseña debe contener:\n");
        boolean esFuerte = true;

        if (password.length() < 8) {
            mensaje.append("• Mínimo 8 caracteres\n");
            esFuerte = false;
        }

        if (!password.matches(".*[A-Z].*")) {
            mensaje.append("• Al menos una letra mayúscula\n");
            esFuerte = false;
        }

        if (!password.matches(".*[a-z].*")) {
            mensaje.append("• Al menos una letra minúscula\n");
            esFuerte = false;
        }

        if (!password.matches(".*[0-9].*")) {
            mensaje.append("• Al menos un número\n");
            esFuerte = false;
        }

        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{}|;:,.<>?].*")) {
            mensaje.append("• Al menos un carácter especial (!@#$%^&*...)\n");
            esFuerte = false;
        }

        return esFuerte ? "Contraseña válida" : mensaje.toString();
    }
}