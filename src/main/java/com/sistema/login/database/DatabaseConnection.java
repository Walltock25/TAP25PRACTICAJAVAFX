package com.sistema.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Clase que maneja la conexión a la base de datos MySQL
 * Patrón Singleton: Solo una instancia de conexión
 */
public class DatabaseConnection {

    // Configuración de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_login";
    private static final String USER = "root"; // Cambia según tu configuración
    private static final String PASSWORD = "Creativo52"; // Cambia según tu configuración

    // Instancia única de la conexión (Singleton)
    private static Connection connection = null;

    /**
     * Constructor privado para evitar instancias múltiples
     */
    private DatabaseConnection() {}

    /**
     * Obtiene la conexión a la base de datos
     * Si no existe, la crea
     *
     * @return Connection - conexión activa a la base de datos
     * @throws SQLException si hay error de conexión
     */
    public static Connection getConnection() throws SQLException {
        // Si la conexión no existe o está cerrada, crear una nueva
        if (connection == null || connection.isClosed()) {
            try {
                // Cargar el driver de MySQL
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Establecer la conexión
                connection = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("✓ Conexión exitosa a la base de datos");

            } catch (ClassNotFoundException e) {
                System.err.println("✗ Error: Driver MySQL no encontrado");
                throw new SQLException("Driver no encontrado", e);
            }
        }
        return connection;
    }

    /**
     * Cierra la conexión a la base de datos
     */
    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("✓ Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            System.err.println("✗ Error al cerrar conexión: " + e.getMessage());
        }
    }

    /**
     * Verifica si la conexión está activa
     *
     * @return true si está conectado, false en caso contrario
     */
    public static boolean isConnected() {
        try {
            return connection != null && !connection.isClosed();
        } catch (SQLException e) {
            return false;
        }
    }
}