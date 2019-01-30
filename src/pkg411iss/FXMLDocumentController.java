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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import org.json.simple.parser.ParseException;

/**
 *
 * @author Jack
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private ImageView mapImage;

    Image img;
    Position pos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ISSProvider iss = new ISSProvider();
        try {
            pos = iss.getISS();

        } catch (IOException | ParseException ex) {
        }
        MapFile map = new MapFile(pos);
        try {
            img = map.getMap();
        } catch (IOException ex) {
        }
        mapImage.setImage(img);

        //display google maps image
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(5), (ActionEvent actionEvent) -> {
                    update();
                }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

    }

    public void update() {
        
        //update ISS location
        ISSProvider iss = new ISSProvider();
        try {
            pos = iss.getISS();

        } catch (IOException | ParseException ex) {
        }

        MapFile map = new MapFile(pos);

        try {
            img = map.getMap();
        } catch (IOException ex) {
        }

        mapImage.setImage(img);
    }

}
