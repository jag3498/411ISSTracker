package pkg411iss;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import javafx.scene.image.Image;

public class MapFile {

    private String lat;
    private String lon;

    public MapFile(String lat, String lon) {
        this.lat = lat;
        this.lon = lon;

    }

    public Image getMap() throws IOException {
        String imageUrl = "https://maps.googleapis.com/maps/api/staticmap?center=" + lat + "," + lon + "&zoom=9&size=800x500&maptype=roadmap"
                + "&markers=color:red%7Clabel:A%7C"+lat+","+lon
                + "&key=AIzaSyDRsDsxhA3xHzjbEW7xDFsS6V26tlPwqtU";
        System.out.println(imageUrl);
        
        String imageFile = "map.png";
        
        saveImage(imageUrl,imageFile);
        
     Image img = new Image("file:map.png");
   
     return img;

    }

    //  saveImage(imageUrl, destinationFile);
    private static void saveImage(String imageUrl, String destinationFile) throws IOException {
        URL url = new URL(imageUrl);
        InputStream is = url.openStream();
        OutputStream os = new FileOutputStream(destinationFile);

        byte[] b = new byte[2048];
        int length;

        while ((length = is.read(b)) != -1) {
            os.write(b, 0, length);
        }

        is.close();
        os.close();
    }

}
