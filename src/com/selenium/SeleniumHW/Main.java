package com.selenium.SeleniumHW;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class Main {

    @Test
    public void test() {
        System.setProperty("webdriver.chrome.driver","D://JuliDirectory//Selenium drivers and jars//Drivers//chromedrivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        // 3.1
        driver.get("https://tempmail.plus/ru/");
        driver.manage().window().maximize();
        //3.2
        driver.findElement(By.id("pre_rand")).click();
        //3.3
        driver.findElement(By.id("domain")).click();
        driver.findElement(By.xpath("//*[@id=\"pre_form\"]/div/div[2]/div/button[6]")).click();
        //3.4
        String nameOfEmail = driver.findElement(By.id("pre_button")).getAttribute("value") + "@rover.info";
        //3.5
        driver.findElement(By.id("pre_settings")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.
                xpath("/html/body/div[4]/div/form/div[3]/div[1]/div[2]/label[1]"))).click();
        driver.findElement(By.cssSelector("#modal-settings > div > form > div.modal-body > div:nth-child(2) > input")).click();
        driver.findElement(By.xpath("//*[@id=\"modal-settings\"]/div/form/div[2]/div/button")).click();
        //3.6
        driver.findElement(By.id("pre_settings")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.
                xpath("/html/body/div[4]/div/form/div[3]/div[1]/div[2]/label[1]")));
        //проверить сохраненный результат
        driver.findElement(By.xpath("//*[@id=\"modal-settings\"]/div/form/div[3]/div[1]/div[2]/label[1]"));
        String secretAddress = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("secret-address"))).
                getAttribute("textContent");
        driver.findElement(By.xpath("//*[@id=\"modal-settings\"]/div/form/div[2]/div/button")).click();
        // 3.7
        WebElement randomName = driver.findElement(By.id("pre_rand"));
        Assertions.assertTrue(randomName.isDisplayed());
        //3.8
        Assertions.assertTrue(driver.getPageSource().contains("В ожидании новых писем..."));
        //3.9
        driver.findElement(By.xpath("//*[@id=\"compose\"]")).click();
        //3.10
        WebElement sendButton = driver.findElement(By.xpath("/html/body/div[3]/div/form[1]/div[5]/input"));
        wait.until(ExpectedConditions.visibilityOf(sendButton));
        Assertions.assertTrue(sendButton.isDisplayed());
        driver.findElement(By.id("to")).sendKeys(nameOfEmail);
        driver.findElement(By.id("subject")).sendKeys("Test");
        driver.findElement(By.id("text")).sendKeys(secretAddress);
        // 3.11
        driver.findElement(By.id("submit")).click();
        // 3.12
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"container-body\"]/div/div[1]/div[2]/div"))).click();
        // 3.13
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[8]/div[2]/div/div[2]/button")));
        String senderName = driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div[1]/div[2]/div[1]/div[1]/span")).getAttribute("textContent");
        Assertions.assertEquals(nameOfEmail, senderName);
        String themeOfEmail = driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div[1]/div[2]/div[2]")).getAttribute("textContent");
        Assertions.assertEquals("Test", themeOfEmail);
        String textOfEmail = driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div[1]/div[2]/div[3]")).getAttribute("textContent");
        Assertions.assertEquals(secretAddress, textOfEmail);
        // 3.14
        driver.findElement(By.id("reply")).click();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("submit"))));
        driver.findElement(By.xpath("//*[@id=\"text\"]")).sendKeys("Test2");
        driver.findElement(By.id("submit")).click();
        // 3.15
        driver.findElement(By.id("back")).click();
        // 3.16
        String resendEmail = "Re: " + themeOfEmail;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[8]/div[2]/div/div[2]/button[2]")));
        String toCheckRe = driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div[1]/div[2]/div/div[3]/span")).getAttribute("textContent");
        Assertions.assertEquals(resendEmail, toCheckRe);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"container-body\"]/div/div[1]/div[2]"))).click();
        // 3.17
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[8]/div[2]/div/div[1]/div[2]/div[3]")));  // 3.17
        String textOfEmailRe = driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div[1]/div[2]/div[3]")).getAttribute("textContent");
        Assertions.assertEquals("Test2", textOfEmailRe);
        // 3.18
        driver.findElement(By.id("delete_mail")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"modal-destroy-mail\"]/div/div/div")));
        driver.findElement(By.id("confirm_mail")).click();
        // 3.19
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete")));
        Assertions.assertFalse(driver.getPageSource().contains("Re: Test"));
    }
}

