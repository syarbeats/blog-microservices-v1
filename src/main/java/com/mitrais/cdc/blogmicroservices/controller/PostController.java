package com.mitrais.cdc.blogmicroservices.controller;

import com.mitrais.cdc.blogmicroservices.exception.BadRequestAlertException;
import com.mitrais.cdc.blogmicroservices.payload.PostPayload;
import com.mitrais.cdc.blogmicroservices.services.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class PostController extends CrossOriginController{

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
        log.info("REST request to save Post : {}", postDTO);
        ZonedDateTime zone =ZonedDateTime.now();
        postDTO.setCreatedDate(zone);

        if (postDTO.getId() != null) {
            throw new BadRequestAlertException("A new post cannot already have an ID", ENTITY_NAME, "id exists");
        }
        PostPayload result = postService.save(postDTO);

        return ResponseEntity.created(new URI("/api/posts/" + result.getId())).body(result);

    }

    @PutMapping("/posts")
    public ResponseEntity<PostPayload> updatePost(@Valid @RequestBody PostPayload postDTO)  {
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
        Optional<PostPayload> postDTO = postService.findOne(id);
        PostPayload result = null;

        if(postDTO.isPresent()){
           result = postDTO.get();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/post")
    public ResponseEntity<PostPayload> getPostByTitle(@RequestParam String title) {
        log.debug("REST request to get Post : {}", title);
        Optional<PostPayload> postDTO = postService.findByTitle(title);
        PostPayload result = null;

        if(postDTO.isPresent()){
            result = postDTO.get();
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<PostPayload> deletePost(@PathVariable Long id) {
        log.debug("REST request to delete Post : {}", id);
        Optional<PostPayload> postPayload = postService.findOne(id);
        PostPayload result = null;

        if(postPayload.isPresent()){
            result = postPayload.get();
            postService.delete(id);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        return ResponseEntity.ok(result);
    }

    @GetMapping("/posts/category")
    public ResponseEntity<List<PostPayload>> findPostsByCategory(Pageable pageable, @RequestParam("category") String category){
        log.debug("REST request to get posts by category {}", category);
        Page<PostPayload> postPayload = postService.findByCategory(pageable, category);

        return ResponseEntity.ok(postPayload.getContent());
    }

    @GetMapping("/posts/today")
    public ResponseEntity<List<PostPayload>> findPostByToday(Pageable pageable){
        ZonedDateTime beforeToday = ZonedDateTime.now().minusDays(1);
        ZonedDateTime today = ZonedDateTime.now();
        Page<PostPayload> postPayloads = postService.findByCreatedDate(pageable, today, beforeToday);

        return ResponseEntity.ok(postPayloads.getContent());

    }

    @GetMapping("/posts/search")
    public ResponseEntity<List<PostPayload>> findPostByKeyword(@RequestParam("keyword") String keyword, Pageable pageable){
        log.debug("REST request to get posts by keyword");
        Page<PostPayload> postPayloads = postService.findByKeywords(pageable, keyword);

        return ResponseEntity.ok(postPayloads.getContent());
    }
}
