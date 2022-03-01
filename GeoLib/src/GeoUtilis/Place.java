/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GeoUtilis;

/**
 *
 * @author Davide
 */
/**
 *
 * @author Davide
 */
public class Place {

    private String road;
    private String town;
    private String county;
    private String state;
    private String postcode;
    private String country;
    private String country_code;
    private String amenity;
    private String railway;
    private String emergency;
    private String village;
    private String hamlet;
    private String geojson;

    public Place() {
        road = "";
        town = "";
        county = "";
        state = "";
        postcode = "";
        country = "";
        country_code = "";
        amenity = "";
        railway = "";
        emergency = "";
        village = "";
        hamlet = "";
        geojson = "";
    }

    //metodo toString
    @Override
    public String toString() {
        String content = "";

        if (this.getAmenity() != "") {
            content += this.getAmenity();
        }

        if (this.getTown() != "") {
            content += this.getTown() + ", ";
        }
        if (this.getVillage() != "") {
            content += this.getVillage() + ", ";
        }
        if (this.getCounty() != "") {
            content += this.getCounty() + ", ";
        }
        if (this.getCountry() != "") {
            content += this.getCountry();
        }

        if (this.getRoad() != "") {
            content += this.getRoad();
        }

        if (this.getRailway() != "") {
            content += ", Railway";
        } else if (this.getEmergency() != "") {
            content += ", Emergency";
        }
        return content;
    }

    public String getRoad() {
        return road;
    }

    public void setRoad(String road) {
        this.road = road;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getAmenity() {
        return amenity;
    }

    public void setAmenity(String amenity) {
        this.amenity = amenity;
    }

    public String getRailway() {
        return railway;
    }

    public void setRailway(String railway) {
        this.railway = railway;
    }

    public String getEmergency() {
        return emergency;
    }

    public void setEmergency(String emergency) {
        this.emergency = emergency;
    }

    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
    }

    public String getHamlet() {
        return hamlet;
    }

    public void setHamlet(String hamlet) {
        this.hamlet = hamlet;
    }

    public String getGeojson() {
        return geojson;
    }

    public void setGeojson(String geojson) {
        this.geojson = geojson;
    }

}
