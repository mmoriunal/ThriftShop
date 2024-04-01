import java.time.LocalDate;

public class Order{
    private LocalDate date;
    private int id_usuario, cantidad;
    private Prenda prenda;
  
    public Order(LocalDate date, int id_usuario, int cantidad, Prenda prenda){
      this.date = date;
      this.id_usuario = id_usuario;
      this.cantidad = cantidad;
      this.prenda = prenda;
    }
  
    public LocalDate getDate() {
      return date;
    }
  
    public void setDate(LocalDate date) {
      this.date = date;
    }
  
    public int getId_usuario() {
      return id_usuario;
    }
  
    public void setId_usuario(int id_usuario) {
      this.id_usuario = id_usuario;
    }
  
    public int getCantidad() {
      return cantidad;
    }
  
    public void setCantidad(int cantidad) {
      this.cantidad = cantidad;
    }
  
    public Prenda getPrenda(){
      return prenda;
    }
  
    public void setPrenda(Prenda prenda){
      this.prenda = prenda;
    }
  
    @Override
    public String toString(){
      return "Fecha: " + date + ", id_usuario: " + id_usuario + ", cantidad: " + cantidad + ", prenda: " + prenda.nombre;
    }
}
