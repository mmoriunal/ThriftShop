package com.example.demo1.services;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

import com.example.demo1.controllers.PrendaPrevController;
import com.example.demo1.models.Historial;
import com.example.demo1.models.MaxHeap;
import com.example.demo1.models.MinHeap;
import com.example.demo1.models.Prenda;

public class SearchRes {

    @FXML
    private Pane PriceRange;
    @FXML
    private Label ResNum;
    @FXML
    private Button Sort1,Sort2,Filter,Apply;
    @FXML
    private GridPane Resgrid;
    @FXML
    private ImageView CloseB,CloseB1;
    @FXML
    private TextField Min,Max;
    @FXML
    private void initialize() throws IOException{
        PriceRange.setVisible(false);
        printresult();
        CloseB.setOnMouseClicked(event -> closeWindow());
        CloseB1.setOnMouseClicked(event -> PriceRange.setVisible(false));
    }
    @FXML
    public void closeWindow() {

        Stage stage = (Stage) CloseB.getScene().getWindow();
        stage.close();
    }
    public void printresult() throws IOException {
        if (Search.resultados.size()==0) {
          ResNum.setText("Prenda no encontrada");
        } else {
            int res = Search.resultados.size();
            ResNum.setText(res + " Resultados encontrados");
            int column = 0;
            int row = 0;
            for (int i = 0; i < Search.resultados.size();i++){
                if(Search.resultados.get(i)!=null){
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("prendaprev.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                PrendaPrevController prendaPrevController = fxmlLoader.getController();
                prendaPrevController.setData(Search.resultados.get(i),"SearchRes");
                Resgrid.add(anchorPane,column, row++);
                GridPane.setMargin(anchorPane, new Insets(Search.resultados.size()));
            }}
        }
    }
    public void SortmM(ActionEvent event) throws IOException{
        Resgrid.getChildren().clear();
        int column = 0;
        int row = 0;
        MinHeap heap = new MinHeap();
        for(int i=0;i<Search.resultados.size();i++){
            heap.insertKey(Search.resultados.get(i));
        }
        while (!heap.isEmpty()) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("prendaprev.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            PrendaPrevController prendaPrevController = fxmlLoader.getController();
            prendaPrevController.setData(heap.extractMin(),"SearchRes");
            Resgrid.add(anchorPane,column, row++);
            GridPane.setMargin(anchorPane, new Insets(Search.resultados.size()));
        }
    }
    public void SortMm(ActionEvent event) throws IOException{
        Resgrid.getChildren().clear();
        int column = 0;
        int row = 0;
        MaxHeap heapmax = new MaxHeap();
        for (int i = 0; i < Search.resultados.size(); i++) {
            heapmax.insertKey(Search.resultados.get(i));
        }
        while (!heapmax.isEmpty()) {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("prendaprev.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            PrendaPrevController prendaPrevController = fxmlLoader.getController();
            prendaPrevController.setData(heapmax.extractMax(),"SearchRes");
            Resgrid.add(anchorPane,column, row++);
            GridPane.setMargin(anchorPane, new Insets(Search.resultados.size()));
        }}
    public void PriceFilter(ActionEvent event){
        PriceRange.setVisible(true);
    }

    public void ApplyFilter(ActionEvent event) throws IOException{
        List<Prenda> filtres;
        FiltradoPrecioService filtrado = new FiltradoPrecioService();
        int min = Integer.parseInt(Min.getText());
        int max = Integer.parseInt(Max.getText());
        filtres = filtrado.filtradoPorPrecioAVL(Search.resultados, min, max);
        if (Search.resultados.size()==0) {
            ResNum.setText("No se hay resultados");
        } else {
            int res = filtres.size();
            ResNum.setText(res + " Resultados encontrados");
            PriceRange.setVisible(false);
            Resgrid.getChildren().clear();
            int column = 0;
            int row = 0;
            for (int i = 0; i < filtres.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("prendaprev.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();
                PrendaPrevController prendaPrevController = fxmlLoader.getController();
                prendaPrevController.setData(filtres.get(i),"SearchRes");
                Resgrid.add(anchorPane, column, row++);
                GridPane.setMargin(anchorPane, new Insets(Search.resultados.size()));
            }
        }

    }
    @FXML
    public void handleBackClick(MouseEvent event) {
        if (Historial.hayHistorial()) {
            HistorialEntry ultimaEntrada = Historial.atras();
            if (ultimaEntrada != null) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(ultimaEntrada.getFxmlFile()));
                    System.out.println(ultimaEntrada.getFxmlFile());
                    Parent root = loader.load();
                    Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
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
