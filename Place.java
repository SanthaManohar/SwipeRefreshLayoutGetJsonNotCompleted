package basicandroid.com.swiperefreshlayoutgetjson;

public class Place {

//    private String place;
//    private String thumbnailUrl;
//    private String description;
//    private String besttime;
//    private String airport;
//    private String railwaystation;

    private String name;
    private String email;

//    public Place(String place, String thumbnailUrl, String description, String besttime, String airport, String railwaystation) {
//        this.place = place;
//        this.thumbnailUrl = thumbnailUrl;
//        this.description = description;
//        this.besttime = besttime;
//        this.airport = airport;
//        this.railwaystation = railwaystation;
//    }


    public Place(String name, String email) {
        this.name = name;
        this.email = email;
    }


    public Place() {

    }



    public String getName() {
        return name;
    }

    public void setName(String place) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String thumbnailUrl) {
        this.email = email;
    }



//    public String getPlace() {
//        return place;
//    }
//
//    public void setPlace(String place) {
//        this.place = place;
//    }
//
//    public String getThumbnailUrl() {
//        return thumbnailUrl;
//    }
//
//    public void setThumbnailUrl(String thumbnailUrl) {
//        this.thumbnailUrl = thumbnailUrl;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getBesttime() {
//        return besttime;
//    }
//
//    public void setBesttime(String besttime) {
//        this.besttime = besttime;
//    }
//
//    public String getAirport() {
//        return airport;
//    }
//
//    public void setAirport(String airport) {
//        this.airport = airport;
//    }
//
//    public String getRailwaystation() {
//        return railwaystation;
//    }
//
//    public void setRailwaystation(String railwaystation) {
//        this.railwaystation = railwaystation;
//    }
}