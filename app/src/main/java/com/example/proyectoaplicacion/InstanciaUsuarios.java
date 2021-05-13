package com.example.proyectoaplicacion;

public class InstanciaUsuarios {
    public static Usuarios users;
    public static Usuarios getUsuariosInstance(){
        if(users == null){
            users = new Usuarios();
        }
        return users;
    }
}
