package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import  javafx.scene.image.Image;

import javax.print.DocFlavor;
import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("calculadora.fxml"));

        primaryStage.setTitle("Calculadora");

        Image icon = new Image(getClass().getResourceAsStream("ico/calculadora.png"));
        primaryStage.getIcons().add(icon);
        primaryStage.setScene(new Scene(root, 430, 511));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
