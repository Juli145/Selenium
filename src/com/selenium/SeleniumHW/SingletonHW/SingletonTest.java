package com.selenium.SeleniumHW.SingletonHW;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SingletonTest {

    @BeforeClass
    public static void setUp(){
        Singleton.initialize();
    }


    @Test
    public void test() {
        WebDriverWait wait = new WebDriverWait(Singleton.driver, 10);
        // 3.1
        //3.2
        Singleton.driver.findElement(By.id("pre_rand")).click();
        //3.3
        Singleton.driver.findElement(By.id("domain")).click();
        Singleton.driver.findElement(By.xpath("//*[@id=\"pre_form\"]/div/div[2]/div/button[6]")).click();
        //3.4
        String nameOfEmail = Singleton.driver.findElement(By.id("pre_button")).getAttribute("value") + "@rover.info";
        //3.5
        Singleton.driver.findElement(By.id("pre_settings")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.
                xpath("/html/body/div[4]/div/form/div[3]/div[1]/div[2]/label[1]"))).click();
        Singleton.driver.findElement(By.cssSelector("#modal-settings > div > form > div.modal-body > div:nth-child(2) > input")).click();
        Singleton.driver.findElement(By.xpath("//*[@id=\"modal-settings\"]/div/form/div[2]/div/button")).click();
        //3.6
        Singleton.driver.findElement(By.id("pre_settings")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.
                xpath("/html/body/div[4]/div/form/div[3]/div[1]/div[2]/label[1]")));
        //проверить сохраненный результат
        Singleton.driver.findElement(By.xpath("//*[@id=\"modal-settings\"]/div/form/div[3]/div[1]/div[2]/label[1]"));
        String secretAddress = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("secret-address"))).
                getAttribute("textContent");
        Singleton.driver.findElement(By.xpath("//*[@id=\"modal-settings\"]/div/form/div[2]/div/button")).click();
        // 3.7
        WebElement randomName = Singleton.driver.findElement(By.id("pre_rand"));
        Assertions.assertTrue(randomName.isDisplayed());
        //3.8
        Assertions.assertTrue(Singleton.driver.getPageSource().contains("В ожидании новых писем..."));
        //3.9
        Singleton.driver.findElement(By.xpath("//*[@id=\"compose\"]")).click();
        //3.10
        WebElement sendButton = Singleton.driver.findElement(By.xpath("/html/body/div[3]/div/form[1]/div[5]/input"));
        wait.until(ExpectedConditions.visibilityOf(sendButton));
        Assertions.assertTrue(sendButton.isDisplayed());
        Singleton.driver.findElement(By.id("to")).sendKeys(nameOfEmail);
        Singleton.driver.findElement(By.id("subject")).sendKeys("Test");
        Singleton.driver.findElement(By.id("text")).sendKeys(secretAddress);
        // 3.11
        Singleton.driver.findElement(By.id("submit")).click();
        // 3.12
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"container-body\"]/div/div[1]/div[2]/div"))).click();
        // 3.13
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div[8]/div[2]/div/div[2]/button")));
        String senderName = Singleton.driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div[1]/div[2]/div[1]/div[1]/span")).getAttribute("textContent");
        Assertions.assertEquals(nameOfEmail, senderName);
        String themeOfEmail = Singleton.driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div[1]/div[2]/div[2]")).getAttribute("textContent");
        Assertions.assertEquals("Test", themeOfEmail);
        String textOfEmail = Singleton.driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div[1]/div[2]/div[3]")).getAttribute("textContent");
        Assertions.assertEquals(secretAddress, textOfEmail);
        // 3.14
        Singleton.driver.findElement(By.id("reply")).click();
        wait.until(ExpectedConditions.visibilityOf(Singleton.driver.findElement(By.id("submit"))));
        Singleton.driver.findElement(By.xpath("//*[@id=\"text\"]")).sendKeys("Test2");
        Singleton.driver.findElement(By.id("submit")).click();
        // 3.15
        Singleton.driver.findElement(By.id("back")).click();
        // 3.16
        String resendEmail = "Re: " + themeOfEmail;
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[8]/div[2]/div/div[2]/button[2]")));
        String toCheckRe = Singleton.driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div[1]/div[2]/div/div[3]/span")).getAttribute("textContent");
        Assertions.assertEquals(resendEmail, toCheckRe);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"container-body\"]/div/div[1]/div[2]"))).click();
        // 3.17
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[8]/div[2]/div/div[1]/div[2]/div[3]")));  // 3.17
        String textOfEmailRe = Singleton.driver.findElement(By.xpath("/html/body/div[8]/div[2]/div/div[1]/div[2]/div[3]")).getAttribute("textContent");
        Assertions.assertEquals("Test2", textOfEmailRe);
        // 3.18
        Singleton.driver.findElement(By.id("delete_mail")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"modal-destroy-mail\"]/div/div/div")));
        Singleton.driver.findElement(By.id("confirm_mail")).click();
        // 3.19
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("delete")));
        Assertions.assertFalse(Singleton.driver.getPageSource().contains("Re: Test"));
    }


    @AfterClass
    public static void tearDown(){
        Singleton.quit();
    }
}
