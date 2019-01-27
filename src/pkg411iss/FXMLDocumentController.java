/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg411iss;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author Jack
 */
public class FXMLDocumentController implements Initializable {
    
    
     @FXML
    private ImageView mapImage;
     
     Image img;     
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        MapFile map = new MapFile("1","1");
                
         try {
             img = map.getMap();
         } catch (IOException ex) {
             Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        mapImage.setImage(img);
        
        
    }    
    
}
