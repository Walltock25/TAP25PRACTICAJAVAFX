package com.sistema.login;

import com.sistema.login.view.LoginView;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Clase principal de la aplicación
 * Extiende de Application para crear una aplicación JavaFX
 */
public class Main extends Application {

    /**
     * Método start - Se ejecuta al iniciar la aplicación JavaFX
     * @param primaryStage - La ventana principal de la aplicación
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            // Crear y mostrar la ventana de Login
            LoginView loginView = new LoginView();
            loginView.show(primaryStage);

        } catch (Exception e) {
            System.err.println("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Método main - Punto de entrada de la aplicación
     * @param args - Argumentos de línea de comandos
     */
    public static void main(String[] args) {
        // launch() inicia la aplicación JavaFX
        // Automáticamente llama al método start()
        launch(args);
    }
}