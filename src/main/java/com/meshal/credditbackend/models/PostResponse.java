package com.meshal.credditbackend.models;

public class PostResponse {
    private final Post post;

    public PostResponse(Post post) {
        this.post = post;
    }

    public Post getPost() {
        return post;
    }
}
