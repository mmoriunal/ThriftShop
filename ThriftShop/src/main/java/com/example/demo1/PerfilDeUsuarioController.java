package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class PerfilDeUsuarioController {

    @FXML
    private ImageView CloseB,Back;

    @FXML
    private Label lblIdUsuario;

    @FXML
    private Label lblUsuario;

    // Método para inicializar la vista con los datos del usuario
    public void initialize(Usuario usuario) {
        lblIdUsuario.setText("ID de Usuario: " + usuario.getIdUsuario());
        lblUsuario.setText("Usuario: " + usuario.getNombre());
    }

    @FXML
    private void handleSalir() {
        Stage stage = (Stage) lblIdUsuario.getScene().getWindow();
        stage.close(); // Cierra la ventana
    }

    @FXML
    public void handleBackClick(MouseEvent event) {
        if (Historial.hayHistorial()) {
            HistorialEntry ultimaEntrada = Historial.atras();
            if (ultimaEntrada != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(ultimaEntrada.getFxmlFile()));
                    Parent root = loader.load();
                    Stage stage = (Stage) Back.getScene().getWindow();
                    stage.setScene(new Scene(root));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No hay páginas anteriores en el historial.");
        }
    }
}
