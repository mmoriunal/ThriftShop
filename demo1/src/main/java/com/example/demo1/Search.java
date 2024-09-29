package com.example.demo1;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Search extends prendaprevController {
    @FXML
    private TextField Query;
    @FXML
    private ImageView CloseB;
    @FXML
    private Button SearchButton, NewP;
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
    @FXML
    private AnchorPane recomendacionesPane;
    @FXML
    private HBox recomendacionesHBox;
    @FXML
    private GridPane recomendacionesGrid;

    String query;
    static List<Prenda> resultados;

    @FXML
    private void initialize() {
        menu.setVisible(true);
        menuBack.setVisible(false);
        slider.setTranslateX(-161);
        carritoNum.setText("(" + Carrito.getSize() + ")");
        menu.setOnMouseClicked(event -> slideSliderop());
        menuBack.setOnMouseClicked(event -> slideSlidercl());
        mostrarRecomendaciones();
    }

    public void SearchB(ActionEvent event) throws IOException {
        query = Query.getText();
        buscarProductos(query);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchRes.fxml"));
        Historial.agregar("Search.fxml");
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public static List<Prenda> buscarProductos(String query) {
        String[] keywords = query.toLowerCase().split(" ");
        resultados = new ArrayList<>();
        List<Prenda> allProd = HelloApplication.getListaDePrendas();
        for (int i = 0; i < allProd.size() / 2; i++) {
            Prenda producto = allProd.get(i);
            if (keywords.length > 1) {
                if (producto.nombre.toLowerCase().contains(keywords[0]) &&
                        producto.color.toLowerCase().contains(keywords[1])) {
                    resultados.add(producto);
                }
            } else {
                if (producto.nombre.toLowerCase().contains(keywords[0])) {
                    resultados.add(producto);
                }
            }
        }
        return resultados;
    }

    public void NuevaPrenda(ActionEvent event) throws IOException {
        query = Query.getText();
        buscarProductos(query);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("NewPrenda.fxml"));
        Parent root = loader.load();
        Historial.agregar("Search.fxml");
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void mostrarVerTodo(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("verTodo.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void mostrarCarrito(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("carrito.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) carrito.getScene().getWindow();
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
        TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), slider);
        slide.setToX(0);
        slide.play();
        slide.setOnFinished((ActionEvent e) -> {
            menu.setVisible(false);
            menuBack.setVisible(true);
        });
    }

    @FXML
    private void slideSlidercl() {
        slider.setTranslateX(0);
        TranslateTransition slide = new TranslateTransition(Duration.seconds(0.4), slider);
        slide.setToX(-161);
        slide.play();
        slide.setOnFinished((ActionEvent e) -> {
            menu.setVisible(true);
            menuBack.setVisible(false);
        });
    }

    @FXML
    private void mostrarRecomendaciones() {

        List<Prenda> prendas = HelloApplication.getListaDePrendas();
        Graph graph = new Graph();
        graph.buildGraphbySize(prendas);

        Random random = new Random();
        List<Prenda> prendasGrafo = new ArrayList<>(graph.getAdjacencyList().keySet());
        Prenda prendaAleatoria = prendasGrafo.get(random.nextInt(prendasGrafo.size()));

        List<Prenda> recomendaciones = graph.getSampleConnections(prendaAleatoria, 6);

        recomendacionesHBox.getChildren().clear();

        for (Prenda prenda : recomendaciones) {
            VBox prendaColumn = new VBox();
            prendaColumn.setAlignment(Pos.TOP_CENTER);
            prendaColumn.setSpacing(5);
            prendaColumn.setStyle("-fx-border-color: gold; -fx-border-width: 1; -fx-padding: 10; -fx-background-color: white;");

            Label prendaLabel = new Label(prenda.getNombre() + " " + prenda.getColor());
            prendaLabel.setAlignment(Pos.TOP_CENTER);
            prendaLabel.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-background-color: #D5BD72; -fx-padding: 5;");
            prendaLabel.setMinWidth(130);

            ImageView prendaImage = new ImageView(new Image(getClass().getResourceAsStream(prenda.getFotoPath())));
            prendaImage.setFitWidth(90);
            prendaImage.setFitHeight(70);

            Label precioLabel = new Label("$" + prenda.getPrecio());
            precioLabel.setMinWidth(130);
            precioLabel.setAlignment(Pos.CENTER);

            Label verLabel = new Label("Ver");
            verLabel.setStyle("-fx-text-fill: blue; -fx-underline: true;");
            verLabel.setOnMouseClicked(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("prendaView.fxml"));
                    Parent root = loader.load();
                    prendaViewController controller = loader.getController();
                    controller.setData(prenda);

                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            prendaColumn.getChildren().addAll(prendaLabel, prendaImage, precioLabel, verLabel);

            recomendacionesHBox.getChildren().add(prendaColumn);
            }
        }
    }

