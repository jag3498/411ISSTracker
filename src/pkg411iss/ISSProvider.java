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

        // parser = new JSONParser();
        JSONParser parser = new JSONParser();

        Object parsed = parser.parse(inputLine);

        JSONObject jsonObject = (JSONObject) parsed;
        JSONObject jsonChildObject = (JSONObject) jsonObject.get("iss_position");

        String lat = (String) jsonChildObject.get("latitude");
        String lon = (String) jsonChildObject.get("longitude");

        Position pos = new Position(lat, lon);

        return pos;
    }

}
