package com.example.proyectoaplicacion;

public class MisVentas {
    private int Id;
    private String Encabezado;
    private String Descripcion;
    private String Precio;
    private String Disponibilidad;
    private String Status;
    private String Proposito;

    public String getProposito() {
        return Proposito;
    }

    public void setProposito(String proposito) {
        Proposito = proposito;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getEncabezado() {
        return Encabezado;
    }

    public void setEncabezado(String encabezado) {
        Encabezado = encabezado;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getDisponibilidad() {
        return Disponibilidad;
    }

    public void setDisponibilidad(String disponibilidad) {
        Disponibilidad = disponibilidad;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }
}
