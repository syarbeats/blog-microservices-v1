package com.mitrais.cdc.blogmicroservices.controller;

import com.mitrais.cdc.blogmicroservices.exception.BadRequestAlertException;
import com.mitrais.cdc.blogmicroservices.payload.CommentPayload;
import com.mitrais.cdc.blogmicroservices.services.CommentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;


@RestController
@RequestMapping("/api")
public class CommentController extends CrossOriginController{

    private final Logger log = LoggerFactory.getLogger(CommentController.class);

    private static final String ENTITY_NAME = "blogComment";

    @Value("${spring.application.name}")
    private String applicationName;

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/comments")
    public ResponseEntity<CommentPayload> createComment(@RequestBody CommentPayload commentDTO) throws URISyntaxException {
        log.debug("REST request to save Comment : {}", commentDTO);
        if (commentDTO.getId() != null) {
            throw new BadRequestAlertException("A new comment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CommentPayload result = commentService.save(commentDTO);
        return ResponseEntity.created(new URI("/api/comments/" + result.getId())).body(result);
    }


    @PutMapping("/comments")
    public ResponseEntity<CommentPayload> updateComment(@RequestBody CommentPayload commentDTO) throws URISyntaxException {
        log.debug("REST request to update Comment : {}", commentDTO);
        if (commentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CommentPayload result = commentService.save(commentDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/comments")
    public List<CommentPayload> getAllComments() {
        log.debug("REST request to get all Comments");
        return commentService.findAll();
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<CommentPayload> getComment(@PathVariable Long id) {
        log.debug("REST request to get Comment : {}", id);
        CommentPayload commentDTO = commentService.findOne(id).get();
        return ResponseEntity.ok(commentDTO);
    }

    @DeleteMapping("/comments/{id}")
    public ResponseEntity<CommentPayload> deleteComment(@PathVariable Long id) {
        log.debug("REST request to delete Comment : {}", id);
        CommentPayload commentPayload = commentService.findOne(id).get();
        commentService.delete(id);
        return ResponseEntity.ok(commentPayload);
    }

    @GetMapping("/comments-by-title")
    public ResponseEntity<List<CommentPayload>> getCommentByTitle(@RequestParam String title){
        log.debug("Get All Comment for certain post title");
        List<CommentPayload> commentPayloadList = commentService.findAllCommentByPostTitle(title);
        return ResponseEntity.ok(commentPayloadList);
    }

    @GetMapping("/comment-by-comment")
    public ResponseEntity<CommentPayload> getCommenDatatByComment(@RequestParam String comment){
        log.debug("Get Comment Data for certain comment");
        CommentPayload commentPayload = commentService.findByComment(comment).get();
        return ResponseEntity.ok(commentPayload);
    }
}
