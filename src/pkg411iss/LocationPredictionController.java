/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg411iss;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

/**
 * FXML Controller class
 *
 * @author JoeQ7
 */
public class LocationPredictionController implements Initializable {

    @FXML
    private Button back, lookUpTime;
    @FXML
    private TextField latitude, longitude;
    @FXML
    private Label responseField;
    
    private Stage mainStage;
    private NavCntl theNavCntl;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void loadMapPage(){
        mainStage = (Stage) latitude.getScene().getWindow();
        theNavCntl = new NavCntl(mainStage,"MapPage");
    }
    
    @FXML
    public void lookUpTime(){
        ISSProvider iss = new ISSProvider();
        try{
            //********these have example fields, map them to the correct ones.********************************
            //********need to match the values the user inputs***************
        RiseTime riseTime = iss.nextPass("60","60");
        
        System.out.println("Rise Time" + riseTime.getDateTime());
        
        }catch(IOException | ParseException ex){
            ex.printStackTrace();
        }
        
        //************************Add input sanitization here, only numbers allowed in string******************************
        
        
    }
    
}
