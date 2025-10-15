package com.sistema.login.util;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordUtil {

    public static String encriptarPassword(String password) {
        // BCrypt.hashpw genera un hash seguro con salt automático
        // El número 12 es el "costo" (work factor) - más alto = más seguro pero más lento
        return BCrypt.hashpw(password, BCrypt.gensalt(12));
    }

    public static boolean verificarPassword(String password, String hashedPassword) {
        try {
            // BCrypt.checkpw compara de forma segura la contraseña con el hash
            return BCrypt.checkpw(password, hashedPassword);
        } catch (Exception e) {
            System.err.println("Error al verificar contraseña: " + e.getMessage());
            return false;
        }
    }
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