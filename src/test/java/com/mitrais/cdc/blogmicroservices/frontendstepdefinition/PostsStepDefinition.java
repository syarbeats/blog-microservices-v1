package com.mitrais.cdc.blogmicroservices.frontendstepdefinition;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class PostsStepDefinition {
}
    @Given("User click Create Blog Menu")
    public void user_click_Create_Blog_Menu() {

    }

    @When("User on Create Blog page, insert new blog with title Oracle OSB with category Enterprise Application Integration and blog testblog")
    public void user_on_Create_Blog_page_insert_new_blog_with_title_Oracle_OSB_with_category_Enterprise_Application_Integration_and_blog_testblog() {

    }

    @Then("Blog created successfully, user will direct to home page")
    public void blog_created_successfully_user_will_direct_to_home_page() {

    }

    @Then("The created blog will be found on that Home Page")
    public void the_created_blog_will_be_found_on_that_Home_Page() {

    }
}
