package com.example.demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class PrendaService {
    public List<Prenda> getPrendas() {
        List<Prenda> listaDePrendas = new ArrayList<>();
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream("prendas.txt");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Prenda prenda = Prenda.fromString(line);
                listaDePrendas.add(prenda);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaDePrendas;
    }
}