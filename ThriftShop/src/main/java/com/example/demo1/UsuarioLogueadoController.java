package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class UsuarioLogueadoController {
    @FXML
    private Label lblUsuario;
    @FXML
    private Label lblIdUsuario; // Nuevo Label para la ID del usuario
    

    public void setUsuario(Usuario usuario) {
        lblUsuario.setText("Usuario logueado: " + usuario.getNombre());
        lblIdUsuario.setText("ID del usuario: " + usuario.getIdUsuario()); // Mostrar la ID
    }
}
