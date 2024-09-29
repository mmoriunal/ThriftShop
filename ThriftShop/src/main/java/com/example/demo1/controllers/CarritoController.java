package com.example.demo1.Controllers;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.util.LinkedList;

import com.example.demo1.Models.Carrito;
import com.example.demo1.Models.Historial;
import com.example.demo1.Models.Prenda;


public class carritoController {
    @FXML
    private ImageView close;
    @FXML
    private AnchorPane menu;
    @FXML
    private AnchorPane menuBack;
    @FXML
    private AnchorPane slider;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    @FXML
    private Label carritoNum;
    @FXML
    private Button verTodo;
    @FXML
    private Button mainPage;
    @FXML
    private Label subtotal;

    @FXML
    private void initialize() throws IOException {
        menu.setVisible(true);
        menuBack.setVisible(false);
        slider.setTranslateX(-161);
        updateCarritoView();
        close.setOnMouseClicked(event -> closeWindow());
        menu.setOnMouseClicked(event -> slideSliderop());
        menuBack.setOnMouseClicked(event -> slideSlidercl());

    }
    public void updateSubtotalDisplay() {
        subtotal.setText("$" + Carrito.getSubtotal());
    }
    @FXML
    private void slideSliderop() {
        slider.setTranslateX(-161);
        // Crear y configurar la animación para deslizar el slider hacia afuera
        TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), slider);
        slide.setToX(0); // Mover el slider a la posición X=0
        slide.play();
        //menu.setVisible(false);
        //menuBack.setVisible(true);
        // Configurar el evento para cuando la animación termina
        slide.setOnFinished((ActionEvent e) -> {
            menu.setVisible(false);
            menuBack.setVisible(true);
        });
    }
    @FXML
    private void slideSlidercl() {
        slider.setTranslateX(0);
        // Crear y configurar la animación para deslizar el slider hacia adentro
        TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), slider);
        slide.setToX(-161); // Mover el slider a la posición X=-176
        slide.play();

        // Configurar el evento para cuando la animación termina
        slide.setOnFinished((ActionEvent e) -> {
            menu.setVisible(true);
            menuBack.setVisible(false);
        });
    }
    @FXML
    private void closeWindow() {
        // Obtener la ventana actual y cerrarla
        Stage stage = (Stage) close.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void mostrarVerTodo(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("verTodo.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) verTodo.getScene().getWindow();
        Historial.agregar("carrito.fxml");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void mostrarMainPage(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Search.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) mainPage.getScene().getWindow();
        Historial.agregar("carrito.fxml");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void mostrarCategorias(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("categorias.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) mainPage.getScene().getWindow();
        Historial.agregar("verTodo.fxml");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void updateCarritoView() {
        grid.getChildren().clear();
        carritoNum.setText("(" + Carrito.getSize() + ")");
        double totalSubtotal = 0;
        int column = 0;
        int row = 0;
        LinkedList<Prenda> prendasCarrito = Carrito.getCarrito();
        for (Prenda prenda : prendasCarrito) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("prendaprevCarrito.fxml"));
            try {
                AnchorPane anchorPane = fxmlLoader.load();
                prendaprevCarritoController prendaprevCarritoController = fxmlLoader.getController();
                prendaprevCarritoController.setData(prenda);
                prendaprevCarritoController.setMainController(this);
                grid.add(anchorPane, column, row++);
                GridPane.setMargin(anchorPane, new Insets(10));
                totalSubtotal += prenda.getPrecio();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Carrito.resetSubtotal();
        Carrito.addToSubtotal(totalSubtotal);
        updateSubtotalDisplay();
    }

}
