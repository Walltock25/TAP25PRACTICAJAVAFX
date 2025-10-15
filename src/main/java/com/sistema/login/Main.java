package com.sistema.login;

import com.sistema.login.view.LoginView;
import com.sistema.login.view.LoginViewComando;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Scanner;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            Scanner v_teclado = new Scanner(System.in);
            System.out.println("Bienvenido al login deseas tener vista grafica o por comandos");
            System.out.println("Opción 1 gráfico\nOpción 2 comando");
            int seleccion = v_teclado.nextInt();

            switch (seleccion) {
                case 1: // Crear y mostrar la ventana de Login
                    LoginView loginView = new LoginView();
                    loginView.show(primaryStage);
                    break;
                case 2: // Crear y mostrar en terminal Login
                    LoginViewComando loginViewComando = new LoginViewComando();
                    loginViewComando.m_ingrDatos();
                    break;
                default:
            }

        } catch (Exception e) {
            System.err.println("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // launch() inicia la aplicación JavaFX y llama a start()
        launch(args);
    }
}