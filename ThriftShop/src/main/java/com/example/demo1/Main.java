package com.example.demo1;// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.*;

import com.example.demo1.models.Prenda;
import com.example.demo1.models.itemCarrito;
import com.example.demo1.services.FiltradoPrecioService;

import java.time.LocalDate;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {
    //private static List<prenda> allProd= new ArrayList<prenda>();
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Main main = new Main();  // Creación de la instancia de Main solo una vez
        //cargarPrendas("prendas.txt");

        boolean continuar = true;

        while (continuar) {
            System.out.println("Selecciona una opción:" +
                    "\n 1. Entrar al carrito de compras " +
                    "\n 2. Ver historial de prendas recientes " +
                    "\n 3. Ver lista de órdenes \n 4. Buscar " +
                    "\n 5. Separar por color \n 6. Cerrar sesión ");

            String respuesta = sc.nextLine();

            switch (respuesta) {
                case "1":
                  //  main.carritoDeCompras(sc);
                    break;
                case "2":
                  //  main.historialPrendas(sc);
                    break;
                case "3":
                  //  main.listaOrdenes(sc);
                    break;
                case "4":

                  //  main.buscarProductos(sc);
                    //continuar=false;
                    break;
                case "5":
                   // disjointSetSepararCategoria(sc);
                    break;
                case "6":
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
                    break;
            }
        }
    }
   /* public static void disjointSetSepararCategoria (Scanner sc){
            int numPrendas = 100;
            Random rand = new Random();
            prenda[] prendas = new prenda[numPrendas];
            for (int i = 0; i < numPrendas; i++) {
                prendas[i] = generarPrendaAleatoria(i);
            }
            boolean flag = true;
            while(flag){
                System.out.println("   1. Ingresar la cantidad de prendas para el programa \n  2. Calcular tiempos promedio de la ejecución con ambas implementaciones \n  3. Operaciones \n  4. Volver");
                int request = 0;
                try {
                    request = sc.nextInt();
                    sc.nextLine();
                } catch (Exception e){
                    System.out.println("Ingresa un número válido");
                    sc.nextLine();
                    continue;
                }
                switch (request){
                    case 1 :
                        System.out.println("Ingrese la cantidad de prendas para el programa");
                        numPrendas = sc.nextInt();
                        if (numPrendas <= 0) {
                            System.out.println("La cantidad de prendas debe ser positiva.");
                            sc.nextLine();
                            break;
                        }
                        prendas = new prenda[numPrendas];
                        for (int i = 0; i < numPrendas; i++) {
                            prendas[i] = generarPrendaAleatoria(i);
                        }
                        System.out.println("La cantidad ingresada fue: " + numPrendas);
                        sc.nextLine();
                        break;
                    case 2:
                        System.out.println("Los tiempos promedio son:");
                        disjointSetColorCompare(numPrendas, prendas);
                        break;
                    case 3:
                        dsColorQuantity(numPrendas, prendas, sc);
                        break;
                    case 4:
                        flag = false;
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, elige una opción entre 1 y 5.");
                        break;
                }
            }
    }
    public static void disjointSetColorCompare(int numPrendas, prenda[]prendas){
        float countunhe = 0;
        float countunpa = 0;
        float countfihe = 0;
        float countfipa = 0;
        for (int j = 0; j < 10; j++){

            Map<String, List<Integer>> prendasPorColor = new HashMap<>();
            for (int i = 0; i < numPrendas; i++) {
                prenda p = prendas[i];
                prendasPorColor.computeIfAbsent(p.getColor(), k -> new ArrayList<>()).add(i);
            }

            long startTime = System.nanoTime();
            DisjointSetUnionOnly dsUnionOnly = new DisjointSetUnionOnly(numPrendas);
            for (List<Integer> indices : prendasPorColor.values()) {
                for (int i = 1; i < indices.size(); i++) {
                    dsUnionOnly.union( indices.get(i),indices.get(0));
                }
            }
            long endTime = System.nanoTime();
            countunhe += (endTime-startTime)/ 1e6;
            //System.out.println("Tiempo de ejecución para unir conjuntos de prendas por color con union por heuristica: " + (endTime - startTime) / 1e6 + " ms");

            Map<Integer, String> representanteAColor = new HashMap<>();
            startTime =System.nanoTime();


            for (int i = 0; i < numPrendas; i++) {
                int representante = dsUnionOnly.find(i);
                representanteAColor.putIfAbsent(representante, prendas[i].getColor());
            }
            for (int i = 0; i < numPrendas; i++) {
                int representante = dsUnionOnly.find(i);
                String color = representanteAColor.get(representante);
                //System.out.println("La prenda " + i + " pertenece al conjunto de color: " + color + representante);
            }
            endTime = System.nanoTime();
            countfihe += (endTime-startTime)/ 1e6;
            //System.out.println("Tiempo de ejecución para find conjuntos de prendas por color con union por heuristica: " + (endTime - startTime) / 1e6 + " ms");


            startTime = System.nanoTime();
            DisjointSetPathCompression dsPathCom= new DisjointSetPathCompression(numPrendas);
            for (List<Integer> indices : prendasPorColor.values()) {
                for (int i = 1; i < indices.size(); i++) {
                    dsPathCom.union( indices.get(i),indices.get(0));
                }
            }
            endTime = System.nanoTime();
            countunpa += (endTime-startTime)/ 1e6;
            //System.out.println("Tiempo de ejecución para unir conjuntos de prendas por color con path compression: " + (endTime - startTime) / 1e6 + " ms");

            Map<Integer, String> representanteBColor = new HashMap<>();
            startTime = System.nanoTime();
            for (int i = 0; i < numPrendas; i++) {
                int representante = dsPathCom.find(i);
                representanteBColor.putIfAbsent(representante, prendas[i].getColor());
            }
            for (int i = 0; i < numPrendas; i++) {
                int representante = dsPathCom.find(i);
                String color = representanteBColor.get(representante);
                //System.out.println("La prenda " + i + " pertenece al conjunto de color: " + color + representante);
            }
            endTime = System.nanoTime();
            countfipa += (endTime-startTime)/ 1e6;
            //System.out.println("Tiempo de ejecución para find conjuntos de prendas por color con path compression: " + (endTime - startTime) / 1e6 + " ms");
        }
        //Calcular promedio de 10 veces
        System.out.println("Tiempo de ejecución promedio para unir conjuntos de prendas por color con union por heuristica: " + String.format("%.2f", countunhe / 10) +"  ms.");
        System.out.println("Tiempo de ejecución promedio del find de prendas por color con union por heuristica: " + String.format("%.2f", countfihe / 10) +"  ms.");
        System.out.println("Tiempo de ejecución promedio para unir conjuntos de prendas por color con path compression: " + String.format("%.2f", countunpa / 10)+"  ms.");
        System.out.println("Tiempo de ejecución promedio del find de prendas por color con path compression: " + String.format("%.2f", countfipa / 10)  + "  ms.");
    }
    public static void dsColorQuantity(int numPrendas,prenda[]prendas, Scanner sc){
        System.out.println("Elija una opción:");
        System.out.println("1. Ver cantidad de prendas por color");
        System.out.println("2. Buscar prendas por color");
        int opcion = sc.nextInt();
        sc.nextLine();
        //agregar todos los ids de
        Map<String, List<Integer>> prendasPorColor = new HashMap<>();
        for (int i = 0; i < numPrendas; i++) {
            prenda p = prendas[i];
            prendasPorColor.computeIfAbsent(p.getColor(), k -> new ArrayList<>()).add(i);
        }
        //Crear conjunto disjunto, unir los elementos que sean del mismo color
        DisjointSetPathCompression ds = new DisjointSetPathCompression(numPrendas);
        for (List<Integer> indices : prendasPorColor.values()) {
            for (int i = 1; i < indices.size(); i++) {
                ds.union( indices.get(i),indices.get(0));
            }
        }

        //LLenar hashmap con el representante de cada color.
        Map<Integer, String> representanteCColor = new HashMap<>();
        for (int i = 0; i < numPrendas; i++) {
            int representante = ds.find(i);
            representanteCColor.putIfAbsent(representante, prendas[i].getColor());
            if(representanteCColor.size() == prendasPorColor.size())
                break;
        }
        switch (opcion){
            case 1:
                //get size de cada color
                for (Map.Entry<Integer, String> entry : representanteCColor.entrySet()) {
                    String color = entry.getValue();
                    int representante = entry.getKey();
                    int tamaño = ds.getSize(ds.find(representante));
                    System.out.println("Color: " + color + " - Cantidad: " + tamaño);
                }
                break;
            case 2:
                System.out.print("Ingrese el color a buscar: ");
                String colorBuscado = sc.nextLine();
                if (prendasPorColor.containsKey(colorBuscado)) {
                    List<Integer> indices = prendasPorColor.get(colorBuscado);
                    System.out.println("Prendas del color " + colorBuscado + ":");
                    for (int index : indices) {
                        System.out.println("ID Prenda: " + index + " - " + prendas[index]);
                    }
                } else {
                    System.out.println("No se encontraron prendas con el color " + colorBuscado);
                }
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }*/
    public static List<Prenda> buscarProductos(String query) {
        String[] keywords = query.toLowerCase().split(" ");

        List<Prenda> resultados = new ArrayList<>();
        List<Prenda> allProd = HelloApplication.getPrendas();
        for (int i = 0; i < allProd.size(); i++) {
            Prenda producto = allProd.get(i);
            if   (keywords.length>1){
                if (producto.nombre.toLowerCase().contains(keywords[0]) &&
                        producto.color.toLowerCase().contains(keywords[1])) {
                        resultados.add(producto);
                }}
            else{
                if (producto.nombre.toLowerCase().contains(keywords[0])) {
                    resultados.add(producto);
        }
        } }

       //printresult(resultados);
        //ordFilt(resultados);
        return resultados;
    }
    public static void ordFilt(List<Prenda> resultados){
        FiltradoPrecioService filtrado = new FiltradoPrecioService();
        boolean cont=true;
        /*while(cont){
        System.out.println("Desea: \n 1.Ordenar \n 2.Filtrar");
        String respuesta = sc.nextLine();
        switch (respuesta) {
            case "1":
               sortheap(sc,resultados);
                break;
            case "2":
                System.out.println("Inserte limite inferior del precio:");
                int n = sc.nextInt();
                System.out.println("Inserte limite superior del precio:");
                int m = sc.nextInt();

                //filtrado.filtradoPorPrecioBST(resultados,n, m);
               // filtrado.filtradoPorPrecioAVL(resultados,n, m);
                filtrado.testPerformance( allProd, 50000 , 10,resultados);
                break;
            default:
                System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
                break;
        }
    }}*/}
    /*public void sortheap(Scanner sc,List<Prenda> resultados){
        System.out.println("\n 1.Ordenar de menor a mayor \n 2.Ordenar de mayor a menor");
        String ans = sc.nextLine();
        switch (ans) {
            case "1":
                MinHeap heap = new MinHeap();
                //MinQuadHeap heap = new MinQuadHeap();
                for(int i=0;i<resultados.size();i++){
                    heap.insertKey(resultados.get(i));
                }

                int res = heap.current_heap_size;
                System.out.println(res +" Resultados encontrados:");
                while (!heap.isEmpty()) {
                    prenda p = heap.extractMin();
                    System.out.println(p);
                }

                break;
            case "2":
                MaxHeap heapmax = new MaxHeap();
                //MaxQuadHeap heapmax = new MaxQuadHeap();
                for (int i = 0; i < resultados.size(); i++) {
                    heapmax.insertKey(resultados.get(i));
                }
                int res2 = heapmax.current_heap_size;
                System.out.println(res2 +" Resultados encontrados:");
                while (!heapmax.isEmpty()) {
                    prenda p = heapmax.extractMax();
                    System.out.println(p);
                }
                break;
            default:
                System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
                break;
        }
    }
    public static void printresult(List<Prenda>resultados){
        if (resultados.size()==0) {
            System.out.println("Prenda no encontrada");
        } else {
            int res = resultados.size();
            System.out.println(res +" Resultados encontrados:");
            for (int i = 0; i < resultados.size(); i++) {
                prenda p = resultados.get(i);
                System.out.println(p);
            }}
    }

    private static void cargarPrendas(String nombreArchivo) {
        try (Scanner fileScanner = new Scanner(new File(nombreArchivo))) {
            int id = 0;
            while (fileScanner.hasNextLine()) {
                String linea = fileScanner.nextLine();
                String[] partes = linea.split(" ");
                if (partes.length == 5) {
                    String nombre = partes[0];
                    String color = partes[1];
                    char talla = partes[2].charAt(0);
                    int id_vendedor = Integer.parseInt(partes[3]);
                    int precio = Integer.parseInt(partes[4]);

                    prenda producto = new prenda(id, nombre, color, talla, id_vendedor, precio);
                    id++;
                    allProd.add(producto);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error: No se encontró el archivo " + nombreArchivo);
        }
    }

    public void carritoDeCompras(Scanner sc){
        linkedList<itemCarrito> carrito = new linkedList<>();
        boolean continuar = true;

        while (continuar) {
            System.out.println("Selecciona una opción:\n 1. Agregar prenda al carrito\n 2. Modificar la cantidad de algún elemento del carrito\n 3. Eliminar algún elemento del carrito\n 4. Ver el carrito\n 5. Salir del carrito");

            String respuesta = sc.nextLine();

            switch (respuesta) {
                case "1":
                    System.out.println("Escribe la cantidad de elementos que deseas agregar al carrito: ");
                    int cantidadPrendas = sc.nextInt();
                    sc.nextLine();

                    for (int i = 0; i < cantidadPrendas; i++) {
                        itemCarrito nuevoItem = new itemCarrito(generarPrendaAleatoria(i), 1);
                        carrito.pushBack(nuevoItem);
                    }
                    System.out.println(cantidadPrendas + " prenda(s) agregada(s) al carrito.");
                    break;

                case "2":
                    System.out.println("Ingrese el número del elemento que desea modificar: ");
                    int indiceModificar = sc.nextInt() - 1;
                    sc.nextLine();
                    System.out.println("Ingrese la nueva cantidad: ");
                    int nuevaCantidad = sc.nextInt();
                    sc.nextLine();
                    carrito.setCantidad(indiceModificar, nuevaCantidad);
                    System.out.println("El elemento modificado fue: ");
                    System.out.println(carrito.getData(indiceModificar));
                    break;

                case "3":
                    System.out.println("Ingrese el número del elemento que desea eliminar: ");
                    int indiceEliminar = sc.nextInt() - 1;
                    String elementoEliminado = carrito.getData(indiceEliminar);
                    carrito.delete(indiceEliminar);
                    sc.nextLine();
                    System.out.println("El elemento eliminado fue: ");
                    System.out.println(elementoEliminado);
                    break;

                case "4":
                    carrito.getData();
                    break;

                case "5":
                    continuar = false;
                    System.out.println("Saliendo del carrito");
                    break;

                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción válida.");
                    break;
            }
        }
    }

    public void historialPrendas(Scanner sc){
        System.out.println("Escribe la cantidad de datos que deseas añadir al historial: ");
        int cantidadPrendas = sc.nextInt();
        sc.nextLine();
        stack<prenda> historialPrendas = new stack<prenda>(cantidadPrendas);
        for (int i = 0; i < cantidadPrendas; i++) {
            prenda nuevaPrenda = generarPrendaAleatoria(i);
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
        Random random = new Random();
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

            prenda prenda = generarPrendaAleatoria(random.nextInt(100));
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



    public static prenda generarPrendaAleatoria(int id) {
        String[] nombres = {"Pantalon", "Camiseta", "Sudadera", "Falda", "Chaqueta", "Vestido", "Blusa", "Pantaloneta", "Zapatos", "Sandalias"};
        String[] colores = {"Azul", "Rojo", "Verde", "Negro", "Blanco", "Amarillo", "Naranja", "Rosa", "Morado", "Gris"};
        char[] tallas = {'S', 'M', 'L'};
        Random random = new Random();

        String nombre = nombres[random.nextInt(nombres.length)];
        String color = colores[random.nextInt(colores.length)];
        char talla = tallas[random.nextInt(tallas.length)];
        int idVendedor = random.nextInt(100000);
        int precio = random.nextInt(100000) + 20000; // Precio aleatorio entre 500 y 50500

        return new prenda(id, nombre, color, talla, idVendedor, precio);
    }
    public static void buscarPrendasPorNombreHistorial(stack<prenda> historial, String nombre) {
        stack<prenda> tempStack = new stack<>(historial.length);
        boolean encontrado = false;

        while (!historial.isEmpty()) {
            prenda p = historial.pop();
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
    public static void limpiarHistorial(stack<prenda> historial) {
        while (!historial.isEmpty()) {
            historial.pop();
        }
    }
    public static void eliminarPrendaHistorial(stack<prenda> historial, String nombre) {
        stack<prenda> tempStack = new stack<>(historial.length);
        boolean encontrado = false;

        while (!historial.isEmpty()) {
            prenda p = historial.pop();
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
    public static void addOrdenLista(Queue<Order> q, int id_usuario, prenda prenda, int cant){
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
        return new Order(fecha, id_usuario, cantidad, generarPrendaAleatoria(rand.nextInt()));
    }
}

/*class prenda implements Comparable<prenda>{
    String nombre, color;
    char talla;
    int id_vendedor, precio, id;
    public prenda(int id, String nombre, String color, char talla, int id_vendedor, int precio){
        this.id = id;
        this.nombre = nombre;
        this.color = color;
        this.talla = talla;
        this.id_vendedor = id_vendedor;
        this.precio = precio;
    }
    public String toString(){
        return "Nombre: " + nombre + " Color: " + color + " Talla: " + talla + " Id_Vendedor: " + id_vendedor + " Precio: " + precio;
    }
    public String getColor(){
        return color;
    }
    public int compareTo(prenda otraPrenda) {
        return Integer.compare(this.precio, otraPrenda.precio);
    }
}*/}
class Order{
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
class linkedList <T>{
    Node head;
    Node tail;

