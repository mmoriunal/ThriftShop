package com.example.demo1.Controllers;

import com.example.demo1.Models.Carrito;
import com.example.demo1.Models.Historial;
import com.example.demo1.Models.Prenda;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class prendaprevCarritoController extends prendaprevController{
    @FXML
    private Label nameLabel;

    @FXML
    private Label priceLabel;
    @FXML
    private CheckBox check;
    @FXML
    private ImageView image;

    private Prenda prenda;
    private carritoController mainController;

    public void setMainController(carritoController controller) {
        this.mainController = controller;
    }

    public void setData(Prenda prenda){
        super.setData(prenda,"carrito");
        this.prenda = prenda;
        nameLabel.setText(prenda.getNombre() + " " + prenda.getColor());
        priceLabel.setText("$" + prenda.getPrecio());
        Image imagen = new Image(getClass().getResourceAsStream(prenda.getFotoPath()));
        image.setImage(imagen);
    }
    @FXML
    public void handleVerLabel(MouseEvent event) {
        super.handleVerLabelClick(event);
        Historial.agregar("carrito.fxml");
    }
    @FXML
    private void handleCheckBoxAction() {
        double precio = prenda.getPrecio();
        if (check.isSelected()) {
            Carrito.addToSubtotal(precio); // Método para añadir al subtotal
        } else {
            Carrito.subtractFromSubtotal(precio); // Método para restar del subtotal
        }
        if (mainController != null) {
            mainController.updateSubtotalDisplay();
        }

    }
    @FXML
    private void handleEliminarAction() {
        if (mainController != null) {
            Carrito.removeFromCarrito(prenda);
            mainController.updateCarritoView();
        }
    }
}
