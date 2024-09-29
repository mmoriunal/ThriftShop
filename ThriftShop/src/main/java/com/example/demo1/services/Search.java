package com.example.demo1.services;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.example.demo1.HelloApplication;
import com.example.demo1.controllers.PrendaPrevController;
import com.example.demo1.models.Carrito;
import com.example.demo1.models.Historial;
import com.example.demo1.models.Prenda;

public class Search extends PrendaPrevController{
    @FXML
    private TextField Query;
    @FXML
    private ImageView CloseB;
    @FXML
    private Button SearchButton,NewP;
    @FXML
    private ImageView menu;

    @FXML
    private ImageView menuBack;

    @FXML
    private AnchorPane slider;
    @FXML
    private AnchorPane carrito;
    @FXML
    private Label carritoNum;

    @FXML
    private Button verTodo;
    String query;
    static List<Prenda> resultados;
    @FXML
    private void initialize(){
        menu.setVisible(true);
        menuBack.setVisible(false);
        slider.setTranslateX(-161);
        carritoNum.setText("(" + Carrito.getSize() + ")");
        //CloseB.setOnMouseClicked(event -> closeWindow());
        menu.setOnMouseClicked(event -> slideSliderop());
        menuBack.setOnMouseClicked(event -> slideSlidercl());
    }

    public void SearchB(ActionEvent event) throws IOException{
        query = Query.getText();
        buscarProductos(query);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchRes.fxml"));
        Historial.agregar("Search.fxml");
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static List<Prenda> buscarProductos(String query) {
        String[] keywords = query.toLowerCase().split(" ");
        resultados = new ArrayList<>();
        List<Prenda> allProd = HelloApplication.getListaDePrendas() ;
        for (int i = 0; i < allProd.size(); i++) {
            Prenda producto = allProd.get(i);
            if   (keywords.length>1){
                if (producto.nombre.toLowerCase().contains(keywords[0]) &&
                        producto.color.toLowerCase().contains(keywords[1])) {
                    resultados.add(producto);
                }}
            else{
                if (producto.nombre.toLowerCase().contains(keywords[0])) {
                    resultados.add(producto);
                }
            } }
        return resultados;
    }

    public void NuevaPrenda(ActionEvent event) throws IOException{

        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewPrenda.fxml")); // Cambia a la ruta correcta
        Parent root = loader.load();
        Historial.agregar("Search.fxml");
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void mostrarVerTodo(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("verTodo.fxml")); // Cambia a la ruta correcta
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void mostrarCarrito(MouseEvent event) throws IOException {
        // Cargar el FXML del carrito
        FXMLLoader loader = new FXMLLoader(getClass().getResource("carrito.fxml"));
        Parent root = loader.load();

        // Obtener la ventana actual
        Stage stage = (Stage) carrito.getScene().getWindow();

        // Crear una nueva escena y establecerla
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void mostrarCategorias(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("categorias.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) carrito.getScene().getWindow();
        Historial.agregar("Search.fxml");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    public void closeWindow() {

        Stage stage = (Stage) CloseB.getScene().getWindow();
        stage.close();
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
}
