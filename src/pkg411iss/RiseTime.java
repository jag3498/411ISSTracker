/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg411iss;

/**
 *
 * @author JoeQ7
 */
   
import java.util.Date;

public class RiseTime {
    //This class contins info on the next pass of the ISS
    private String riseTime;
    
    public RiseTime(String newTime){
        riseTime = newTime;
    }

    /**
     * @return the riseTime
     */
    public String getRiseTimestamp() {
        return riseTime;
    }

    /**
     * @param riseTime the riseTime to set
     */
    public void setRiseTimestamp(String riseTime) {
        this.riseTime = riseTime;
    }
    
    /**
     * @return the riseTime, translates it from a string timestamp, to a date. 
     */
    public Date getDateTime() {
        long riseTimeAsLong = Long.parseLong(riseTime)*1000; 
        
        Date theDateTime = new Date(riseTimeAsLong);
        
        return theDateTime;
    }
    
    
    
    
    
}
