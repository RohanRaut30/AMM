package com.example.amm;

public class Event {
    private String eventId;
    private String title;
    private String date;
    private String content;
    private String imageUrl;

    // Default constructor (required for Firebase)
    public Event() {
    }

    public Event(String eventId, String title, String date, String content, String imageUrl) {
        this.eventId = eventId;
        this.title = title;
        this.date = date;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public Event(String eventId, String title, String date, String content) {

    }

    // Getters and setters
    // ...

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
