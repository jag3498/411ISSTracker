/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg411iss;

import com.sun.deploy.util.StringUtils;
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
            if(StringContainsOnlyNumbers(latitude.getText()) &&
                    StringContainsOnlyNumbers(longitude.getText()))
            {
                String lat = latitude.getText();
                String lon = longitude.getText();
                RiseTime riseTime = iss.nextPass(lat,lon);
        
        System.out.println("Rise Time" + riseTime.getDateTime());
            }else { 
                responseField.setText("Error, please use a valid latitude and longitude. \n Proper values fall between 90 and -90, excluding 0");
            }
            
            
        
        }catch(IOException | ParseException ex){
            ex.printStackTrace();
        }
        
        //************************Add input sanitization here, only numbers allowed in string******************************
        
        
    }
    
    public boolean StringContainsOnlyNumbers(String str){
        try{
            double d = Double.parseDouble(str);
        }catch(NumberFormatException ex){
            return false;
        }
        return true;
    }
    
}
