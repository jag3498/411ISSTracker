package pkg411iss;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javafx.scene.image.Image;

public class MapFile {

    private Position pos;

    public MapFile(Position pos) {
        this.pos = pos;

    }

    public Image getMap() throws IOException {
        
        APIKey key = new APIKey();
        String apiKey = key.getAPIKey();
        
        String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + pos.getLat() + "," + pos.getLon() + "&zoom=3&size=700x700&maptype=roadmap"
                + "&markers=color:red%7Clabel:A%7C" + pos.getLat() + "," + pos.getLon()
                + "&key="+apiKey;
        System.out.println(imageUrl);

        String imageFile = "map.png";

        saveImage(imageUrl, imageFile);

        Image img = new Image("file:map.png");

        return img;

    }

    private static void saveImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        InputStream in = url.openStream();
        OutputStream out = new FileOutputStream(destinationFile);

        byte[] by = new byte[2048];
        int length;

        while ((length = in.read(by)) != -1) {
            out.write(by, 0, length);
        }

        in.close();
        out.close();
    }

}
