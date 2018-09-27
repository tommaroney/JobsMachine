package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.controller.Controller;

import javax.swing.*;
import java.io.Serializable;
import java.util.HashMap;

public class Main extends Application {

    Button newClassBtn = new Button("Assign New Jobs");
    Label allPurpose = new Label("Hi, Paige!");
    private HashMap<String, Integer> initiateJobs = new HashMap<String, Integer>();



    Controller sample;

    @Override
    public void start(Stage primaryStage) throws Exception{

/*        initiateJobs.put("Teacher's Assistant", 1);
        initiateJobs.put("Trash and Recycling", 1);
        initiateJobs.put("Pencils", 2);
        initiateJobs.put("Custodian", 1);
        initiateJobs.put("Postal Service", 2);
        initiateJobs.put("Swipe and Wipe", 1);
        initiateJobs.put("Chair Checkers", 2);
        initiateJobs.put("Electrician", 1);
        initiateJobs.put("Substitute", 1);
        initiateJobs.put("Line Leader", 1);
        initiateJobs.put("Caboose", 1);
        initiateJobs.put("Material Manager", 1);
        initiateJobs.put("Lost and Found", 1);
        sample = new Controller("Paige's Room", new String[]{"Matthew", "Claire", "Lydia", "Ryan", "Tobias",
                "Ellory", "Andre", "Roe", "Cam", "Sulley", "Maddie",
                "Kendall", "Kelsey", "Mollie", "Max", "Maia"}, initiateJobs);*/
        Controller sample = new Controller("Paige's Room");

//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        FlowPane landingScreen = new FlowPane(10, 10);
        landingScreen.setAlignment(Pos.CENTER);
        landingScreen.getChildren().addAll(newClassBtn, allPurpose);

        primaryStage.setTitle("Get a Job!");
        FlowPane rootNode = new FlowPane(10, 10);
        rootNode.getChildren().add(landingScreen);
        rootNode.setAlignment(Pos.CENTER);
        Scene primaryScene = new Scene(rootNode, 300, 120);

        newClassBtn.setOnAction(ae -> sample.assignNewJobs());

        primaryStage.setScene(primaryScene);
        primaryStage.show();


    }


    public static void main(String[] args) {
        launch(args);
    }
}
