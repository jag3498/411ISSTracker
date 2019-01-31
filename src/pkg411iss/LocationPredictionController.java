/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg411iss;

import com.sun.deploy.util.StringUtils;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;  
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
            
            if(StringContainsOnlyNumbers(latitude.getText()) && StringContainsOnlyNumbers(longitude.getText()) )
            {
                double lat = Double.parseDouble(latitude.getText());
                double lon = Double.parseDouble(longitude.getText());
                if(lat >= -90 && lat <=90 && lat!=0 && lon >= -90 && lon <=90 && lon!=0){
                    //check for the next passing of the iss, and set this value into a riseTime obj
                   RiseTime riseTime = iss.nextPass(latitude.getText(),longitude.getText());
                   //format thegetDateTime() method's returned date into a string
                   DateFormat dateFormat = new SimpleDateFormat("yyy-mm-dd hh:mm:ss");
                   String strRiseTime = dateFormat.format(riseTime.getDateTime());
                   
                   responseField.setText("The next pass will be: " + riseTime.getDateTime());
                    System.out.println("Rise Time" + riseTime.getDateTime()); 
                }else{
                    responseField.setText("Error, please use a valid latitude and longitude. \n Proper values fall between 90 and -90, excluding 0");
                }
                
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
