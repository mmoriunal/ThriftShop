package com.example.demo1.Services;

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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.example.demo1.Models.Historial;
import com.example.demo1.Models.Prenda;

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
    private String[] tallas={"XS","S","M","L","XL","XXL"};
    private String imgpath;
    File selectedFile;
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
        selectedFile = fc.showOpenDialog(null);
        if(selectedFile!=null){
            Image image = new Image(selectedFile.getPath());
            PrendaImg.setImage(image);
            ImageShow.setVisible(true);
        }
    }
    public void CreateNewPrenda(ActionEvent event) throws IOException{
        File targetFolder = new File("C:\\demo2\\demo1\\src\\main\\resources\\com\\example\\demo1\\images");
        File targetFile = new File(targetFolder, selectedFile.getName());
        Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        String nombre = PrendaName.getText();
        nombre= nombre.substring(0, 1).toUpperCase() + nombre.substring(1).toLowerCase();
        String color = PrendaColor.getText();
        color = color.substring(0, 1).toUpperCase() + color.substring(1).toLowerCase();
        String talla = (String) PrendaTalla.getValue();
        int precio = Integer.parseInt(PrendaPrecio.getText());
        String relativePath = "images/" + selectedFile.getName();
        Prenda p = new Prenda(HelloApplication.getListaDePrendas().size()+1,nombre,color,talla,53548,precio,relativePath);
        String newLineContent = HelloApplication.getListaDePrendas().size()+1 + "," + nombre + "," + color + "," + talla + "," + "53548" + "," + precio + "," + relativePath;
        System.out.println(newLineContent);
        try  {
            BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\demo2\\demo1\\src\\main\\resources\\prendas.txt",true));
            writer.write("\n"+newLineContent);  // Write your content
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
