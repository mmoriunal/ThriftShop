package com.example.demo1.models;

public class Prenda implements Comparable<Prenda> {
    public String nombre;
    public String color;
    public String fotoPath;
    public String talla;
    public int id_vendedor, precio, id;

    public Prenda(){
        this.id = 0;
        this.nombre = "";
        this.color = "";
        this.talla = "";
        this.id_vendedor = 0; // o -1
        this.precio = 0;
        this.fotoPath = "";
    }

    public Prenda(int id, String nombre, String color, String talla, int id_vendedor, int precio, String fotoPath) {
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.talla = talla;
        this.id_vendedor = id_vendedor;
        this.precio = precio;
        this.fotoPath = fotoPath;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + " Color: " + color + " Talla: " + talla +
                " Id_Vendedor: " + id_vendedor + " Precio: " + precio + " Foto: " + fotoPath;
    }
    public String getNombre(){return nombre;}
    public int getPrecio(){return precio;}
    public String getColor() {
        return color;
    }
    public String getTalla() {
        return talla;
    }
    public int getId_vendedor(){return id_vendedor;}
    public String getFotoPath() {
        return fotoPath;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public void setTalla(String talla) {
        this.talla = talla;
    }
    public void setId_vendedor(int id_vendedor) {
        this.id_vendedor = id_vendedor;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public void setFotoPath(String fotoPath) {
        this.fotoPath = fotoPath;
    }

    @Override
    public int compareTo(Prenda otraPrenda) {
        return Integer.compare(this.precio, otraPrenda.precio);
    }
    public static Prenda fromString(String line) {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0]);
        String nombre = parts[1];
        String color = parts[2];
        String talla = parts[3];
        int id_vendedor = Integer.parseInt(parts[4]);
        int precio = Integer.parseInt(parts[5]);
        String fotoPath = parts[6];
        return new Prenda(id, nombre, color, talla, id_vendedor, precio, fotoPath);
    }
}