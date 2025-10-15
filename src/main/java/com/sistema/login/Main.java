package com.sistema.login;

import com.sistema.login.view.LoginView;
import com.sistema.login.view.LoginViewComando;
import com.sistema.login.factory.ViewFactory;
import com.sistema.login.factory.ViewFactory.TipoVista;
import javafx.application.Application;
import javafx.stage.Stage;
import java.util.Scanner;

public class Main extends Application {
    private static int seleccionUsuario = 1; // Por defecto gráfico

    public static void main(String[] args) {
        Scanner v_teclado = new Scanner(System.in);
        System.out.println("Bienvenido al login. ¿Deseas tener vista gráfica o por comandos?");
        System.out.println("Opción 1: Interfaz Gráfica (GUI)");
        System.out.println("Opción 2: Línea de Comandos");
        System.out.print("Selecciona una opción: ");

        try {
            seleccionUsuario = v_teclado.nextInt();
            if (seleccionUsuario < 1 || seleccionUsuario > 2) {
                System.out.println("Opción inválida. Usando interfaz gráfica por defecto.");
                seleccionUsuario = 1;
            }
        } catch (Exception e) {
            System.out.println("Entrada inválida. Usando interfaz gráfica por defecto.");
            seleccionUsuario = 1;
        }
        // launch() inicia la aplicación JavaFX y llama a start()
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try {
            // Usar el Factory para crear la vista según la selección
            TipoVista tipoVista = ViewFactory.obtenerTipoVistaLogin(seleccionUsuario);

            switch (tipoVista) {
                case LOGIN_GRAFICO:
                    LoginView loginView = (LoginView) ViewFactory.crearVistaLogin(tipoVista);
                    loginView.show(primaryStage);
                    break;
                case LOGIN_COMANDO:
                    LoginViewComando loginViewComando = (LoginViewComando) ViewFactory.crearVistaLogin(tipoVista);
                    loginViewComando.m_ingrDatos();
                    break;
                default:
                    System.err.println("Tipo de vista no reconocido");
            }
        } catch (Exception e) {
            System.err.println("Error al iniciar la aplicación: " + e.getMessage());
            e.printStackTrace();
        }
    }
}