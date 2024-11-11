package com.meshal.credditbackend.controllers;

import com.meshal.credditbackend.models.Post;
import com.meshal.credditbackend.models.StatusMessage;
import com.meshal.credditbackend.services.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class PostsController {

    private final PostsService postsService;

    @Autowired
    public PostsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts(@RequestParam(required = false) String title) {
        return title == null ? postsService.getPosts() : postsService.getPosts(title);
    }

    @PostMapping("/posts")
    public ResponseEntity<Object> createPost(@RequestBody Post post) {
        try {
            postsService.addPost(post);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(new StatusMessage(e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(post);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Object> getPost(@PathVariable String id) {
        Post post = postsService.getPostById(id);
        if (post == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusMessage("Post with ID " + id + " not found"));
        }
        return ResponseEntity.ok(post);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<StatusMessage> deletePost(@PathVariable String id) {
        boolean deleted = postsService.deletePost(id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusMessage("Post with ID " + id + " not found"));
        }
        return ResponseEntity.ok(new StatusMessage("Post has been deleted successfully"));
    }
}
