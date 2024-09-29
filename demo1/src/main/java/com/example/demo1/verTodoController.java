package com.example.demo1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
import javafx.animation.TranslateTransition;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.net.URL;
import java.util.ResourceBundle;

public class verTodoController {
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
    private AnchorPane carrito;
    @FXML
    private ImageView image;

    private List<Prenda> prendas = new ArrayList<>();
    @FXML
    private void initialize() throws IOException {
        menu.setVisible(true);
        menuBack.setVisible(false);
        slider.setTranslateX(-161);
        String link = "prendaprev.fxml";
        gridView(link);
        close.setOnMouseClicked(event -> closeWindow());
        menu.setOnMouseClicked(event -> slideSliderop());
        menuBack.setOnMouseClicked(event -> slideSlidercl());
    }

    private void gridView(String link) throws IOException {
        carritoNum.setText("(" + Carrito.getSize() + ")");
        List<Prenda> prendas = HelloApplication.getListaDePrendas();
        int column = 0;
        int row = 0;
        for (int i = 0; i < 50;i++){
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource(link));
            AnchorPane anchorPane = fxmlLoader.load();
            prendaprevController prendaprevController = fxmlLoader.getController();
            prendaprevController.setData(prendas.get(i),"verTodo");
            grid.add(anchorPane,column, row++);
            GridPane.setMargin(anchorPane, new Insets(10));
        }
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
    private void mostrarCarrito(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("carrito.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) carrito.getScene().getWindow();
        Historial.agregar("verTodo.fxml");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void mostrarCategorias(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("categorias.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) carrito.getScene().getWindow();
        Historial.agregar("verTodo.fxml");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void mostrarMainPage(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Search.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) carrito.getScene().getWindow();
        Historial.agregar("verTodo.fxml");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
