package com.example.handy.core.bean;

public class RecommendCourseData {

    private String title;
    private String description;
    private String author;
    private String imgUrl;

    public RecommendCourseData(String title, String description, String author, String imgUrl) {
        this.title = title;
        this.description = description;
        this.author = author;
        this.imgUrl = imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
