import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        FiltradoPrecioService filtrado = new FiltradoPrecioService();

        ArrayList <Prenda> prendas= new ArrayList<Prenda>();
        for(int i = 0; i<1000000; i++){
            Prenda p = generarPrendaAleatoria();
            prendas.add(p);
        }

        filtrado.testPerformance(prendas, 0, 50000);
        filtrado.testPerformance(prendas, 0, 50000 , 10);
        // Scanner sc = new Scanner(System.in);
        // System.out.println("Deseas filtrar por precio? (S/N)");
        // String respuesta = sc.nextLine();
        // if (respuesta.equalsIgnoreCase("S")) {
        //     
        // }

    }
  
    public void carritoDeCompras(Scanner sc){
            LinkedList<ItemCarrito> carrito = new LinkedList<>();
            System.out.println("Escribe la cantidad de elementos que deseas agregar al carrito: ");
            int cantidadPrendas = sc.nextInt();
            sc.nextLine();
            for (int i = 0; i < cantidadPrendas; i++) {
                ItemCarrito nuevoItem = new ItemCarrito(generarPrendaAleatoria(),1);
                carrito.pushBack(nuevoItem);
            }
            carrito.getData();
            System.out.println("¿Deseas modificar la cantidad de algún elemento del carrito? (S/N)");
            String respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("S")) {
                System.out.println("Ingrese el número del elemento que desea modificar: ");
                int indiceModificar = sc.nextInt() - 1;
                sc.nextLine();
                System.out.println("Ingrese la nueva cantidad: ");
                int nuevaCantidad = sc.nextInt();
                sc.nextLine();
                carrito.setCantidad(indiceModificar, nuevaCantidad);
                //carrito.getData();
                System.out.println("El elemento modificado fue: ");
                System.out.println(carrito.getData(indiceModificar));
            }
            System.out.println("¿Deseas eliminar algún elemento del carrito? (S/N)");
            respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("S")) {
                System.out.println("Ingrese el número del elemento que desea eliminar: ");
                int indiceEliminar = sc.nextInt() - 1;
                String elementoEliminado = carrito.getData(indiceEliminar);
                carrito.delete(indiceEliminar);
                sc.nextLine();
                System.out.println("El elemento eliminado fue: ");
                System.out.println(elementoEliminado);
            }
            System.out.println("¿Deseas ver tu carrito? (S/N)");
            respuesta = sc.nextLine();
            if (respuesta.equalsIgnoreCase("S")) {
                carrito.getData();
            }
        }
    public void historialPrendas(Scanner sc){
        System.out.println("Escribe la cantidad de datos que deseas añadir al historial: ");
        int cantidadPrendas = sc.nextInt();
        sc.nextLine();
        Stack<Prenda> historialPrendas = new Stack<Prenda>(cantidadPrendas);
        for (int i = 0; i < cantidadPrendas; i++) {
            Prenda nuevaPrenda = generarPrendaAleatoria();
            historialPrendas.push(nuevaPrenda);
        }
        System.out.println("Deseas ver el último elemento del historial? (S/N)");
        String respuesta = sc.nextLine();
        if (respuesta.equalsIgnoreCase("S")) {
            System.out.println(historialPrendas.top());
        }
        System.out.println("Deseas buscar elementos del historial por nombre? (S/N)");
        respuesta = sc.nextLine();
        if (respuesta.equalsIgnoreCase("S")) {
            System.out.println("Escribe el nombre de la prenda: ");
            String nombrePrenda = sc.nextLine();
            buscarPrendasPorNombreHistorial(historialPrendas, nombrePrenda);
        }
        System.out.println("Deseas eliminar alguna prenda del historial por nombre? (S/N)");
        respuesta = sc.nextLine();
        if (respuesta.equalsIgnoreCase("S")) {
            System.out.println("Escribe el nombre de la prenda: ");
            String nombrePrenda = sc.nextLine();
            eliminarPrendaHistorial(historialPrendas, nombrePrenda);
        }
        System.out.println("Deseas vaciar el historial? (S/N)");
        respuesta = sc.nextLine();
        if (respuesta.equalsIgnoreCase("S")) {
            limpiarHistorial(historialPrendas);
        }
        System.out.println("Deseas ver el historial? (S/N)");
        respuesta = sc.nextLine();
        if (respuesta.equalsIgnoreCase("S")) {
            historialPrendas.verStack();
        }
    }
    public void listaOrdenes(Scanner sc){
      System.out.println("Escribe la cantidad de datos que deseas añadir a la lista de ordenes: ");
      int cantidadPrendas = sc.nextInt();
      sc.nextLine();
      Queue<Order> q = pruebaQueue(cantidadPrendas);

      // 
      System.out.println("¿Deseas ver las ordenes? (S/N)");
      String respuesta = sc.nextLine();
      if (respuesta.equalsIgnoreCase("S")) {
          q.printQueue();
      }

      System.out.println("¿Deseas ver el siguiente elemento de la lista de ordenes? (S/N)");
      respuesta = sc.nextLine();
      if (respuesta.equalsIgnoreCase("S")) {
          System.out.println(q.peek());
      }

      System.out.println("¿Deseas eliminar algun elemento de la lista de ordenes por prenda? (S/N)");
      respuesta = sc.nextLine();
      if (respuesta.equalsIgnoreCase("S")) {
          System.out.println("Escribe el nombre de la prenda: ");
          String nombrePrenda = sc.nextLine();
          eliminarOrdenLista(q, nombrePrenda);
      }

      System.out.println("¿Deseas modificar la cantidad de algun elemento de la lista de ordenes? (S/N)");
      respuesta = sc.nextLine();
      if (respuesta.equalsIgnoreCase("S")) {
          System.out.println("Escribe id de usuario: ");
          int id_usuario = sc.nextInt();
          sc.nextLine();
        
          System.out.println("Escribe la nueva cantidad: ");
          int nuevaCant = sc.nextInt();
          sc.nextLine();

          System.out.println("Escribe el nombre de la prenda: ");
          String nombrePrenda = sc.nextLine();
        
          modificarOrdenLista(q, id_usuario, nombrePrenda, nuevaCant);
      }

      System.out.println("¿Deseas ver las ordenes? (S/N)");
      respuesta = sc.nextLine();
      if (respuesta.equalsIgnoreCase("S")) {
          q.printQueue();
      }

      System.out.println("¿Deseas añadir una orden de la lista de ordenes? (S/N)");
      respuesta = sc.nextLine();
      if (respuesta.equalsIgnoreCase("S")) {
          System.out.println("Escribe id de usuario: ");
          int id_usuario = sc.nextInt();
          sc.nextLine();

          System.out.println("Escribe el nombre de la prenda: ");
          String nombrePrenda = sc.nextLine();

          Prenda prenda = generarPrendaAleatoria();
          prenda.nombre = nombrePrenda;

          System.out.println("Escribe cuantos vas a pedir: ");
          int cant = sc.nextInt();
          sc.nextLine();

          addOrdenLista(q, id_usuario, prenda, cant);
      }

      System.out.println("¿Deseas ver las ordenes? (S/N)");
      respuesta = sc.nextLine();
      if (respuesta.equalsIgnoreCase("S")) {
          q.printQueue();
      }
    }
    public static Prenda generarPrendaAleatoria() {
        String[] nombres = {"Pantalon", "Camiseta", "Sudadera", "Falda", "Chaqueta", "Vestido", "Blusa", "Pantaloneta", "Zapatos", "Sandalias"};
        String[] colores = {"Azul", "Rojo", "Verde", "Negro", "Blanco", "Amarillo", "Naranja", "Rosa", "Morado", "Gris"};
        char[] tallas = {'S', 'M', 'L'};
        Random random = new Random();

        String nombre = nombres[random.nextInt(nombres.length)];
        String color = colores[random.nextInt(colores.length)];
        char talla = tallas[random.nextInt(tallas.length)];
        int idVendedor = random.nextInt(100000);
        int precio = random.nextInt(100001) + 20000; // Precio aleatorio entre 20'000 y 120'000

        return new Prenda(nombre, color, talla, idVendedor, precio);
    }
    public static void buscarPrendasPorNombreHistorial(Stack<Prenda> historial, String nombre) {
        Stack<Prenda> tempStack = new Stack<>(historial.length);
        boolean encontrado = false;

        while (!historial.isEmpty()) {
            Prenda p = historial.pop();
            if (p.nombre.equals(nombre)) {
                System.out.println("Prenda encontrada: " + p);
                encontrado = true;
            }
            tempStack.push(p);
        }

        // Restaurar el historial original
        while (!tempStack.isEmpty()) {
            historial.push(tempStack.pop());
        }

        if (!encontrado) {
            System.out.println("No se encontraron prendas con ese nombre.");
        }
    }
    public static void limpiarHistorial(Stack<Prenda> historial) {
        while (!historial.isEmpty()) {
            historial.pop();
        }
    }
    public static void eliminarPrendaHistorial(Stack<Prenda> historial, String nombre) {
        Stack<Prenda> tempStack = new Stack<>(historial.length);
        boolean encontrado = false;

        while (!historial.isEmpty()) {
            Prenda p = historial.pop();
            if (!p.nombre.equals(nombre)) {
                tempStack.push(p);
            } else {
                encontrado = true;
            }
        }

        // Restaurar el historial original
        while (!tempStack.isEmpty()) {
            historial.push(tempStack.pop());
        }

        if (!encontrado) {
            System.out.println("La prenda no se encontró en el historial.");
        }
    }
    public static void eliminarOrdenLista(Queue<Order> q, String nombrePrenda) {
      int size = q.getSize();
      for (int i = 0; i < size; i++){
        Order o = q.dequeue();
        if (!o.getPrenda().nombre.equals(nombrePrenda)){
          q.enqueue(o);
        }
        else{
          System.out.println("Orden eliminada");
        }
      }
    }
    public static void modificarOrdenLista(Queue<Order> q, int id_usuario, String nombrePrenda, int nuevaCant) {
      for (int i = 0; i < q.getSize(); i++){
        Order o = q.peekAt(i);
        if (id_usuario == o.getId_usuario() && nombrePrenda.equals(o.getPrenda().nombre)){
          o.setCantidad(nuevaCant);
          System.out.println("Orden modificada");
          return;
        }
      }
      System.out.println("No se encontró la orden");
    }
    public static void addOrdenLista(Queue<Order> q, int id_usuario, Prenda prenda, int cant){
      Order o = new Order(LocalDate.now(), id_usuario, cant, prenda);
      q.enqueue(o);
      System.out.println("Orden añadida");
    }
    public static void pruebaCompletaQueue(int rep){
      System.out.println("n = 10000 : "+ avgPruebaQueue(10000, rep) + " ms");
      System.out.println("n = 100000 : "+ avgPruebaQueue(100000, rep) + " ms");
      System.out.println("n = 1000000 : "+ avgPruebaQueue(1000000, rep) + " ms");
      System.out.println("n = 10000000 : "+ avgPruebaQueue(10000000, rep) + " ms");
      System.out.println("n = 100000000 : "+ avgPruebaQueue(100000000, rep) + " ms");
    }
    public static long avgPruebaQueue(int cantidadOrdenes, int rep){
      long suma = 0;
      for(int i = 0; i < rep; i++){
        suma += tiempoPruebaQueue(cantidadOrdenes);
      }
      return suma/rep;
    }
    public static long tiempoPruebaQueue(int cantidadOrdenes){
      long start1 = System.currentTimeMillis();
      pruebaQueue(cantidadOrdenes);
      long end1 = System.currentTimeMillis();

      return end1 - start1;
    }
    public static Queue<Order> pruebaQueue(int cantidadOrdenes){
      Queue<Order> q = new Queue<Order>(cantidadOrdenes);
      for (int i = 0; i < cantidadOrdenes; i++){
        Order orden = generarOrdenAleatoria();
        q.enqueue(orden);
      }
      return q;
    }
    public static Order generarOrdenAleatoria(){
      Random rand = new Random();
      int year = rand.nextInt(3) + 2022;
      int month = rand.nextInt(12) + 1;
      int day = rand.nextInt(28) + 1;
      LocalDate fecha = LocalDate.of(year, month, day);
      int id_usuario = rand.nextInt(100000); // de hasta 5 digitos
      int cantidad = rand.nextInt(10) + 1;
      // Prenda prenda = generarPrendaAleatoria();
      return new Order(fecha, id_usuario, cantidad, generarPrendaAleatoria());
    }
}