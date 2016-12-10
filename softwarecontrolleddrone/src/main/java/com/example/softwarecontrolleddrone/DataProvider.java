/*
Team Members: Denis Stepanov, Kevin Libdan, Pramit Roy
Team name: Skynet
*/

package com.example.softwarecontrolleddrone;

public class DataProvider {

    private String date, flightDuration, curLocation;

    public String getFlightDuration() {
        return flightDuration;
    }

    public void setFlightDuration(String flightDuration) {
        this.flightDuration = flightDuration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {

        this.date = date;
    }

    public String getCurLocation() {
        return curLocation;
    }

    public void setCurLocation(String curLocation) {
        this.curLocation = curLocation;
    }

    public DataProvider(String date, String flightDuration, String curLocation)
    {
        this.date = date;
        this.flightDuration = flightDuration;
        this.curLocation = curLocation;
    }
}
