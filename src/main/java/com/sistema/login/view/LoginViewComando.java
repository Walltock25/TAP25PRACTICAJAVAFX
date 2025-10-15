package com.sistema.login.view;

import com.sistema.login.dao.UsuarioDAO;
import com.sistema.login.model.Usuario;
import com.sistema.login.util.PasswordUtil;
import com.sistema.login.util.ValidacionUtil;
import java.util.Scanner;

public class LoginViewComando {
    private String txtUsuario;
    private String txtPassword;
    private UsuarioDAO usuarioDAO;
    Scanner sc = new Scanner(System.in);

    public void m_ingrDatos(){
        while (true) {
            System.out.println("Bienvenido a entrada por comando");
            System.out.println("Indica que opciones buscas:");
            System.out.println("1. Loguearte");
            System.out.println("2. Registrarse");
            System.out.println("3. Salir");
            String linea = sc.nextLine().trim();
            int opcion;
            try {
                opcion = Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Opción incorrecta");
                continue;
            }

            switch (opcion){
                case 1:
                    loginView();
                    break;
                case 2:
                    abrirRegistro();
                    break;
                case 3:
                    System.out.println("Hasta pronto");
                    sc.close();
                    System.exit(0);
                    return;
                default:
                    System.out.println("Opción incorrecta");
            }
        }
    }

    private void loginView() {
        usuarioDAO = new UsuarioDAO();
        System.out.println("Bienvenido a entrada por comando");
        realizarLogin();
    }

    //Realiza el proceso de login
    private void realizarLogin() {
        System.out.println("Ingrese su nombre:");
        txtUsuario = sc.nextLine();
        System.out.println("Ingrese su password:");
        txtPassword = sc.nextLine();
        String usuarioOCorreo = ValidacionUtil.limpiarTexto(txtUsuario);
        String password = txtPassword;

        if (ValidacionUtil.estaVacio(usuarioOCorreo) || ValidacionUtil.estaVacio(password)) {
            System.out.println("Error; Por favor completa todos los campos");
            return;
        }

        Usuario usuario = usuarioDAO.buscarUsuario(usuarioOCorreo);

        if (usuario == null) {
            System.out.println("Error de Login Usuario o correo no encontrado");
            return;
        }

        if (!PasswordUtil.verificarPassword(password, usuario.getPasswordHash())) {
            System.out.println("Error de Login, Contraseña incorrecta");
            return;
        }

        System.out.println("¡Bienvenido! Inicio de sesión exitoso");

        BienvenidaViewComando bienvenidaViewComando = new BienvenidaViewComando(usuario);
        bienvenidaViewComando.show();
    }

    //Abre la ventana de registro
    private void abrirRegistro() {
        RegistroViewComando registroViewComando = new RegistroViewComando();
        registroViewComando.show();
    }
}
