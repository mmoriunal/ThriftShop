package com.example.demo1.models;

import java.util.LinkedList;

public class Carrito {
    private static Carrito instancia;
    private static LinkedList<Prenda> carrito = new LinkedList<>();
    private static double subtotal = 0;
    public static void resetSubtotal(){
        subtotal = 0;
    }
    public static void addToSubtotal(double amount) {
        subtotal += amount;
    }

    public static void subtractFromSubtotal(double amount) {
        subtotal -= amount;
    }

    public static double getSubtotal() {
        return subtotal;
    }

    public static Carrito getInstancia() {
        if (instancia == null) {
            instancia = new Carrito();
        }
        return instancia;
    }

    public static LinkedList<Prenda> getCarrito() {
        return carrito;
    }

    public static void agregarPrenda(Prenda prenda) {
        if (!carrito.contains(prenda)) {
            carrito.add(prenda);
        }
    }
    public static int getSize() {
        return carrito.size();
    }

    public static boolean contienePrenda(Prenda prenda) {
        return carrito.contains(prenda);
    }
    public static void removeFromCarrito(Prenda prenda) {
        carrito.remove(prenda);
    }
}