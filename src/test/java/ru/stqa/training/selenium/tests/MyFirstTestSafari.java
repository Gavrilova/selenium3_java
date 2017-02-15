package ru.stqa.training.selenium.tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;


/**
 * Created by irinagavrilova on 2/2/17.
 */

public class MyFirstTestSafari {

  private SafariDriver driver;

  @Before
  public void start() {
    SafariOptions options = new SafariOptions();
    options.setUseCleanSession(true); //if you wish safari to forget session every time
    driver = new SafariDriver(options);
  }

  @Test
  public void myFirstTest() throws InterruptedException {
    driver.get("http://localhost/litecart/en/");
    WebElement firstProduct = driver.findElement(By.cssSelector("div#box-campaigns li.product a.link"));
    String productText = firstProduct.getText();
    System.out.println("getText = " + productText);
    System.out.println("getAttribute (href) = " + firstProduct.getAttribute("href"));


    String nameMainPage = firstProduct.getAttribute("title");
    System.out.println("getAttribute (title) = " + nameMainPage);
    String priceCampaignsMainPage = driver.findElement(By.cssSelector("div#box-campaigns strong.campaign-price")).getText();
    System.out.println(priceCampaignsMainPage);


    String priceRegularMainPage = driver.findElement(By.cssSelector("div#box-campaigns s.regular-price")).getText();

    System.out.println(driver.findElement(By.cssSelector("div#box-campaigns s.regular-price")).getSize());


    System.out.println(driver
            .findElement(By.cssSelector("div#box-campaigns strong.campaign-price"))
            .getText());
    System.out.println(driver.
            findElement(By.cssSelector("div#box-campaigns strong.campaign-price"))
            .getSize());

    Point location = driver.
            findElement(By.cssSelector("div#box-campaigns strong.campaign-price")).getLocation();
    Dimension size = driver.
            findElement(By.cssSelector("div#box-campaigns strong.campaign-price")).getSize();

    System.out.println("location = " + location + "; size = " + size);


    String urlFirstProduct = driver.getCurrentUrl();
    System.out.println(urlFirstProduct);
    firstProduct.click();  //go to Product page
    new WebDriverWait(driver, 5).until((WebDriver dr) -> dr.findElement(By.cssSelector("h1.title"))); //wait till h1.title will be present
    System.out.println(driver.getCurrentUrl());


    WebElement productPage = driver.findElement(By.cssSelector("h1.title"));
    System.out.println(productPage.getText());
    assertEquals(productPage.getText(), nameMainPage); //assert the same names
    assertEquals(driver.findElement(By.cssSelector("strong.campaign-price")).getText(), priceCampaignsMainPage);
    assertEquals(driver.findElement(By.cssSelector("s.regular-price")).getText(), priceRegularMainPage);

    String color = driver.findElement(By.cssSelector("strong.campaign-price")).getCssValue("color");
    System.out.println("color = " + color);

    // обычная цена серая и зачёркнутая, а акционная цена красная и жирная (это надо проверить на каждой странице независимо, при этом цвета на разных страницах могут не совпадать)

//return to Main Page


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


    new WebDriverWait(driver, 5).until((WebDriver dr) -> dr.findElement(By.cssSelector("h1.title")));
    System.out.println(driver.getCurrentUrl());
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }

}