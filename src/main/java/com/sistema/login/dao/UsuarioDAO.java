package com.sistema.login.dao;

import com.sistema.login.database.DatabaseConnection;
import com.sistema.login.model.Usuario;
import java.sql.*;

public class UsuarioDAO {

    public boolean insertarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (username, correo, password_hash, nombre_completo, fecha_nacimiento) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Asignar valores a los parámetros (los ? en la consulta)
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getCorreo());
            stmt.setString(3, usuario.getPasswordHash());
            stmt.setString(4, usuario.getNombreCompleto());
            stmt.setDate(5, Date.valueOf(usuario.getFechaNacimiento()));

            // Ejecutar la inserción
            int filasAfectadas = stmt.executeUpdate();
            return filasAfectadas > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    public boolean existeUsername(String username) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE username = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0; // Si el conteo es mayor a 0, existe
            }

        } catch (SQLException e) {
            System.err.println("Error al verificar username: " + e.getMessage());
        }
        return false;
    }

    public boolean existeCorreo(String correo) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE correo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt(1) > 0;
            }

        } catch (SQLException e) {
            System.err.println("Error al verificar correo: " + e.getMessage());
        }
        return false;
    }

    public Usuario buscarUsuario(String usernameOCorreo) {
        String sql = "SELECT * FROM usuarios WHERE username = ? OR correo = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usernameOCorreo);
            stmt.setString(2, usernameOCorreo);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Crear objeto Usuario con los datos de la base de datos
                return new Usuario(
                        rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("correo"),
                        rs.getString("password_hash"),
                        rs.getTimestamp("fecha_registro").toLocalDateTime(),
                        rs.getString("nombre_completo"),
                        rs.getDate("fecha_nacimiento").toLocalDate()
                );
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar usuario: " + e.getMessage());
        }
        return null; // No se encontró el usuario
    }
}