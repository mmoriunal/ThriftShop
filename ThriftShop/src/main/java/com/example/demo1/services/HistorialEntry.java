package com.example.demo1.Services;

public class HistorialEntry {

    private String fxmlFile; // Nombre del archivo FXML
    private Object data;     // Datos adicionales (por ejemplo, un objeto Prenda)

    public HistorialEntry(String fxmlFile) {
        this.fxmlFile = fxmlFile;
        this.data = null;
    }
    public HistorialEntry(String fxmlFile, Object data) {
        this.fxmlFile = fxmlFile;
        this.data = data;
    }

    public String getFxmlFile() {
        return fxmlFile;
    }

    public Object getData() {
        return data;
    }
}
