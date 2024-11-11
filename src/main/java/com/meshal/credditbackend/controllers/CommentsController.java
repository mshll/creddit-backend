package com.meshal.credditbackend.controllers;

import com.meshal.credditbackend.models.Comment;
import com.meshal.credditbackend.models.StatusMessage;
import com.meshal.credditbackend.services.PostsService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class CommentsController {

    private final PostsService postsService;


    public CommentsController(PostsService postsService) {
        this.postsService = postsService;
    }

    @PostMapping("/posts/{id}/comments")
    public ResponseEntity<Object> postComment(@PathVariable String id, @RequestBody Comment comment) {
        try {
            postsService.addComment(id, comment);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(new StatusMessage(e.getMessage()));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(comment);
    }

    @DeleteMapping("/posts/comments/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable String id) {
        boolean deleted = postsService.deleteComment(postsService.getPostFromCommentId(id), id);
        if (!deleted) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusMessage("Comment with ID " + id + " not found"));
        }
        return ResponseEntity.ok(new StatusMessage("Comment has been deleted successfully"));

    }
}
