package com.selenium.DevFiksiki;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static com.selenium.DevFiksiki.Constant.*;


public class HomePage {
    WebDriver driver;


    @Before
    public void init() {
        System.setProperty("webdriver.chrome.driver", "D://JuliDirectory//Selenium drivers and jars//Drivers//chromedrivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get(HomePageURL);
    }


    @Test
    public void test_questionsLink() {
        driver.findElement(By.linkText("Question")).click();
        String actual = driver.getTitle();
        String expected = "Questions";
        Assertions.assertEquals(actual, expected);
    }

    @After
    public void close() {
        driver.quit();
    }
}
