package GeoUtilis;

import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Davide
 */
public class Geocoding {

    public static List<Place> SearchPlace(String placeName) throws MalformedURLException, ParserConfigurationException, SAXException, IOException {
        try {
            URL url = new URL("https://nominatim.openstreetmap.org/search?q=" + URLEncoder.encode(placeName, StandardCharsets.UTF_8) + "&format=xml&polygon_geojson=1&addressdetails=1");

            Scanner s = new Scanner(url.openStream());
            s.useDelimiter("/u00a");
            String xmlString = s.next();
            XmlParser parser = new XmlParser();
            List<Place> places = parser.GetPlaces(xmlString);
            return places;
        } catch (IOException ex) {
//            Logger.getLogger(Geocoding.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
