package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Path;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class PerfilDeUsuarioController {

    @FXML
    private ImageView CloseB,Back;

    @FXML
    private Label lblIdUsuario;

    @FXML
    private Label lblUsuario;

    @FXML
    private Button Pic;

    @FXML
    private ImageView UserImg;

    static Usuario usuario;

    File selectedFile;

    // Método para inicializar la vista con los datos del usuario
    public void initialize(Usuario usuario) {
        this.usuario = usuario;
        lblIdUsuario.setText("ID de Usuario: " + usuario.getIdUsuario());
        lblUsuario.setText("Usuario: " + usuario.getNombre());

        if (usuario.getProfileImagePath() != null && !usuario.getProfileImagePath().isEmpty()) {
            File file = new File(usuario.getProfileImagePath());
            if (file.exists()) {
                Image image = new Image(file.toURI().toString());
                UserImg.setImage(image);
            }
        }
    }

    @FXML
    private void handleSalir() {
        Stage stage = (Stage) CloseB.getScene().getWindow();
        stage.close();
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

    @FXML
    public void handleUploadPicture(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPEG image", "*.jpg"),
                                        new FileChooser.ExtensionFilter("PNG image", "*.png"));
        selectedFile = fc.showOpenDialog(null);
        if (selectedFile != null) {
            Image image = new Image(selectedFile.toURI().toString());
            UserImg.setImage(image);

            usuario.setProfileImagePath(selectedFile.getAbsolutePath());

            try {
                saveProfileImage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void saveProfileImage() throws IOException {
        if (selectedFile != null) {
            try {
                File targetFolder = new File("ThriftShop\\src\\main\\resources\\com\\example\\demo1\\imagesUser");
                if (!targetFolder.exists()) {
                    targetFolder.mkdirs(); // Crea la carpeta si no existe
                }
                File targetFile = new File(targetFolder, selectedFile.getName());
                Files.copy(selectedFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    
                // Guarda la ruta relativa para uso futuro
                usuario.setProfileImagePath(targetFile.getPath());

                LoginController.guardarUsuarioEnArchivo(usuario);
    
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error al guardar la imagen.");
            }
        }
    }
}