package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class HelloApplication extends Application {
    public static PrendaService prendaService = new PrendaService();
    private static List<Prenda> listaDePrendas = prendaService.getPrendas();
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        //stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
        stage.setTitle("ThriftStop - Login");
        stage.setScene(scene);
        stage.show();
    }

    public static List<Prenda> getPrendas(){
        listaDePrendas = prendaService.getPrendas();
        return listaDePrendas;
    }

    public static List<Prenda> getListaDePrendas(){
        return listaDePrendas;
    }

    public static void main(String[] args) throws IOException {
        launch();
    }
}
