package com.example.proyectoaplicacion;

public class InstanciaProductos {
    public static Productos productos = null;
    public static Productos getProductosInstance(){
        if(productos == null){
            productos = new Productos();
        }
        return productos;
    }
}
