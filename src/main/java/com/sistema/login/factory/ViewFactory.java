package com.sistema.login.factory;
import com.sistema.login.view.*;

public class ViewFactory {
    public enum TipoVista {
        LOGIN_GRAFICO,
        LOGIN_COMANDO,
    }
    //Crea una vista de login según el tipo especificado
    public static Object crearVistaLogin(TipoVista tipo) {
        switch (tipo) {
            case LOGIN_GRAFICO:
                return new LoginView();
            case LOGIN_COMANDO:
                return new LoginViewComando();
            default:
                throw new IllegalArgumentException("Tipo de vista de login no válido: " + tipo);
        }
    }
    //Determina el tipo de vista según una selección numérica
    public static boolean esVistaGrafica(int seleccion) {
        return seleccion == 1;
    }
    //Obtiene el tipo de vista login según la selección del usuario
    public static TipoVista obtenerTipoVistaLogin(int seleccion) {
        return esVistaGrafica(seleccion) ? TipoVista.LOGIN_GRAFICO : TipoVista.LOGIN_COMANDO;
    }
}