package com.selenium.SeleniumHW.SingletonHW;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public  class Singleton {
    public static WebDriver driver = null;
    public static String browserName = "chrome";

    public static void initialize() {
        if (driver == null) {
           if(browserName.equalsIgnoreCase("chrome")){
               System.setProperty("webdriver.chrome.driver","D://JuliDirectory//Selenium drivers and jars//Drivers//chromedrivers/chromedriver.exe");
               driver = new ChromeDriver();
           }
        }
        driver.manage().window().maximize();
        driver.get("https://tempmail.plus/ru/");
    }

    public static void quit(){
        System.out.println("quitting the browser");
        driver.quit();
        driver = null; // хз зачем пока что
    }
}
