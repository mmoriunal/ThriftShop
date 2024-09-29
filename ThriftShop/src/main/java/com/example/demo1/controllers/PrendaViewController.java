package com.example.demo1.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.LinkedList;

import com.example.demo1.Models.Carrito;
import com.example.demo1.Models.Historial;
import com.example.demo1.Models.Prenda;
import com.example.demo1.Services.HistorialEntry;

public class prendaViewController {
    @FXML
    private ImageView close;

    @FXML
    private AnchorPane menuBack;

    @FXML
    private AnchorPane slider;

    @FXML
    private ImageView back;

    @FXML
    private Label nombreLabel;

    @FXML
    private ImageView image;

    @FXML
    private Label priceLabel;

    @FXML
    private Label sizeLabel;

    @FXML
    private Label sellerLabel;
    @FXML
    private ImageView carritoImage;

    @FXML
    private Label carritoLabel;
    //NEW NEWNEWNEWNEWNEWNEWNWENENWNENWENWNENW
    @FXML
    private Label carritoNum;
    @FXML
    private AnchorPane carrito;
    //NEW NEWNEWNEWNEWNEWNEWNWENENWNENWENWNENW


    private Prenda prendaActual;

    public void setData(Prenda prenda) {
        this.prendaActual = prenda;
        nombreLabel.setText(prenda.getNombre() + " " + prenda.getColor());
        priceLabel.setText("$" + prenda.getPrecio());
        sizeLabel.setText("Talla: " + prenda.getTalla());
        sellerLabel.setText("Vendedor: " + prenda.getId_vendedor());
        Image imagen = new Image(getClass().getResourceAsStream(prenda.getFotoPath()));
        image.setImage(imagen);
    }
    @FXML
    private void initialize(){
        close.setOnMouseClicked(event -> closeWindow());
        carritoImage.setOnMouseClicked(event -> handleAddToCart());
        //NEW NEWNEWNEWNEWNEWNEWNWENENWNENWENWNENW
        carritoNum.setText("(" + Carrito.getSize() + ")");
        //NEW NEWNEWNEWNEWNEWNEWNWENENWNENWENWNENW
    }

    @FXML
    private void closeWindow() {
        // Obtener la ventana actual y cerrarla
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void handleBackClick(MouseEvent event) {
        if (Historial.hayHistorial()) {
            HistorialEntry ultimaEntrada = Historial.atras();
            if (ultimaEntrada != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(ultimaEntrada.getFxmlFile()));
                    Parent root = loader.load();
                    Stage stage = (Stage) image.getScene().getWindow();
                    stage.setScene(new Scene(root));

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("No hay páginas anteriores en el historial.");
        }
    }
    @FXML
    private void handleAddToCart() {
        Carrito carrito = Carrito.getInstancia();
        if (prendaActual != null) {
            if (!carrito.contienePrenda(prendaActual)) {
                carrito.agregarPrenda(prendaActual);
                carritoLabel.setVisible(true);
                carritoLabel.setText("Prenda añadida al carrito: " + prendaActual.getNombre());
                //NEW NEWNEWNEWNEWNEWNEWNWENENWNENWENWNENW
                carritoNum.setText("(" + Carrito.getSize() + ")");
                //NEW NEWNEWNEWNEWNEWNEWNWENENWNENWENWNENW
                //System.out.println("Prenda añadida al carrito: " + prendaActual.getNombre());
            } else {
                carritoLabel.setVisible(true);
                carritoLabel.setText("Esta prenda ya fue añadida al carrito.");
                //System.out.println("Esta prenda ya fue añadida al carrito.");
            }
        } else {
            //System.out.println("No hay prenda actual para añadir al carrito.");
        }
    }
    @FXML
    private void mostrarCarrito(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("carrito.fxml"));
        Historial.agregar("prendaViewController.fxml",prendaActual);
        Parent root = loader.load();
        Stage stage = (Stage) carrito.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
