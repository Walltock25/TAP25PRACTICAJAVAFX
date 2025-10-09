package com.sistema.login.view;

import com.sistema.login.dao.UsuarioDAO;
import com.sistema.login.model.Usuario;
import com.sistema.login.util.PasswordUtil;
import com.sistema.login.util.ValidacionUtil;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 * Vista de Login - Pantalla de inicio de sesión
 */
public class LoginView {

    private Stage stage;
    private TextField txtUsuario;
    private PasswordField txtPassword;
    private UsuarioDAO usuarioDAO;

    public LoginView() {
        usuarioDAO = new UsuarioDAO();
    }

    /**
     * Muestra la ventana de Login
     * @param stage - Ventana principal
     */
    public void show(Stage stage) {
        this.stage = stage;

        // Crear el layout principal
        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #f0f0f0;");

        // Título
        Label lblTitulo = new Label("Iniciar Sesión");
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        lblTitulo.setStyle("-fx-text-fill: #2c3e50;");

        // Subtítulo
        Label lblSubtitulo = new Label("Ingresa tus credenciales");
        lblSubtitulo.setFont(Font.font("Arial", 14));
        lblSubtitulo.setStyle("-fx-text-fill: #7f8c8d;");

        // GridPane para los campos
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setPadding(new Insets(20));
        grid.setStyle("-fx-background-color: white; -fx-background-radius: 10;");

        // Campo Usuario/Correo
        Label lblUsuario = new Label("Usuario o Correo:");
        lblUsuario.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 13));
        txtUsuario = new TextField();
        txtUsuario.setPromptText("Ingresa tu usuario o correo");
        txtUsuario.setPrefWidth(250);
        txtUsuario.setStyle("-fx-font-size: 13;");

        // Campo Contraseña
        Label lblPassword = new Label("Contraseña:");
        lblPassword.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 13));
        txtPassword = new PasswordField();
        txtPassword.setPromptText("Ingresa tu contraseña");
        txtPassword.setPrefWidth(250);
        txtPassword.setStyle("-fx-font-size: 13;");

        // Agregar campos al grid
        grid.add(lblUsuario, 0, 0);
        grid.add(txtUsuario, 0, 1);
        grid.add(lblPassword, 0, 2);
        grid.add(txtPassword, 0, 3);

        // Botones
        HBox botonesBox = new HBox(15);
        botonesBox.setAlignment(Pos.CENTER);

        Button btnLogin = new Button("Iniciar Sesión");
        btnLogin.setPrefWidth(120);
        btnLogin.setStyle("-fx-background-color: #3498db; -fx-text-fill: white; " +
                "-fx-font-size: 13; -fx-font-weight: bold; -fx-cursor: hand;");
        btnLogin.setOnAction(e -> realizarLogin());

        Button btnRegistro = new Button("Registrarse");
        btnRegistro.setPrefWidth(120);
        btnRegistro.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; " +
                "-fx-font-size: 13; -fx-font-weight: bold; -fx-cursor: hand;");
        btnRegistro.setOnAction(e -> abrirRegistro());

        botonesBox.getChildren().addAll(btnLogin, btnRegistro);

        // Enter para login
        txtPassword.setOnAction(e -> realizarLogin());

        // Agregar todo al root
        root.getChildren().addAll(lblTitulo, lblSubtitulo, grid, botonesBox);

        // Crear escena
        Scene scene = new Scene(root, 450, 450);
        stage.setScene(scene);
        stage.setTitle("Sistema de Login - Iniciar Sesión");
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Realiza el proceso de login
     */
    private void realizarLogin() {
        // Obtener datos
        String usuarioOCorreo = ValidacionUtil.limpiarTexto(txtUsuario.getText());
        String password = txtPassword.getText();

        // Validar campos vacíos
        if (ValidacionUtil.estaVacio(usuarioOCorreo) || ValidacionUtil.estaVacio(password)) {
            mostrarError("Error", "Por favor completa todos los campos");
            return;
        }

        // Buscar usuario en la base de datos
        Usuario usuario = usuarioDAO.buscarUsuario(usuarioOCorreo);

        if (usuario == null) {
            mostrarError("Error de Login", "Usuario o correo no encontrado");
            return;
        }

        // Verificar contraseña
        if (!PasswordUtil.verificarPassword(password, usuario.getPasswordHash())) {
            mostrarError("Error de Login", "Contraseña incorrecta");
            return;
        }

        // Login exitoso
        mostrarExito("¡Bienvenido!", "Inicio de sesión exitoso");

        // Abrir ventana de bienvenida
        BienvenidaView bienvenidaView = new BienvenidaView(usuario);
        bienvenidaView.show(stage);
    }

    /**
     * Abre la ventana de registro
     */
    private void abrirRegistro() {
        RegistroView registroView = new RegistroView();
        registroView.show(stage);
    }

    /**
     * Muestra un mensaje de error
     */
    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra un mensaje de éxito
     */
    private void mostrarExito(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}