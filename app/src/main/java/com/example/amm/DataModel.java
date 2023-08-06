package com.example.amm;

// DataModel.java
public class DataModel {
    private String title;
    private String dateAndTime;
    private String information;

    public DataModel() {
        // Default constructor required for Firebase
    }

    public DataModel(String title, String dateAndTime, String information) {
        this.title = title;
        this.dateAndTime = dateAndTime;
        this.information = information;
    }

    // Getters and setters (required for Firebase)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(String dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }
}
