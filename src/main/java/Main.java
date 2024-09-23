import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static HashMapUsuario usuarios = new HashMapUsuario();
    private static Scanner sc = new Scanner(System.in);
    private static final String FILE_NAME = "src/data/usuarios.txt";
    private static int idCounter = 1;

    public static void main(String[] args) {
        cargarUsuariosDesdeArchivo();

        while (true) {
            System.out.println("\nOpciones:");
            System.out.println("1. Crear usuario");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion;
            try{
                opcion = sc.nextInt();
            }
            catch (InputMismatchException e){
                sc.nextLine();
                System.out.println("Ingrese una opción numérica");
                continue;
            }
            sc.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    crearUsuario();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    System.out.println("Saliendo del sistema...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private static void crearUsuario() {
        System.out.print("Ingresa un nombre de usuario: ");
        String nombre = sc.nextLine();

        if (usuarios.containsKey(nombre)) {
            System.out.println("El usuario ya existe.");
            return;
        }

        System.out.print("Ingresa una contraseña: ");
        String password = sc.nextLine();

        Usuario nuevoUsuario = new Usuario(idCounter++, nombre, password);
        usuarios.put(nombre, nuevoUsuario);

        // Guardar en archivo
        guardarUsuarioEnArchivo(nuevoUsuario);
        System.out.println("Usuario creado exitosamente. ID de usuario: " + nuevoUsuario.getIdUsuario());
    }

    private static void iniciarSesion() {
        System.out.print("Ingresa tu nombre de usuario: ");
        String nombre = sc.nextLine();

        if (!usuarios.containsKey(nombre)) {
            System.out.println("Usuario no encontrado.");
            return;
        }

        System.out.print("Ingresa tu contraseña: ");
        String password = sc.nextLine();

        Usuario usuario = usuarios.get(nombre);
        if (usuario.verifyPassword(password)) {
            System.out.println("Inicio de sesión exitoso. ID de usuario: " + usuario.getIdUsuario());
        } else {
            System.out.println("Contraseña incorrecta.");
        }
    }

    private static void guardarUsuarioEnArchivo(Usuario usuario) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true); 
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(usuario.toFileString());
        } catch (IOException e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
        }
    }

    private static void cargarUsuariosDesdeArchivo() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String nombre = parts[1];
                    String hashedPassword = parts[2];
                    Usuario usuario = new Usuario(id, nombre, hashedPassword, true);
                    usuarios.put(nombre, usuario);
                    idCounter = Math.max(idCounter, id + 1); // Para evitar repetir IDs
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo: " + e.getMessage());
        }
    }
}