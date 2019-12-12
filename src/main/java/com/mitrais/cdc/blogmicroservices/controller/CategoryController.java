/**
 * <h1>Category Controller</h1>
 * This class will be used to setup controller for
 * Category API.
 *
 * @author Syarif Hidayat
 * @version 1.0
 * @since 2019-08-20
 * */

package com.mitrais.cdc.blogmicroservices.controller;

import com.mitrais.cdc.blogmicroservices.exception.BadRequestAlertException;
import com.mitrais.cdc.blogmicroservices.payload.CategoryPayload;
import com.mitrais.cdc.blogmicroservices.services.CategoryService;
import com.mitrais.cdc.blogmicroservices.utility.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URISyntaxException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CategoryController extends CrossOriginController{

    private final Logger log = LoggerFactory.getLogger(CategoryController.class);

    private static final String ENTITY_NAME = "blogCategory";

    @Value("${spring.application.name}")
    private String applicationName;

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/categories")
    public ResponseEntity<CategoryPayload> createCategory(@Valid @RequestBody CategoryPayload categoryDTO) throws URISyntaxException {
        log.debug("REST request to save Category : {}", categoryDTO);
        if (categoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new category cannot already have an ID", ENTITY_NAME, "idexists");
        }

        return ResponseEntity.ok(categoryService.save(categoryDTO));
    }

    @PutMapping("/categories")
    public ResponseEntity<CategoryPayload> updateCategory(@Valid @RequestBody CategoryPayload categoryDTO) throws URISyntaxException {
        log.debug("REST request to update Category : {}", categoryDTO);
        if (categoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }

        return ResponseEntity.ok().body(categoryService.save(categoryDTO));
    }

    @GetMapping("/categories")
    public List<CategoryPayload> getAllCategories() {
        log.debug("REST request to get all Categories");
        log.info("Request Header's Token: {}", UserContextHolder.getContext().getAuthToken());

        return categoryService.findAll();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryPayload> getCategory(@PathVariable Long id) {
        log.debug("REST request to get Category : {}", id);

        return ResponseEntity.ok(categoryService.findOne(id).get());
    }

    @GetMapping("/category")
    public ResponseEntity<CategoryPayload> getCategoryByName(@RequestParam String name) {
        log.debug("REST request to get Category : {}", name);

        return ResponseEntity.ok(categoryService.findByName(name).get());
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryPayload> deleteCategory(@PathVariable Long id) {
        log.debug("REST request to delete Category : {}", id);
        CategoryPayload categoryPayload = categoryService.findOne(id).get();
        categoryService.delete(id);
        return ResponseEntity.ok(categoryPayload);
    }
}
