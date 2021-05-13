package com.example.proyectoaplicacion;

public class InstanciaServicios {
    private static Servicios servicios = null;
    public static Servicios getServiciosInstance(){
        if(servicios == null){
            servicios = new Servicios();
        }
        return servicios;
    }
}
