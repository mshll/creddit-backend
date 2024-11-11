package com.meshal.credditbackend.services;

import com.meshal.credditbackend.models.Comment;
import com.meshal.credditbackend.models.Post;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostsService {

    private final List<Post> posts = new ArrayList<>();

    public PostsService() {
    }

    public List<Post> getPosts() {
        return posts;
    }

    public List<Post> getPosts(String title) {
        List<Post> filteredPosts = new ArrayList<>();
        for (Post post : posts) {
            if (post.getTitle().toLowerCase().contains(title.toLowerCase())) {
                filteredPosts.add(post);
            }
        }
        return filteredPosts;
    }

    public Post getPostById(String id) {
        for (Post post : posts) {
            if (post.getId().equals(id)) {
                return post;
            }
        }
        return null;
    }

    public void addPost(Post post) {
        if (post.getTitle() == null || post.getTitle().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post title is required");
        }

        if (post.getDescription() == null || post.getDescription().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Post description is required");
        }

        posts.add(post);
    }

    public boolean deletePost(String id) {
        for (Post post : posts) {
            if (post.getId().equals(id)) {
                posts.remove(post);
                return true;
            }
        }
        return false;
    }

    public void addComment(String postID, Comment comment) {
        if (comment.getUsername() == null || comment.getUsername().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment username is required");
        }

        if (comment.getComment() == null || comment.getComment().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Comment content is required");
        }

        if (getPostById(postID) == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }

        getPostById(postID).getComments().add(comment);
    }

    public Post getPostFromCommentId(String commentId) {
        for (Post post : posts) {
            for (Comment comment : post.getComments()) {
                if (comment.getId().equals(commentId)) {
                    return post;
                }
            }
        }
        return null;
    }

    public boolean deleteComment(Post post, String commentId) {
        
        for (Comment comment : post.getComments()) {
            if (comment.getId().equals(commentId)) {
                getPostFromCommentId(commentId).getComments().remove(comment);
                return true;
            }
        }
        return false;
    }
}
