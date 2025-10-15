package com.sistema.login.view;
import com.sistema.login.model.Usuario;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
public class BienvenidaViewComando {
    Scanner sc = new Scanner(System.in);
    private Usuario usuario;

    public BienvenidaViewComando(Usuario usuario) {
        this.usuario = usuario;
    }
    public void show() {

        String Username = "Usuario: @" + usuario.getUsername();
        System.out.println(Username);
        String Correo = "Correo: " + usuario.getCorreo();
        System.out.println(Correo);

        // Formatear fecha de nacimiento
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String fechaNacimiento = usuario.getFechaNacimiento().format(formatter);
        System.out.println(fechaNacimiento);

        // Formatear fecha de registro
        DateTimeFormatter formatterRegistro = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String fechaRegistro = usuario.getFechaRegistro().format(formatterRegistro);
        String Registro = "Miembro desde: " + fechaRegistro;
        System.out.println(Registro);

        System.out.println("Deseas cerrar sesión?\n1. Si");
        int eleccion = sc.nextInt();
        if (eleccion == 1) {
            cerrarSesion();
        }
    }
    private void cerrarSesion() {
        // Mensaje de confirmación
        System.out.println("Sesión Cerrada");
        System.out.println("Has cerrado sesión correctamente");

        // Limpiar datos de sesión
        this.usuario = null;

        // Regresar a la ventana de login
        LoginViewComando loginViewComando = new LoginViewComando();
        loginViewComando.m_ingrDatos();
    }
}
