public class Prenda{
    String nombre, color;
    Character talla;
    int id_vendedor, precio;
    public Prenda(String nombre, String color, Character talla, int id_vendedor, int precio){
        this.nombre = nombre;
        this.color = color;
        this.talla = talla;
        this.id_vendedor = id_vendedor;
        this.precio = precio;
    }
    public String toString(){
        return "Nombre: " + nombre + " Color: " + color + " Talla: " + talla + " Id_Vendedor: " + id_vendedor + " Precio: " + precio;
    }
}
