/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkg411iss;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Jack
 */
public class ISSProvider {

    public Position getISS() throws ProtocolException, IOException, ParseException {

        URL url = new URL("http://api.open-notify.org/iss-now.json");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        int status = con.getResponseCode();

        String inputLine;
        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            StringBuilder content = new StringBuilder();

            inputLine = in.readLine();
            content.append(inputLine);

        }

        JSONParser parser = new JSONParser();

        Object parsed = parser.parse(inputLine);

        JSONObject jsonObject = (JSONObject) parsed;
        JSONObject jsonChildObject = (JSONObject) jsonObject.get("iss_position");

        String lat = (String) jsonChildObject.get("latitude");
        String lon = (String) jsonChildObject.get("longitude");

        Position pos = new Position(lat, lon);

        return pos;
    }
    
    /**
     * 
     * @param lat the latitude entered
     * @param lon the longitude entered
     * @return a string value stating the date and time it will return. 
     * @throws ProtocolException
     * @throws IOException
     * @throws ParseException 
     */
    public RiseTime nextPass(String lat, String lon) throws ProtocolException, IOException, ParseException{
        URL url = new URL("http://api.open-notify.org/iss-pass.json?lat=" + lat + "&lon=" + lon + "&n=1");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");

        int status = con.getResponseCode();

        String inputLine;
        try (BufferedReader search = new BufferedReader(
                new InputStreamReader(con.getInputStream()))) {
            StringBuilder content = new StringBuilder();

            inputLine = search.readLine();
            content.append(inputLine);

        }

        JSONParser parser = new JSONParser();

        Object parsed = parser.parse(inputLine);
        //JSONObject jsonObject = (JSONObject) parsed;
        //JSONArray jasonArray = jsonObject.getJSONArray("response");
        JSONObject jsonObject = (JSONObject) parsed;
        JSONArray jsonArray = (JSONArray) jsonObject.get("response");
        Object jsonArrayMember = jsonArray.get(0);
        System.out.println(jsonArrayMember);
        String newTime = (String) jsonArrayMember;
        
        RiseTime riseTime = new RiseTime(newTime);
        
        return riseTime;

        
    }

}
