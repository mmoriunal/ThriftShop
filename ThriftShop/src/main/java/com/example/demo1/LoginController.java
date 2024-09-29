package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LoginController {
    @FXML
    private ImageView CloseB;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField newUsernameField;

    @FXML
    private PasswordField newPasswordField;

    private Usuario usuarioLogueado;

    private static final String FILE_NAME = "ThriftShop\\src\\main\\resources\\usuarios.txt";
    static int idCounter = 1;
    static HashMapUsuario usuarios = new HashMapUsuario();

    private static List<Prenda> listaDePrendas;
    private static List<Usuario> listaDeUsuarios;

    public LoginController() {
        cargarUsuariosDesdeArchivo();
    }

    @FXML
    private void handleLogin() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        Usuario usuario = usuarios.get(username);
        if (usuario != null && usuario.verifyPassword(password)) {
            // Login exitoso
            try {
                usuarioLogueado = usuario;
                cargarBusqueda(); // Cargar la escena de búsqueda
            } catch (IOException e) {
                e.printStackTrace();
                showAlert("Error", "No se pudo cargar la vista de búsqueda.");
            }
        } else {
            // Login fallido
            showAlert("Error de inicio de sesión", "Nombre de usuario o contraseña incorrectos.");
        }
    }
    @FXML
    public void handleCreateUser() {
        try {
            // Cargar el nuevo FXML de registro
            FXMLLoader loader = new FXMLLoader(getClass().getResource("register.fxml"));
            Parent registerView = loader.load();
            
            // Cambiar la escena a la vista de registro
            Historial.agregar("Login.fxml");
            Scene scene = new Scene(registerView);
            Stage stage = (Stage) CloseB.getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void closeWindow() {

        Stage stage = (Stage) CloseB.getScene().getWindow();
        stage.close();
    }

    public static void guardarUsuarioEnArchivo(Usuario usuario) {
        // Cargar todos los usuarios
        List<Usuario> allUsers = usuarios.getValues();

        // Buscar si el usuario ya existe
        boolean userExists = false;
        for (Usuario existingUser : allUsers) {
            if (existingUser.getIdUsuario() == usuario.getIdUsuario()) {
                userExists = true;
                break;
            }
        }

        // Escribir en el archivo
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Usuario existingUser : allUsers) {
                if (existingUser.getIdUsuario() == usuario.getIdUsuario()) {
                    writer.write(usuario.toFileString()); // Reemplazar la entrada del usuario
                } else {
                    writer.write(existingUser.toFileString()); // Mantener la entrada original
                }
                writer.newLine();
            }
            if (!userExists) {
                writer.write(usuario.toFileString()); // Si el usuario no existía, agregarlo al final
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el usuario: " + e.getMessage());
        }
    }

    

    private void cargarBusqueda() throws IOException {
        // Cargar la escena de búsqueda
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Search.fxml"));
        Parent root = fxmlLoader.load();

        Search searchController = fxmlLoader.getController();
        searchController.setUsuarioLogueado(usuarioLogueado);
        
        // Obtener la ventana actual y cambiar la escena
        Stage stage = (Stage) usernameField.getScene().getWindow();

        listaDePrendas = getPrendas();
        Scene scene = new Scene(root);
        
        stage.setTitle("ThriftShop");
        stage.setScene(scene);
        stage.show();
    }

    public static List<Prenda> getPrendas(){
        List<Prenda> listaDePrendas = new ArrayList<>();
        try (InputStream inputStream = HelloApplication.class.getClassLoader().getResourceAsStream("prendas.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Prenda pr = Prenda.fromString(line);
                listaDePrendas.add(pr);
                //System.out.println(pr.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaDePrendas;
    }

    public static List<Prenda> getListaDePrendas() { // Método para acceder a la lista de prendas
        return listaDePrendas;
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
                    Usuario usuario = new Usuario(id, nombre, hashedPassword, null, true);
                    usuarios.put(nombre, usuario);
                    idCounter = Math.max(idCounter, id + 1); // Para evitar repetir IDs
                }
                if (parts.length == 4) {
                    int id = Integer.parseInt(parts[0]);
                    String nombre = parts[1];
                    String hashedPassword = parts[2];
                    String profileImagePath = parts[3];
                    Usuario usuario = new Usuario(id, nombre, hashedPassword, profileImagePath, true);
                    usuarios.put(nombre, usuario);
                    idCounter = Math.max(idCounter, id + 1); // Para evitar repetir IDs
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo: " + e.getMessage());
        }
    
        // Print loaded users for debugging
        for (Usuario n : usuarios.getValues()) {
            System.out.println(n.toFileString());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}