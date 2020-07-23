package com.monwareclinical.model;

public class Event {

    String title;
    String date;
    Clinic place;

    public Event(String title, String date, Clinic place) {
        this.title = title;
        this.date = date;
        this.place = place;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public Clinic getPlace() {
        return place;
    }

    @Override
    public String toString() {
        return "Event{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                ", place='" + place + '\'' +
                '}';
    }
}