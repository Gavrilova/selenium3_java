package ru.stqa.training.selenium.tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Created by irinagavrilova on 2/2/17.
 */

public class MyFirstTestSafari {

  private SafariDriver driver;

  @Before
  public void start() {
    SafariOptions options = new SafariOptions();
    options.setUseCleanSession(true); //if you wish safari to forget session everytime
    driver = new SafariDriver(options);
  }

  @Test
  public void myFirstTest() throws InterruptedException {
    driver.get("http://localhost/litecart/en/");
    WebElement firstProduct = driver.findElement(By.cssSelector("div#box-campaigns li.product a.link"));
    String productName = firstProduct.getText();
    System.out.println("getText = " + productName);
    System.out.println("getAttibute (href) = " + firstProduct.getAttribute("href"));
    System.out.println("getAttibute (title) = " + firstProduct.getAttribute("title"));

    String urlFirstProduct = driver.getCurrentUrl();
    System.out.println(urlFirstProduct);
    firstProduct.click();
    //  sleep(5000);

    new WebDriverWait(driver, 5).until((WebDriver dr1) -> dr1.findElement(By.cssSelector("h1.title")));
    System.out.println(driver.getCurrentUrl());

    driver.get("http://localhost/litecart/en/");

    System.out.println("refresh to http://localhost/litecart/en/");
    System.out.println(driver.getCurrentUrl());

    WebElement firstProduct2 = driver.findElement(By.cssSelector("li.product a.link"));
    String productName2 = firstProduct2.getText();
    System.out.println("getText = " + productName2);
    System.out.println("getAttibute (href) = " + firstProduct2.getAttribute("href"));
    System.out.println("getAttibute (title) = " + firstProduct2.getAttribute("title"));

    String urlFirstProduct2 = driver.getCurrentUrl();
    System.out.println(urlFirstProduct2);
    firstProduct2.click();
    //  sleep(5000);

    new WebDriverWait(driver, 5).until((WebDriver dr1) -> dr1.findElement(By.cssSelector("h1.title")));
    System.out.println(driver.getCurrentUrl());
  }

  @After
  public void stop() {
    driver.quit();
  }

}