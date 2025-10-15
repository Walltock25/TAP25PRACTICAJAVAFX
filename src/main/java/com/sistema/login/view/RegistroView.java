package com.sistema.login.view;

import com.sistema.login.dao.UsuarioDAO;
import com.sistema.login.model.Usuario;
import com.sistema.login.util.PasswordUtil;
import com.sistema.login.util.ValidacionUtil;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.time.LocalDate;

public class RegistroView {

    private Stage stage;
    private TextField txtNombreCompleto;
    private DatePicker dpFechaNacimiento;
    private TextField txtUsername;
    private TextField txtCorreo;
    private PasswordField txtPassword;
    private PasswordField txtConfirmarPassword;
    private UsuarioDAO usuarioDAO;

    public RegistroView() {
        usuarioDAO = new UsuarioDAO();
    }

    public void show(Stage stage) {
        this.stage = stage;

        // Crear el layout principal con scroll
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: #f0f0f0;");

        VBox root = new VBox(20);
        root.setPadding(new Insets(30));
        root.setAlignment(Pos.TOP_CENTER);
        root.setStyle("-fx-background-color: #f0f0f0;");

        // Título
        Label lblTitulo = new Label("Crear Cuenta");
        lblTitulo.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        lblTitulo.setStyle("-fx-text-fill: #2c3e50;");

        // Subtítulo
        Label lblSubtitulo = new Label("Completa todos los campos para registrarte");
        lblSubtitulo.setFont(Font.font("Arial", 14));
        lblSubtitulo.setStyle("-fx-text-fill: #7f8c8d;");

        // GridPane para los campos
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(15);
        grid.setPadding(new Insets(20));
        grid.setStyle("-fx-background-color: white; -fx-background-radius: 10;");

        // Campo Nombre Completo
        Label lblNombre = new Label("Nombre Completo:");
        lblNombre.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 13));
        txtNombreCompleto = new TextField();
        txtNombreCompleto.setPromptText("Ej: Juan Pérez García");
        txtNombreCompleto.setPrefWidth(300);

        // Campo Fecha de Nacimiento
        Label lblFecha = new Label("Fecha de Nacimiento:");
        lblFecha.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 13));
        dpFechaNacimiento = new DatePicker();
        dpFechaNacimiento.setPromptText("Selecciona tu fecha");
        dpFechaNacimiento.setPrefWidth(300);

        // Campo Username
        Label lblUsername = new Label("Nombre de Usuario:");
        lblUsername.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 13));
        txtUsername = new TextField();
        txtUsername.setPromptText("3-20 caracteres, sin espacios");
        txtUsername.setPrefWidth(300);

        // Campo Correo
        Label lblCorreo = new Label("Correo Electrónico:");
        lblCorreo.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 13));
        txtCorreo = new TextField();
        txtCorreo.setPromptText("ejemplo@correo.com");
        txtCorreo.setPrefWidth(300);

        // Campo Contraseña
        Label lblPassword = new Label("Contraseña:");
        lblPassword.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 13));
        txtPassword = new PasswordField();
        txtPassword.setPromptText("Mínimo 8 caracteres");
        txtPassword.setPrefWidth(300);

        // Info contraseña
        Label lblInfoPass = new Label("Debe contener: mayúsculas, minúsculas, números y caracteres especiales");
        lblInfoPass.setFont(Font.font("Arial", 11));
        lblInfoPass.setStyle("-fx-text-fill: #7f8c8d;");
        lblInfoPass.setWrapText(true);
        lblInfoPass.setMaxWidth(300);

        // Campo Confirmar Contraseña
        Label lblConfirmar = new Label("Confirmar Contraseña:");
        lblConfirmar.setFont(Font.font("Arial", FontWeight.SEMI_BOLD, 13));
        txtConfirmarPassword = new PasswordField();
        txtConfirmarPassword.setPromptText("Repite tu contraseña");
        txtConfirmarPassword.setPrefWidth(300);

        // Agregar campos al grid
        int row = 0;
        grid.add(lblNombre, 0, row++);
        grid.add(txtNombreCompleto, 0, row++);
        grid.add(lblFecha, 0, row++);
        grid.add(dpFechaNacimiento, 0, row++);
        grid.add(lblUsername, 0, row++);
        grid.add(txtUsername, 0, row++);
        grid.add(lblCorreo, 0, row++);
        grid.add(txtCorreo, 0, row++);
        grid.add(lblPassword, 0, row++);
        grid.add(txtPassword, 0, row++);
        grid.add(lblInfoPass, 0, row++);
        grid.add(lblConfirmar, 0, row++);
        grid.add(txtConfirmarPassword, 0, row++);

        // Botones
        HBox botonesBox = new HBox(15);
        botonesBox.setAlignment(Pos.CENTER);
        botonesBox.setPadding(new Insets(10, 0, 0, 0));

        Button btnRegistrar = new Button("Registrarse");
        btnRegistrar.setPrefWidth(130);
        btnRegistrar.setStyle("-fx-background-color: #2ecc71; -fx-text-fill: white; " +
                "-fx-font-size: 13; -fx-font-weight: bold; -fx-cursor: hand;");
        btnRegistrar.setOnAction(e -> realizarRegistro());

        Button btnVolver = new Button("Volver al Login");
        btnVolver.setPrefWidth(130);
        btnVolver.setStyle("-fx-background-color: #95a5a6; -fx-text-fill: white; " +
                "-fx-font-size: 13; -fx-font-weight: bold; -fx-cursor: hand;");
        btnVolver.setOnAction(e -> volverLogin());

        botonesBox.getChildren().addAll(btnRegistrar, btnVolver);

        // Agregar todo al root
        root.getChildren().addAll(lblTitulo, lblSubtitulo, grid, botonesBox);

        scrollPane.setContent(root);

        // Crear escena
        Scene scene = new Scene(scrollPane, 500, 650);
        stage.setScene(scene);
        stage.setTitle("Sistema de Login - Registro");
        stage.setResizable(false);
        stage.show();
    }

    //Realiza el proceso de registro
    private void realizarRegistro() {
        // Obtener datos
        String nombreCompleto = ValidacionUtil.limpiarTexto(txtNombreCompleto.getText());
        LocalDate fechaNacimiento = dpFechaNacimiento.getValue();
        String username = ValidacionUtil.limpiarTexto(txtUsername.getText());
        String correo = ValidacionUtil.limpiarTexto(txtCorreo.getText());
        String password = txtPassword.getText();
        String confirmarPassword = txtConfirmarPassword.getText();

        // Validar campos vacíos
        if (ValidacionUtil.estaVacio(nombreCompleto) || fechaNacimiento == null ||
                ValidacionUtil.estaVacio(username) || ValidacionUtil.estaVacio(correo) ||
                ValidacionUtil.estaVacio(password) || ValidacionUtil.estaVacio(confirmarPassword)) {
            mostrarError("Campos Incompletos", "Por favor completa todos los campos");
            return;
        }

        // Validar nombre completo
        if (!ValidacionUtil.esNombreCompletoValido(nombreCompleto)) {
            mostrarError("Nombre Inválido",
                    "El nombre debe contener al menos nombre y apellido,\nsolo letras y espacios");
            return;
        }

        // Validar username
        String errorUsername = ValidacionUtil.obtenerMensajeErrorUsername(username);
        if (errorUsername != null) {
            mostrarError("Username Inválido", errorUsername);
            return;
        }

        // Validar correo
        if (!ValidacionUtil.esCorreoValido(correo)) {
            mostrarError("Correo Inválido", "Por favor ingresa un correo válido");
            return;
        }

        // Validar que las contraseñas coincidan
        if (!ValidacionUtil.passwordsCoinciden(password, confirmarPassword)) {
            mostrarError("Contraseñas no Coinciden", "Las contraseñas deben ser idénticas");
            return;
        }

        // Validar fortaleza de contraseña
        if (!PasswordUtil.esPasswordFuerte(password)) {
            mostrarError("Contraseña Débil", PasswordUtil.obtenerMensajeValidacion(password));
            return;
        }

        // Verificar si el username ya existe
        if (usuarioDAO.existeUsername(username)) {
            mostrarError("Username en Uso", "Este nombre de usuario ya está registrado");
            return;
        }

        // Verificar si el correo ya existe
        if (usuarioDAO.existeCorreo(correo)) {
            mostrarError("Correo en Uso", "Este correo electrónico ya está registrado");
            return;
        }

        // Encriptar contraseña
        String passwordHash = PasswordUtil.encriptarPassword(password);

        // Crear usuario
        Usuario nuevoUsuario = new Usuario(username, correo, passwordHash,
                nombreCompleto, fechaNacimiento);

        // Insertar en base de datos
        boolean exito = usuarioDAO.insertarUsuario(nuevoUsuario);

        if (exito) {
            mostrarExito("Registrado Correctamente",
                    "Tu cuenta ha sido creada.\nAhora puedes iniciar sesión.");
            volverLogin();
        } else {
            mostrarError("Error", "Hubo un problema al crear tu cuenta.\nIntenta nuevamente.");
        }
    }

    private void volverLogin() {
        LoginView loginView = new LoginView();
        loginView.show(stage);
    }

    private void mostrarError(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private void mostrarExito(String titulo, String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}