    static class Node <T> {
        T data;
        Node next;
        Node(T d){
            data = d;
            next = null;
        }
    }
    public void pushFront(T data){
        Node newNode = new Node(data);
        if (head == null){
            head = newNode;
            tail = newNode;
        } else {
            head.next = newNode;
        }
    }
    public void pushBack(T data){
        Node newNode = new Node(data);
        if (head == null){
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
    }
    public void popFront(){
        if (head == null){
            System.out.println("La lista está vacía");
        } else {
            head = head.next;
        }
    }
    public void popBack(){
        Node p =  head;
        if (head == null){ //la lista está vacía
            System.out.println("No se puede hacer");
        } else if (p == tail) { //la lista tiene un solo elemento
            head = null;
            tail = null;
        } else { //Caso general
            while (p.next != tail){
                p = p.next;
            }
            tail = p;
            p.next = null;
        }
    }
    public void delete(int index) {
        if (head == null) {
            System.out.println("La lista está vacía");
            return;
        }

        if (index == 0) {
            head = head.next;
            return;
        }

        Node<T> current = head;
        Node<T> previous = null;
        for (int i = 0; i < index; i++) {
            if (current == null) {
                System.out.println("El índice está fuera de rango");
                return;
            }
            previous = current;
            current = current.next;
        }

        if (current == null) {
            System.out.println("El índice está fuera de rango");
            return;
        }

        previous.next = current.next;
    }

    public void add(T data, int position) {

        Node<T> newNode = new Node<>(data);
        if (position == 0) { // Insertar al principio de la lista
            newNode.next = head;
            head = newNode;
            if (tail == null) { // Si la lista está vacía, también actualizamos el tail
                tail = newNode;
            }
        } else {
            Node<T> current = head;
            for (int i = 0; i < position - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
            if (newNode.next == null) { // Si el nuevo nodo se inserta al final, actualizamos el tail
                tail = newNode;
            }
        }
    }

    public void getData(){
        Node p = head;
        if (head == null){
            System.out.println("La lista se encuentra vacía");
        } else {
            System.out.print("[");
            while (p != null){
                if (p.next == null){
                    System.out.println(p.data + "]");
                } else {
                    System.out.print(p.data + ",");
                }
                p = p.next;
            }
        }
    }
    public void topFront(){
        if (head == null){
            System.out.println("La lista se encuentra vacía");
        } else {
            System.out.println(head.data);
        }
    }
    public T topBack(){
        if (tail == null)
            return null;
        else {
            System.out.println((T) tail.data);
            return (T) tail.data;
        }
    }
    public boolean find(T data){
        Node p = head;
        if (head == null)
            return false;
        if (p == data)
            return true;
        while (p.next != null){
            if (p.data == data){
                System.out.print("true");
                return true;
            }
            System.out.print("false");
            return false;
        }
        return false;
    }
    public boolean isEmpty(){
        if (head==null) return true;
        else return false;
    }
    public int size() {
        int count = 0;
        Node current = head;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    public void setCantidad(int index, int nuevaCantidad) {
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        ((itemCarrito) current.data).setCantidad(nuevaCantidad);
    }
    public String getData(int index) {
        Node<T> current = head;
        int currentIndex = 0;
        while (current != null) {
            if (currentIndex == index) {
                return current.data.toString();
            }
            current = current.next;
            currentIndex++;
        }
        return null; // Si no se encuentra el índice
    }
}
class Queue <T> {
    private T[] elements;
    private int size;
    private int front;
    private int rear;

    public Queue(int maxsize) {
        @SuppressWarnings("unchecked") T[] elements = (T[]) new Object[maxsize];
        this.elements = elements;
        size = 0;
        front = 0;
        rear = 0;
    }

    public void enqueue(T item) {
        if (isFull()) {
            System.out.println("La cola está llena");
            return;
        }
        elements[rear] = item;
        rear = (rear + 1) % elements.length;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            System.out.println("La cola está vacía");
            return null;
        }
        T item = elements[front];
        front = (front + 1) % elements.length;
        size--;
        return item;
    }

    public T peek() {
        if (isEmpty()) {
            System.out.println("La cola está vacía");
            return null;
        }
        return elements[front];
    }

    public T peekAt(int index){
        if (index < 0 || index >= size) {
            System.out.println("Indice fuera de limites");
            return null;
        }
        return elements[(front + index) % elements.length];
    }

    public void printQueue(){
        for (int i = 0; i < size; i++) {
            System.out.println(peekAt(i));
        }
    }

    public boolean isFull(){
        return size == elements.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }
}
class stack <T>{
    private T[] pila;
    private int top;
    public int length;

    public stack(int length){
        this.length = length;
        pila = (T[]) new Object[length];
        top = 0;
    }
    public void push(T nuevo){
        if (isFull())
            throw new IllegalStateException("La pila está llena");
        else {
            pila[top] = nuevo;
            top++;
        }
    }
    public T pop(){
        if (isEmpty())
            return null;
        else {
            top--;
            return pila[top];
        }
    }

    public boolean isEmpty(){
        return top==0;
    }
    public boolean isFull(){
        return top==length;
    }
    public T top(){
        if (isEmpty())
            return null;
        else {
            return pila[top-1];
        }
    }
    public void verStack() {
        for (int i = top - 1; i >= 0; i--) {
            System.out.println(pila[i]);
        }
    }
}
class DisjointSetUnionOnly {
    private int[] parent;
    private int[] rank;
    private int[] size;
    public DisjointSetUnionOnly(int n) {
        parent = new int[n];
        rank = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
            size[i] = 1;
        }
    }
    public int find(int a) {
        while (parent[a] != a) {
            a = parent[a];
        }
        return a;
    }
    public void union(int a, int b) {
        int root1 = find(a);
        int root2 = find(b);
        if (root1 == root2)
            return;
        if (rank[root1] > rank[root2]){
            parent[root2] = root1;
            size[root1] += size[root2];
        } else {
            parent[root1] = root2;
            size[root2] += size[root1];
            if (rank[root1] == rank[root2]){
                rank[root2] += 1;
            }
        }
    }
    public int getSize(int p) {
        int root = find(p);
        return size[root];
    }
}
class DisjointSetPathCompression {
    private int[] parent;
    private int[] rank; // Unión por tamaño
    private int[] size;

    public DisjointSetPathCompression(int n) {
        parent = new int[n];
        rank = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
            size[i] = 1;
        }
    }

    // Path Compression
    public int find(int p) {
        if (parent[p] != p) {
            parent[p] = find(parent[p]); // Path compression
        }
        return parent[p];
    }

    // Unión por tamaño
    public void union(int a, int b) {
        int root1 = find(a);
        int root2 = find(b);
        if (root1 == root2)
            return;
        if (rank[root1] > rank[root2]){
            parent[root2] = root1;
            size[root1] += size[root2];
        } else {
            parent[root1] = root2;
            size[root2] += size[root1];
            if (rank[root1] == rank[root2]){
                rank[root2] += 1;
            }
        }
    }
    public int getSize(int p) {
        int root = find(p);
        return size[root];
    }
}

