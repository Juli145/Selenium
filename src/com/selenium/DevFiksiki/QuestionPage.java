package com.selenium.DevFiksiki;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.selenium.DevFiksiki.Constant.*;


public class QuestionPage {
    WebDriver driver;

    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "D://JuliDirectory//Selenium drivers and jars//Drivers//chromedrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(QuestionPageURL);
    }

    @Test
    public void test_findFileSystemDropdown(){
        driver.findElement(By.id("file-system"));
    }

    @Test
    public void test_findQuestionThemeDropdown(){
        driver.findElement(By.id("questions-theme"));
    }

    @Test
    public void test_findNewQuestionButton(){
        driver.findElement(By.id("new-question"));
    }

    @Test
    public void test_findTeamName(){
        String actual = driver.findElement(By.className("header")).findElement(By.className("header__team-name")).getText();
        Assertions.assertEquals(actual, TEAM_NAME);
    }

    @Test
    public void test_findLogo(){
        driver.findElement(By.className("header")).findElement(By.className("header__logo"));
    }

    @Test
    public void test_findDeleteMessageButton(){
        driver.findElement(By.id("delete-message"));
    }

    @Test
    public void test_findPopUp(){
        driver.findElement(By.id("delete-message")).click();
        driver.findElement(By.className("popup"));
    }

    @After
    public void close() {
        driver.quit();
    }
}
