package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class NewPrenda {
    @FXML
    private Button Publicar, Pic;
    @FXML
    private Pane ImageShow;

    @FXML
    private ImageView PrendaImg,CloseB,Delete,Back;
    @FXML
    private TextField PrendaName, PrendaColor,PrendaPrecio;
    @FXML
    private ChoiceBox PrendaTalla;
    private String[] tallas={"S","M","L"};
    private String imgpath;
    @FXML
    void initialize() throws IOException {
        ImageShow.setVisible(false);
        CloseB.setOnMouseClicked(event -> closeWindow());
        Delete.setOnMouseClicked(event -> ImageShow.setVisible(false));
        PrendaTalla.getItems().addAll(tallas);

    }
    @FXML
    public void closeWindow() {

        Stage stage = (Stage) CloseB.getScene().getWindow();
        stage.close();
    }
    public void AddPic(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG image","*.jpg"),new FileChooser.ExtensionFilter("PNG image","*.png"));
        File selectedFile = fc.showOpenDialog(null);
        if(selectedFile!=null){
            Image image = new Image(selectedFile.getPath());
            PrendaImg.setImage(image);
            ImageShow.setVisible(true);
            imgpath=selectedFile.getPath();
        }
    }
    public void CreateNewPrenda(ActionEvent event) throws IOException{
        String nombre = PrendaName.getText();
        String color = PrendaColor.getText();
        char talla = (char) PrendaTalla.getValue();
        int precio = Integer.parseInt(PrendaPrecio.getText());
        Prenda p = new Prenda(HelloApplication.getListaDePrendas().size()+1,nombre,color,talla,53548,precio,imgpath);
        HelloApplication.getListaDePrendas().add(p);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Search.fxml")); // Cambia a la ruta correcta
        Parent root = loader.load();
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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
