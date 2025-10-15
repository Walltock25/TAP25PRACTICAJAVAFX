package com.sistema.login.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    // Configuración de la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/sistema_login";
    private static final String USER = "root";
    private static final String PASSWORD = "Creativo52";

    // Instancia única (volatile para thread-safety)
    private static volatile DatabaseConnection instance;
    private Connection connection;

    // Constructor privado para evitar instancias externa
    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Driver MySQL no encontrado");
            throw new RuntimeException("Driver no encontrado", e);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos");
            throw new RuntimeException("Error de conexión", e);
        }
    }

    //Obtiene la instancia única de DatabaseConnection (Double-checked locking)
    public static DatabaseConnection getInstance() {
        if (instance == null) {
            synchronized (DatabaseConnection.class) {
                if (instance == null) {
                    instance = new DatabaseConnection();
                }
            }
        }
        return instance;
    }

    //Obtiene la conexión actual, recreándola si está cerrada
    public Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Reconexión a la base de datos");
        }
        return connection;
    }
}