package com.example.demo1.models;

public class itemCarrito {
    Prenda prenda;
    int cantidad;
    public itemCarrito(Prenda prenda, int cantidad){
        this.cantidad=cantidad;
        this.prenda = prenda;
    }
    public Prenda getPrenda() {
        return prenda;
    }
    public String toString() {
        return "Prenda: " + prenda.toString() + ", Cantidad: " + cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
