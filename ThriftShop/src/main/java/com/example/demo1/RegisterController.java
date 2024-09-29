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

public class RegisterController {
    @FXML
    private ImageView CloseB, Volver;

    @FXML
    private TextField newUsernameField;

    @FXML
    private PasswordField newPasswordField;



    @FXML
    public void handleRegister() {
        String nombre = newUsernameField.getText();
        String password = newPasswordField.getText();

        // Validar que los campos no estén vacíos
        if (nombre.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Los campos de nombre de usuario y contraseña no pueden estar vacíos.");
            return;
        }

        // Verificar si el usuario ya existe
        if (LoginController.usuarios.containsKey(nombre)) {
            showAlert("Error", "El nombre de usuario ya está en uso. Por favor elige otro.");
            return;
        }

        Usuario nuevoUsuario = new Usuario(LoginController.idCounter++, nombre, password, null);
        LoginController.usuarios.put(nombre, nuevoUsuario);
        LoginController.guardarUsuarioEnArchivo(nuevoUsuario);
        System.out.println("Usuario creado exitosamente. ID de usuario: " + nuevoUsuario.getIdUsuario());
    }
    
    @FXML
    private void closeWindow() {
        Stage stage = (Stage) CloseB.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void handleBack() {
        if (Historial.hayHistorial()) {
            HistorialEntry ultimaEntrada = Historial.atras();
            if (ultimaEntrada != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(ultimaEntrada.getFxmlFile()));
                    Parent root = loader.load();
                    Stage stage = (Stage) (CloseB.getScene().getWindow());
                    stage.setScene(new Scene(root));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No hay páginas anteriores en el historial.");
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
