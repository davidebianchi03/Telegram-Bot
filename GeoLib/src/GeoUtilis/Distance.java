/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GeoUtilis;

/**
 *
 * @author Davide
 */
public class Distance {
    //metodo per calcolare la distanza tra 2 punti
    public static double CalculateDistanceInMeters(Coordinate p1, Coordinate p2) {
        double earthRadius = 6371;//raggio della terra in km
        double deltaLat = deg2rad(p2.getLatitude() - p1.getLatitude());
        double deltaLon = deg2rad(p2.getLongitude() - p1.getLongitude());
        var a
                = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2)
                + Math.cos(deg2rad(p1.getLatitude())) * Math.cos(deg2rad(p2.getLatitude()))
                * Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        var c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        var d = earthRadius * c; // Distanza in km
        return d * 1000;
    }

    public static double deg2rad(double deg) {
        return deg * (Math.PI / 180);
    }
}
