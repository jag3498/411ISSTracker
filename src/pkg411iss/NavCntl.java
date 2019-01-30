/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg411iss;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author JoeQ7
 */
public class NavCntl {
    private Stage stage;

    public NavCntl(Stage theStage, String page) {
        stage = theStage;
        switch(page){
            case "MapPage":
                setUpDocumentControllerScene();
                break;
            case "LocationPrediciton":
                setUpLocationPredictionScene();
                break;
            default:
                //nothing
        }
    }
    
    /**
    * Method to load the map page.
    */
    public void setUpDocumentControllerScene() {
        Parent root;
        Scene scene;
        try {
            root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            scene = new Scene(root);
            stage.setTitle("Where is the ISS?");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
    * Method to load the PredictionScene
    */
    public void setUpLocationPredictionScene() {
        Parent root;
        Scene scene;
        try {
            root = FXMLLoader.load(getClass().getResource("LocationPrediction.fxml"));
            scene = new Scene(root);
            stage.setTitle("Find a pass time.");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
