package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.example.demo1.models.Prenda;

public class HelloApplication extends Application {
    private static List<Prenda> listaDePrendas;
    @Override
    public void start(Stage stage) throws IOException {
        listaDePrendas = getPrendas();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/views/Search.fxml"));
        Scene scene = new Scene(fxmlLoader.load() /*474, 605*/);
        stage.initStyle(javafx.stage.StageStyle.UNDECORATED);
        stage.setTitle("ThriftStop");
        stage.setScene(scene);
        stage.show();

    }

    public static List<Prenda> getPrendas(){
        List<Prenda> listaDePrendas = new ArrayList<>();
        try (InputStream inputStream = HelloApplication.class.getClassLoader().getResourceAsStream("/demo1/data/prendas.txt")){
            if (inputStream == null) {
                throw new IOException("Archivo 'prendas.txt' no encontrado.");
            }
        
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream)); 
            String line;
            while ((line = reader.readLine()) != null) {
                Prenda pr = Prenda.fromString(line);
                listaDePrendas.add(pr);
                //System.out.println(pr.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaDePrendas;
    }

    public static List<Prenda> getListaDePrendas() { // Método para acceder a la lista de prendas
        return listaDePrendas;
    }

    public static void main(String[] args) throws IOException {
        launch();
    }
}
