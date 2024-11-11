package com.meshal.credditbackend.models;

public class Comment {
    private static long commentIdCounter = 1;
    private String id;
    private String username;
    private String comment;


    public Comment(String username, String comment) {
        this.username = username;
        this.comment = comment;
        this.id = String.valueOf(commentIdCounter++);
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
