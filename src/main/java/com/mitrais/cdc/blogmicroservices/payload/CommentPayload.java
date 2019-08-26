package com.mitrais.cdc.blogmicroservices.payload;

import java.io.Serializable;
import java.util.Objects;


public class CommentPayload implements Serializable {

    private Long id;

    private String username;

    private String comment;


    private Long postId;

    private String postTitle;

    public CommentPayload(Long id, String username, String comment, Long postId, String postTitle) {
        this.id = id;
        this.username = username;
        this.comment = comment;
        this.postId = postId;
        this.postTitle = postTitle;
    }

    public CommentPayload() {
    }

    public CommentPayload(String username, String comment, Long postId, String postTitle) {
        this.username = username;
        this.comment = comment;
        this.postId = postId;
        this.postTitle = postTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CommentPayload commentPayload = (CommentPayload) o;
        if (commentPayload.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), commentPayload.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
            "id=" + getId() +
            ", username='" + getUsername() + "'" +
            ", comment='" + getComment() + "'" +
            ", post=" + getPostId() +
            ", post='" + getPostTitle() + "'" +
            "}";
    }
}
