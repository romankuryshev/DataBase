package Main;

import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.fxml.*;

import java.io.IOException;
import java.sql.*;

public class Main extends Application {
    public static final String DB_URL = "jdbc:postgresql://localhost:5432/Zad1";
    public static final String USER = "postgres";
    public static final String PASSWORD = "1653";

    public static void main(String[] args) throws SQLException {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/table.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setWidth(1000);
        stage.setHeight(700);
        stage.show();
    }

    public static void showAlternativeForm(){
        Stage alternativeForm = new Stage();
        alternativeForm.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AlternativeForm.fxml"));
        try{
            Parent root = loader.load();
            alternativeForm.setScene(new Scene(root));
            alternativeForm.setWidth(900);
            alternativeForm.setHeight(400);
            alternativeForm.show();
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Alternative Form error");
        }
    }

    public static void showTask1Form(){
        Stage task1 = new Stage();
        task1.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader((Main.class.getResource("/Task1.fxml")));
        try{
            Parent root = loader.load();
            task1.setScene(new Scene(root));
            task1.setWidth(600);
            task1.setHeight(400);
            task1.show();
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Task1Form error");
        }
    }

    public static void showTask2Form(){
        Stage task1 = new Stage();
        task1.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader((Main.class.getResource("/Task2.fxml")));
        try{
            Parent root = loader.load();
            task1.setScene(new Scene(root));
            task1.setWidth(600);
            task1.setHeight(400);
            task1.show();
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Task2Form error");
        }
    }
    public static void showTask3Form(){
        Stage task1 = new Stage();
        task1.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader((Main.class.getResource("/Task3.fxml")));
        try{
            Parent root = loader.load();
            task1.setScene(new Scene(root));
            task1.setWidth(600);
            task1.setHeight(400);
            task1.show();
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException("Task3Form error");
        }
    }
}
