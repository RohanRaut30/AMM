package com.example.amm;

public class Event {
    private String eventId;
    private String title;
    private String date;
    private String content;
    private String imageUrl;

    public Event() {
        // Required no-argument constructor for Firebase deserialization
    }

    public Event(String eventId, String title, String date, String content) {
        this.eventId = eventId;
        this.title = title;
        this.date = date;
        this.content = content;
    }

    public Event(String eventId, String title, String date, String content, String imageUrl) {
        this.eventId = eventId;
        this.title = title;
        this.date = date;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public String getEventId() {
        return eventId;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getContent() {
        return content;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
