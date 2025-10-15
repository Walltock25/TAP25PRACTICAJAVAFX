package com.sistema.login.view;

import com.sistema.login.model.Usuario;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.time.format.DateTimeFormatter;

public class BienvenidaView {

    private Stage stage;
    private Usuario usuario;

    public BienvenidaView(Usuario usuario) {
        this.usuario = usuario;
    }

    public void show(Stage stage) {
        this.stage = stage;

        // Layout principal
        VBox root = new VBox(25);
        root.setPadding(new Insets(40));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: linear-gradient(to bottom, #3498db, #2ecc71);");

        // Contenedor blanco para el contenido
        VBox contenedor = new VBox(20);
        contenedor.setPadding(new Insets(40));
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setStyle("-fx-background-color: white; -fx-background-radius: 15; " +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 10, 0, 0, 3);");
        contenedor.setMaxWidth(450);

        // Título de bienvenida
        Label lblBienvenida = new Label("Bienvenido");
        lblBienvenida.setFont(Font.font("Arial", FontWeight.BOLD, 32));
        lblBienvenida.setStyle("-fx-text-fill: #2c3e50;");

        // Nombre del usuario
        Label lblNombre = new Label(usuario.getNombreCompleto());
        lblNombre.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        lblNombre.setStyle("-fx-text-fill: #3498db;");
        lblNombre.setWrapText(true);
        lblNombre.setAlignment(Pos.CENTER);

        // Separador
        Label lblSeparador = new Label("━━━━━━━━━━━━━━━━━━━━━━");
        lblSeparador.setStyle("-fx-text-fill: #ecf0f1;");

        // Información del usuario
        VBox infoBox = new VBox(10);
        infoBox.setAlignment(Pos.CENTER_LEFT);
        infoBox.setPadding(new Insets(10));
        infoBox.setStyle("-fx-background-color: #ecf0f1; -fx-background-radius: 8;");

        Label lblInfoTitulo = new Label("📋 Información de tu cuenta:");
        lblInfoTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        lblInfoTitulo.setStyle("-fx-text-fill: #2c3e50;");

        Label lblUsername = new Label("Usuario: @" + usuario.getUsername());
        lblUsername.setFont(Font.font("Arial", 13));
        lblUsername.setStyle("-fx-text-fill: #34495e;");

        Label lblCorreo = new Label("Correo: " + usuario.getCorreo());
        lblCorreo.setFont(Font.font("Arial", 13));
        lblCorreo.setStyle("-fx-text-fill: #34495e;");
        lblCorreo.setWrapText(true);

        // Formatear fecha de nacimiento
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaNacimiento = usuario.getFechaNacimiento().format(formatter);
        Label lblFechaNac = new Label("Fecha de Nacimiento: " + fechaNacimiento);
        lblFechaNac.setFont(Font.font("Arial", 13));
        lblFechaNac.setStyle("-fx-text-fill: #34495e;");

        // Formatear fecha de registro
        DateTimeFormatter formatterRegistro = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fechaRegistro = usuario.getFechaRegistro().format(formatterRegistro);
        Label lblRegistro = new Label("Miembro desde: " + fechaRegistro);
        lblRegistro.setFont(Font.font("Arial", 13));
        lblRegistro.setStyle("-fx-text-fill: #34495e;");

        infoBox.getChildren().addAll(lblInfoTitulo, lblUsername, lblCorreo,
                lblFechaNac, lblRegistro);

        // Botón cerrar sesión
        Button btnCerrarSesion = new Button("Cerrar Sesión");
        btnCerrarSesion.setPrefWidth(200);
        btnCerrarSesion.setPrefHeight(40);
        btnCerrarSesion.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        btnCerrarSesion.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; " +
                "-fx-cursor: hand; -fx-background-radius: 20;");
        btnCerrarSesion.setOnAction(e -> cerrarSesion());
        btnCerrarSesion.setOnMouseEntered(e ->
                btnCerrarSesion.setStyle("-fx-background-color: #c0392b; -fx-text-fill: white; " +
                        "-fx-cursor: hand; -fx-background-radius: 20;"));
        btnCerrarSesion.setOnMouseExited(e ->
                btnCerrarSesion.setStyle("-fx-background-color: #e74c3c; -fx-text-fill: white; " +
                        "-fx-cursor: hand; -fx-background-radius: 20;"));

        // Añadir elementos al contenedor y escena
        contenedor.getChildren().addAll( lblBienvenida, lblNombre, lblSeparador, infoBox, btnCerrarSesion);
        root.getChildren().add(contenedor);

        Scene scene = new Scene(root, 600, 520);
        stage.setScene(scene);
        stage.setTitle("Bienvenido - " + usuario.getUsername());
        stage.show();
    }

    private void cerrarSesion() {
        // Mensaje de confirmación
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Sesión Cerrada");
        alert.setHeaderText(null);
        alert.setContentText("Has cerrado sesión correctamente.");
        alert.initOwner(stage);
        alert.showAndWait();

        // Limpiar datos de sesión
        this.usuario = null;

        // Regresar a la ventana de login
        LoginView loginView = new LoginView();
        loginView.show(stage);
    }
}