package com.mitrais.cdc.blogmicroservices.controller;

import com.mitrais.cdc.blogmicroservices.exception.BadRequestAlertException;
import com.mitrais.cdc.blogmicroservices.payload.CategoryPayload;
import com.mitrais.cdc.blogmicroservices.services.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
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
        CategoryPayload result = categoryService.save(categoryDTO);
        return ResponseEntity.created(new URI("/api/categories/" + result.getId())).body(result);
    }

    @PutMapping("/categories")
    public ResponseEntity<CategoryPayload> updateCategory(@Valid @RequestBody CategoryPayload categoryDTO) throws URISyntaxException {
        log.debug("REST request to update Category : {}", categoryDTO);
        if (categoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CategoryPayload result = categoryService.save(categoryDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/categories")
    public List<CategoryPayload> getAllCategories() {
        log.debug("REST request to get all Categories");
        return categoryService.findAll();
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<CategoryPayload> getCategory(@PathVariable Long id) {
        log.debug("REST request to get Category : {}", id);
        CategoryPayload categoryDTO = categoryService.findOne(id).get();
        return ResponseEntity.ok(categoryDTO);
    }

    @GetMapping("/category")
    public ResponseEntity<CategoryPayload> getCategoryByName(@RequestParam String name) {
        log.debug("REST request to get Category : {}", name);
        CategoryPayload categoryDTO = categoryService.findByName(name).get();
        return ResponseEntity.ok(categoryDTO);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<CategoryPayload> deleteCategory(@PathVariable Long id) {
        log.debug("REST request to delete Category : {}", id);
        CategoryPayload categoryPayload = categoryService.findOne(id).get();
        categoryService.delete(id);
        return ResponseEntity.ok(categoryPayload);
    }
}
