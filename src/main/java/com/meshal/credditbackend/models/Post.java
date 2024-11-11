package com.meshal.credditbackend.models;

import java.util.ArrayList;
import java.util.List;

public class Post {
    private static long idCounter = 1;
    private String id;
    private String title;
    private String description;
    private List<Comment> comments;

    public Post(String title, String description) {
        this.id = String.valueOf(idCounter++);
        this.title = title;
        this.description = description;
        this.comments = new ArrayList<>();
    }

    public String getId() {
        return id;
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

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
