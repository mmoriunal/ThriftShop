package com.example.demo1;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class categoriasController {

    @FXML
    private ImageView close;

    @FXML
    private AnchorPane menu;

    @FXML
    private AnchorPane menuBack;

    @FXML
    private AnchorPane carrito;

    @FXML
    private Label carritoNum;

    @FXML
    private TreeView<String> cattree;

    @FXML
    private AnchorPane slider;

    @FXML
    private Button mainPage;

    @FXML
    private Button verTodo;

    @FXML
    private Button categorias;
    @FXML
    private AnchorPane catchoose;
    @FXML
    private AnchorPane catshow;
    @FXML
    private GridPane grid;
    @FXML
    private ImageView back;
    @FXML
    private void initialize() throws IOException {
        menu.setVisible(true);
        menuBack.setVisible(false);
        slider.setTranslateX(-161);
        TreeItem<String> rootItem = new TreeItem<>("Categorias");
        cattree.setRoot(rootItem);
        TreeItem<String> child1 = new TreeItem<>("Tipo de prenda");
        TreeItem<String> child2 = new TreeItem<>("Color");
        rootItem.getChildren().addAll(child1,child2);
        TreeItem<String> leaf1 = new TreeItem<>("Pantalon");
        TreeItem<String> leaf2 = new TreeItem<>("Camiseta");
        TreeItem<String> leaf3 = new TreeItem<>("Sudadera");
        TreeItem<String> leaf4 = new TreeItem<>("Chaqueta");
        TreeItem<String> leaf5 = new TreeItem<>("BLusa");
        TreeItem<String> leaf6 = new TreeItem<>("Vestido");
        TreeItem<String> leaf7 = new TreeItem<>("Pantaloneta");
        TreeItem<String> leaf8 = new TreeItem<>("Zapatos");
        TreeItem<String> leaf9 = new TreeItem<>("Sandalias");
        TreeItem<String> leaf11 = new TreeItem<>("Gris");
        TreeItem<String> leaf12 = new TreeItem<>("Morado");
        TreeItem<String> leaf13 = new TreeItem<>("Rosa");
        TreeItem<String> leaf14 = new TreeItem<>("Naranja");
        TreeItem<String> leaf15 = new TreeItem<>("Amarillo");
        TreeItem<String> leaf16 = new TreeItem<>("Azul");
        TreeItem<String> leaf17 = new TreeItem<>("Rojo");
        TreeItem<String> leaf18 = new TreeItem<>("Verde");
        TreeItem<String> leaf19 = new TreeItem<>("Negro");
        TreeItem<String> leaf20 = new TreeItem<>("Blanco");
        child1.getChildren().addAll(leaf1,leaf2,leaf3,leaf4,leaf5,leaf6,leaf7,leaf8,leaf9);
        child2.getChildren().addAll(leaf11,leaf12,leaf13,leaf14,leaf15,leaf16,leaf17,leaf18,leaf19,leaf20);
        cattree.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.isLeaf()) { // Si se selecciona una hoja
                String selectedCategory = newValue.getValue();
                try {
                    mostrarPrendasPorCategoria(selectedCategory);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        mostrarChoose();
        back.setOnMouseClicked(event -> mostrarChoose());

        //String link = "prendaprev.fxml";
        //gridView(link);
        close.setOnMouseClicked(event -> closeWindow());
        menu.setOnMouseClicked(event -> slideSliderop());
        menuBack.setOnMouseClicked(event -> slideSlidercl());
    }
    @FXML
    public void selectItem(){

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
        Historial.agregar("categorias.fxml");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    private void mostrarverTodo(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("verTodo.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) carrito.getScene().getWindow();
        Historial.agregar("categorias.fxml");
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

    private void mostrarChoose() {
        catchoose.setVisible(true);
        catshow.setVisible(false);
        back.setVisible(false);
        carritoNum.setText("(" + Carrito.getSize() + ")");
    }

    private void mostrarShow() {
        catchoose.setVisible(false);
        catshow.setVisible(true);
        back.setVisible(true);
    }
    private void mostrarPrendasPorCategoria(String categoria) throws IOException {
        // Filtrar las prendas según la categoría seleccionada
        List<Prenda> prendas = HelloApplication.getListaDePrendas();
        List<Prenda> prendasFiltradas;

        // Filtrar por tipo de prenda o color
        if (categoria.equalsIgnoreCase("Pantalon") || categoria.equalsIgnoreCase("Camiseta")
                || categoria.equalsIgnoreCase("Sudadera") || categoria.equalsIgnoreCase("Chaqueta")
                || categoria.equalsIgnoreCase("Blusa") || categoria.equalsIgnoreCase("Vestido")
                || categoria.equalsIgnoreCase("Pantaloneta") || categoria.equalsIgnoreCase("Zapatos")
                || categoria.equalsIgnoreCase("Sandalias")) {
            prendasFiltradas = prendas.stream()
                    .filter(prenda -> prenda.getNombre().equalsIgnoreCase(categoria))
                    .collect(Collectors.toList());
        } else { // Filtrar por color
            prendasFiltradas = prendas.stream()
                    .filter(prenda -> prenda.getColor().equalsIgnoreCase(categoria))
                    .collect(Collectors.toList());
        }
        grid.getChildren().clear();
        int column = 0;
        int row = 0;
        for (Prenda prenda : prendasFiltradas) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("prendaprev.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();

            prendaprevController prendaprevController = fxmlLoader.getController();
            prendaprevController.setData(prenda,"categorias"); // Usa el método setData para pasar los datos de la prenda

            grid.add(anchorPane, column, row++); // Añadir el AnchorPane al grid
        }
        mostrarShow();
    }
}
