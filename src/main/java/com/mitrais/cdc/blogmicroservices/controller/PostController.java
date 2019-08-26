package com.mitrais.cdc.blogmicroservices.controller;

import com.mitrais.cdc.blogmicroservices.entity.Post;
import com.mitrais.cdc.blogmicroservices.exception.BadRequestAlertException;
import com.mitrais.cdc.blogmicroservices.payload.PostPayload;
import com.mitrais.cdc.blogmicroservices.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;


@RestController
@RequestMapping("/api")
public class PostController {

    private final Logger log = LoggerFactory.getLogger(PostController.class);

    private static final String ENTITY_NAME = "blogPost";

    @Value("${spring.application.name}")
    private String applicationName;

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping("/posts")
    public ResponseEntity<PostPayload> createPost(@Valid @RequestBody PostPayload postDTO) throws URISyntaxException {
        log.debug("REST request to save Post : {}", postDTO);
        if (postDTO.getId() != null) {
            throw new BadRequestAlertException("A new post cannot already have an ID", ENTITY_NAME, "id exists");
        }
        PostPayload result = postService.save(postDTO);
        return ResponseEntity.created(new URI("/api/posts/" + result.getId())).body(result);
    }

    @PutMapping("/posts")
    public ResponseEntity<PostPayload> updatePost(@Valid @RequestBody PostPayload postDTO) throws URISyntaxException {
        log.debug("REST request to update Post : {}", postDTO);
        if (postDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PostPayload result = postService.save(postDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostPayload>> getAllPosts(Pageable pageable) {
        log.debug("REST request to get a page of Posts");
        Page<PostPayload> page = postService.findAll(pageable);
        return ResponseEntity.ok().body(page.getContent());
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<PostPayload> getPost(@PathVariable Long id) {
        log.debug("REST request to get Post : {}", id);
        PostPayload postDTO = postService.findOne(id).get();
        return ResponseEntity.ok(postDTO);
    }

    @GetMapping("/post")
    public ResponseEntity<PostPayload> getPostByTitle(@RequestParam String title) {
        log.debug("REST request to get Post : {}", title);
        PostPayload postDTO = postService.findByTitle(title).get();
        return ResponseEntity.ok(postDTO);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<?> deletePost(@PathVariable Long id) {
        log.debug("REST request to delete Post : {}", id);
        PostPayload postPayload = postService.findOne(id).get();
        postService.delete(id);
        return ResponseEntity.ok(postPayload);
    }
}
