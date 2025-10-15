package com.sistema.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Configuración de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_login";
    private static final String USER = "root"; // Cambia según tu configuración
    private static final String PASSWORD = "Creativo52"; // Cambia según tu configuración

    // Instancia única de la conexión (Singleton)
    private static Connection connection = null;

    private DatabaseConnection() {}

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
}