package com.example.studyplanner;

public class Event {

    private String title,type,date,time,des,id;

    public Event(String title, String type, String date, String time, String des,String id) {
        this.title = title;
        this.type = type;
        this.date = date;
        this.time = time;
        this.des = des;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getDes() {
        return des;
    }

    public String getId(){ return id; }
}
