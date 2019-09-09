package com.mitrais.cdc.blogmicroservices.frontendstepdefinition.comment;

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

public class CommentStepDefinition {

    WebDriver webDriver;

    @Given("User is on Blog Home page")
    public void user_is_on_Blog_Home_page() {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        System.out.println("Path:"+System.getProperty("webdriver.chrome.driver"));
        webDriver = new ChromeDriver();
        webDriver.get("http://localhost:3000/login");

        webDriver.findElement(By.xpath("//*[@name='username']")).sendKeys("admin");
        webDriver.findElement(By.xpath("//*[@name='password']")).sendKeys("admin123");
        webDriver.findElement(By.xpath("//*[@id='submit']")).click();
        webDriver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @When("User click one blog with title (.*)")
    public void user_click_one_blog_with_title_Linux_Tutorial(String tutorial) {
        webDriver.findElement(By.xpath("//*[@id=\"data\"]/div[10]/div/p[2]/a")).click();
    }

    @Then("User will be directed to the blog page of (.*)")
    public void user_will_be_directed_to_the_blog_page_of_Linux_Tutorial(String title) {
        Assert.assertTrue(webDriver.findElement(By.xpath("//*[@id='display-comment']")).isDisplayed());
        Assert.assertTrue(webDriver.findElement(By.xpath("//*[@id='send-comment']")).isDisplayed());
        String blogTitle = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div/main/div/div/div[3]/div/div[2]/div/div/div/center/h2")).getText();
        assertThat("Linux Tutorial", is(blogTitle));
    }

    @Then("On that page, user send comment (.*)")
    public void on_that_page_user_send_comment_Test_Comment(String comment) {

    }

    @Then("Comment (.*) will be displayed on Blog Comment section")
    public void comment_will_be_displayed_on_Blog_Comment_section(String comment) {

    }
}
