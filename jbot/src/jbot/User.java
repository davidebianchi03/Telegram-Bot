/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jbot;

import GeoUtilis.Coordinate;

/**
 *
 * @author Davide
 */
public class User {

    private int chat_id;
    private Coordinate coordinate;
    private String name;

    public User() {
        chat_id = 0;
        coordinate = new Coordinate();
        name = "";
    }

    public User(int chat_id, Coordinate coordinate, String name) {
        this.chat_id = chat_id;
        this.coordinate = coordinate;
        this.name = name;
    }

    public static User LoadFromCsv(String csvString) {
        User u = new User();
        try {
            double latitude = 0;
            double longitude = 0;
            
            //formato del csv chat_id;latitudine;longitudine;name;
            int pos = csvString.indexOf(";");
            if (pos > -1) {
                u.chat_id = Integer.parseInt(csvString.substring(0, pos));
            }
            csvString = csvString.substring(pos + 1, csvString.length());

            pos = csvString.indexOf(";");
            if (pos > -1) {
                latitude = Double.parseDouble(csvString.substring(0, pos));
            }

            csvString = csvString.substring(pos + 1, csvString.length());

            pos = csvString.indexOf(";");
            if (pos > -1) {
                longitude = Double.parseDouble(csvString.substring(0, pos));
            }
            u.coordinate = new Coordinate(latitude, longitude);
            csvString = csvString.substring(pos + 1, csvString.length());

            pos = csvString.indexOf(";");
            if (pos > -1) {
                u.name = csvString.substring(0, pos);
            }
            return u;
        }
        catch(Exception ex){
            System.out.println("Exception in loading user");
            return new User();
        }
        
    }
    
    public String toCSV(){
        //formato del csv chat_id;latitudine;longitudine;name;
        return Integer.toString(chat_id)+";"+Double.toString(coordinate.getLatitude())+";"+Double.toString(coordinate.getLongitude())+";"+name+";";
    }

    public int getChat_id() {
        return chat_id;
    }

    public void setChat_id(int chat_id) {
        this.chat_id = chat_id;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    

}
