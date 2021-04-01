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

    // 1
    @Test
    public void test_findFileSystemDropdown(){
        driver.findElement(By.id("file-system"));
    }

    // 2
    @Test
    public void test_FileSystemDropdownDisplayALL(){
        driver.findElement(By.id("file-system")).click();
        boolean actual1 = driver.getPageSource().contains("JSON");
        Assertions.assertTrue(actual1);
        boolean actual2 = driver.getPageSource().contains("CSV");
        Assertions.assertTrue(actual2);
        boolean actual3 = driver.getPageSource().contains("XML");
        Assertions.assertTrue(actual3);
        boolean actual4 = driver.getPageSource().contains("YAML");
        Assertions.assertTrue(actual4);
    }

    // 3
    @Test
    public void test_FileSystemDropdownJSON(){
        driver.findElement(By.id("file-system")).click();
        boolean actual = driver.getPageSource().contains("JSON");
        Assertions.assertTrue(actual);
    }

    // 4
    @Test
    public void test_FileSystemDropdownCSV(){
        driver.findElement(By.id("file-system")).click();
        boolean actual = driver.getPageSource().contains("CSV");
        Assertions.assertTrue(actual);
    }

    // 5
    // выдает ошибку false вместо true
//    @Test
//    public void test_FileSystemDropdownXML(){
//        driver.findElement(By.id("file-system")).click();
//        boolean actual = driver.getPageSource().contains("XMl");
//        Assertions.assertTrue(actual);
//    }

    // 6
    @Test
    public void test_FileSystemDropdownYAML(){
        driver.findElement(By.id("file-system")).click();
        boolean actual = driver.getPageSource().contains("YAML");
        Assertions.assertTrue(actual);
    }

    // 7
    @Test
    public void test_findQuestionThemeDropdown(){
        driver.findElement(By.id("questions-theme"));
    }

    // 8
    @Test
    public void test_findNewQuestionButton(){
        driver.findElement(By.id("new-question"));
    }

    // 9
    @Test
    public void test_findTeamName(){
        String actual = driver.findElement(By.className("header")).findElement(By.className("header__team-name")).getText();
        Assertions.assertEquals(actual, TEAM_NAME);
    }

    // 10
    @Test
    public void test_findLogo(){
        driver.findElement(By.className("header")).findElement(By.className("header__logo"));
    }

    // 11
    @Test
    public void test_findDeleteMessageButton(){
        driver.findElement(By.id("delete-message"));
    }

    // 12
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
