package com.example.huynhtruongthaoduyen;

public class Movie {
    private int poster;   // id ảnh drawable
    private String name;
    private String time;
    private String type;
    private String duration;
    private String room;
    private String price;
    private String description;

    public Movie(int poster, String name, String time, String type,
                 String duration, String room, String price, String description) {
        this.poster = poster;
        this.name = name;
        this.time = time;
        this.type = type;
        this.duration = duration;
        this.room = room;
        this.price = price;
        this.description = description;
    }

    public int getPoster() { return poster; }
    public String getName() { return name; }
    public String getTime() { return time; }
    public String getType() { return type; }
    public String getDuration() { return duration; }
    public String getRoom() { return room; }
    public String getPrice() { return price; }
    public String getDescription() { return description; }


}
