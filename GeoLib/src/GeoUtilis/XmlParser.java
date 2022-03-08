/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GeoUtilis;

/**
 *
 * @author Davide
 */
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Davide
 */
public class XmlParser {
    //metodo per ottenere la lista dei luoghi dalla stringa xml ottenuta da open streetmap
    public static List<Place> GetPlaces(String xmlString) throws ParserConfigurationException, IOException, SAXException {
        List<Place> placesList = new LinkedList<>();

        Document document;
        DocumentBuilderFactory factory;
        DocumentBuilder builder;

        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();

        InputSource is = new InputSource(new StringReader(xmlString));

        document = builder.parse(is);

        //ottengo la root
        Element root = document.getDocumentElement();
        //ottengo la lista degli elementi figli con nome del tag place
        NodeList nodeList = root.getElementsByTagName("place");

        //inserisco tutti gli elementi di tipo place all'interno della lista
        for (int i = 0; i < nodeList.getLength(); i++) {

            Element element = (Element) nodeList.item(i);
            //ottengo l'elemento road
            Place place = GetPlace(element);
            placesList.add(place);
        }

        return placesList;
    }

    private static Place GetPlace(Element element) {

        Place place = new Place();

        if (element.hasAttribute("geojson")) {
            place.setGeojson(element.getAttribute("geojson"));
        }

        Element road = (Element) element.getElementsByTagName("road").item(0);
        if (road != null) {
            place.setRoad(road.getFirstChild().getNodeValue());
        }

        //ottengo l'elemento town
        Element town = (Element) element.getElementsByTagName("town").item(0);
        if (town != null) {
            place.setTown(town.getFirstChild().getNodeValue());
        }

        //ottengo l'elemento county
        Element county = (Element) element.getElementsByTagName("county").item(0);
        if (county != null) {
            place.setCounty(county.getFirstChild().getNodeValue());
        }

        //ottengo l'elemento state
        Element state = (Element) element.getElementsByTagName("state").item(0);
        if (state != null) {
            place.setState(state.getFirstChild().getNodeValue());
        }

        //ottengo l'elemento postcode
        Element postcode = (Element) element.getElementsByTagName("postcode").item(0);
        if (postcode != null) {
            place.setPostcode(postcode.getFirstChild().getNodeValue());
        }

        //ottengo l'elemento country
        Element country = (Element) element.getElementsByTagName("country").item(0);
        if (country != null) {
            place.setCountry(country.getFirstChild().getNodeValue());
        }

        //ottengo l'elemento country_code
        Element country_code = (Element) element.getElementsByTagName("country_code").item(0);
        if (country_code != null) {
            place.setCountry_code(country_code.getFirstChild().getNodeValue());
        }

        //ottengo l'elemento railway
        Element railway = (Element) element.getElementsByTagName("railway").item(0);
        if (railway != null) {
            place.setRailway(railway.getFirstChild().getNodeValue());
        }

        //ottengo l'elemento emergency
        Element emergency = (Element) element.getElementsByTagName("emergency").item(0);
        if (emergency != null) {
            place.setEmergency(emergency.getFirstChild().getNodeValue());
        }

        //ottengo l'elemento village
        Element village = (Element) element.getElementsByTagName("village").item(0);
        if (village != null) {
            place.setVillage(village.getFirstChild().getNodeValue());
        }

        // ottengo l'elemento hamlet
        Element hamlet = (Element) element.getElementsByTagName("hamlet").item(0);
        if (hamlet != null) {
            place.setHamlet(hamlet.getFirstChild().getNodeValue());
        }

        //ottengo le coordinate
        String lat = element.getAttribute("lat");
        String lon = element.getAttribute("lon");
        Coordinate c = new Coordinate();
        if(lat != ""){
            try{
               c.setLatitude(Double.parseDouble(lat));
            }
            catch(Exception e){
                
            }
        }
        
        if(lon != ""){
            try{
               c.setLongitude(Double.parseDouble(lon));
            }
            catch(Exception e){
                
            }
        }
        
        place.setCoordinate(c);
        
        return place;
    }

}

