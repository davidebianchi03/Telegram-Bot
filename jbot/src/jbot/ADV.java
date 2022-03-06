/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jbot;

/**
 *
 * @author Davide Classe che contiene le informazioni sulle pubblicità
 */
public class ADV {

    private String description;
    private double latitude;
    private double longitude;

    public ADV() {
        description = "";
        latitude = 0.0;
        longitude = 0.0;
    }

    public ADV(String description, double latitude, double longitude) {
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    
    //metodo per caricare l'oggetto da una stringa CSV
    public static ADV LoadFromCSV(String csvString){
        ADV obj = new ADV();
        //la stringa è salvata nel formato description;latitude;longitude;
        int firstPos = csvString.indexOf(";");
        if(firstPos > 0){
            String description = csvString.substring(0, firstPos);
            obj.setDescription(description);
        }
        
        int secondPos = csvString.indexOf(";");
        if(secondPos > firstPos){
            String latString = csvString.substring(firstPos + 1, secondPos);
            obj.setLatitude(Double.parseDouble(latString));
        }
        
        int thirdPos = csvString.indexOf(";");
        if(thirdPos > secondPos && thirdPos > firstPos){
            String lonString = csvString.substring(secondPos + 1, thirdPos);
            obj.setLongitude(Double.parseDouble(lonString));
        }
        
        return obj;
    }
    
    //metodo per salvare l'oggetto in una stringa in formato CSV
    public String ToCsv(){
        return description + ";" + Double.toString(latitude) + ";" + Double.toString(longitude) + ";";
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
