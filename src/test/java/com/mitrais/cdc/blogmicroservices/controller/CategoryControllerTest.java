package com.mitrais.cdc.blogmicroservices.controller;

import com.mitrais.cdc.blogmicroservices.payload.CategoryPayload;
import com.mitrais.cdc.blogmicroservices.services.CategoryService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryControllerTest {


    @Test
    @Transactional
    @Rollback(true)
    public void createCategory() {

    }

    @Test
    @Transactional
    @Rollback(true)
    public void updateCategory() {
    }

    @Test
    public void getAllCategories() {
    }

    @Test
    public void getCategory() {
    }

    @Test
    public void getCategoryByName() {
    }

    @Test
    @Transactional
    @Rollback(true)
    public void deleteCategory() {
    }
}