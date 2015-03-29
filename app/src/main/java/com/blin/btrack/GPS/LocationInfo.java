package com.blin.btrack.GPS;

/**
 * Created by Lin on 2015/3/29.
 */
public class LocationInfo {
    private String LocationTime;
    private String Latitude;
    private String Longitude;
    private String LocationType;
    private String Radius;

    public String getLocationTime() {
        return LocationTime;
    }

    public void setLocationTime(String locationTime) {
        LocationTime = locationTime;
    }

    public String getLatitude() {
        return Latitude;
    }

    public void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public String getLongitude() {
        return Longitude;
    }

    public void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public String getLocationType() {
        return LocationType;
    }

    public void setLocationType(String locationType) {
        LocationType = locationType;
    }

    public String getRadius() {
        return Radius;
    }

    public void setRadius(String radius) {
        Radius = radius;
    }
}