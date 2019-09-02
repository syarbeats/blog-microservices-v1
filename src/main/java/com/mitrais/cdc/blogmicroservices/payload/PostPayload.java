package com.mitrais.cdc.blogmicroservices.payload;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;


public class PostPayload implements Serializable {

    private Long id;

    @NotNull
    private String title;

    @Lob
    private String content;

    private ZonedDateTime createdDate;


    private Long categoryId;

    private String categoryName;

    public PostPayload() {
    }

    public PostPayload(@NotNull String title, String content, @NotNull ZonedDateTime createdDate, Long categoryId, String categoryName) {
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public PostPayload(Long id, @NotNull String title, String content, @NotNull ZonedDateTime createdDate, Long categoryId, String categoryName) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdDate = createdDate;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ZonedDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(ZonedDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PostPayload postPayload = (PostPayload) o;
        if (postPayload.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), postPayload.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PostDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", content='" + getContent() + "'" +
            ", createdDate='" + getCreatedDate() + "'" +
            ", category=" + getCategoryId() +
            ", category='" + getCategoryName() + "'" +
            "}";
    }
}
