/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg411iss;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import javafx.scene.text.*;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Jack
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ImageView mapImage;
    @FXML
    private Text latitude, longitude;
    @FXML
    private Button timeUntilPass;

    Image img;
    Position pos;
    private Stage mainStage;
    private NavCntl theNavCntl;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ISSProvider iss = new ISSProvider();
        try {
            pos = iss.getISS();
            updateLatLong(pos);

        } catch (IOException | ParseException ex) {
        }
        MapFile map = new MapFile(pos);
        try {
            img = map.getMap();
        } catch (IOException ex) {
        }
        mapImage.setImage(img);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(5), (ActionEvent actionEvent) -> {
                    update();
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void update() {
        ISSProvider iss = new ISSProvider();
        try {
            pos = iss.getISS();
            updateLatLong(pos);

        } catch (IOException | ParseException ex) {
        }

        MapFile map = new MapFile(pos);

        try {
            img = map.getMap();
        } catch (IOException ex) {
        }

        mapImage.setImage(img);
    }
    
    /**
     * This method updates the UI elements, latitude and longitude with the current values.
     */
    private void updateLatLong(Position current){
        latitude.setText(current.getLat());
        longitude.setText(current.getLon());
        System.out.println("Lat: " + current.getLat());
        System.out.println("Long: " + current.getLon());
    }
    
    @FXML
    private void loadTimeUntilPass(){
        mainStage = (Stage) latitude.getScene().getWindow();
        theNavCntl = new NavCntl(mainStage,"LocationPrediciton"); 
    }

}
