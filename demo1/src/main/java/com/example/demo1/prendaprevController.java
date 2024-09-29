package com.example.demo1;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class prendaprevController {

    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;

    @FXML
    private ImageView image;
    @FXML
    private Label verLabel;

    private Prenda prenda;
    private String origen;


    public void setData(Prenda prenda, String origen){
        this.prenda = prenda;
        this.origen = origen;
        nameLabel.setText(prenda.getNombre() + " " + prenda.getColor());
        priceLabel.setText("$" + prenda.getPrecio());
        Image imagen = new Image(getClass().getResourceAsStream(prenda.getFotoPath()));
        image.setImage(imagen);
    }
    @FXML
    public void handleVerLabelClick(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("prendaview.fxml")); // Cambia a la ruta correcta
            Parent root = loader.load();
            Historial.agregar(origen + ".fxml");
            prendaViewController controller = loader.getController();
            controller.setData(prenda);

            Stage stage = (Stage) verLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}