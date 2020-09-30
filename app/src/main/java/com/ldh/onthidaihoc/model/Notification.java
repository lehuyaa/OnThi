package com.ldh.onthidaihoc.model;

public class Notification {

    private String image;
    private String content;
    private String time;

    public Notification(String image, String content, String time) {
        this.image = image;
        this.content = content;
        this.time = time;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
