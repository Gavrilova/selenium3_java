package ru.stqa.training.selenium.tests;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Array;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;


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
   
    WebElement firstProduct
            = getFirstWebElementInCategory(By.cssSelector("div#box-campaigns li.product a.link"));

    String nameMainPage = getProductAttribute(firstProduct, "title");
    String productURL = getProductAttribute(firstProduct, "href");
    String priceCampaignsMainPage = getProductPrice(firstProduct, By.cssSelector("strong.campaign-price"));
    String priceRegularMainPage = getProductPrice(firstProduct, By.cssSelector("s.regular-price"));

    Point locationCampaign = getPoint(firstProduct, By.cssSelector("strong.campaign-price"));
    Dimension sizeCampaign = getDimension(firstProduct, By.cssSelector("strong.campaign-price"));
    String colorCampaigh = getValue(firstProduct, By.cssSelector("strong.campaign-price"), "color");
    String fontCampaigh = getValue(firstProduct, By.cssSelector("strong.campaign-price"), "font-weight");

    Point locationRegular = getPoint(firstProduct, By.cssSelector("s.regular-price"));
    Dimension sizeRegular = getDimension(firstProduct, By.cssSelector("s.regular-price"));
    String colorRegular = getValue(firstProduct, By.cssSelector("s.regular-price"), "color");
    String textDecorationRegular = getValue(firstProduct, By.cssSelector("s.regular-price"), "text-decoration");
    String fontRegular = getValue(firstProduct, By.cssSelector("s.regular-price"), "font-weight");

   // System.out.println("Campaign price = " + priceCampaignsMainPage + "; location = " + locationCampaign +
   //         "; size = " + sizeCampaign + "; color = " + colorCampaigh + fontCampaigh);
   // System.out.println("Regular price  = " + priceRegularMainPage + "; location = " + locationRegular +
   //         "; size = " + sizeRegular + "; color = " + colorRegular + textDecorationRegular + fontRegular);

    attributeCheck(colorCampaigh, fontCampaigh, colorRegular, textDecorationRegular);



    String urlFirstProduct = driver.getCurrentUrl();
    System.out.println(urlFirstProduct);

    firstProduct.click();  //go to Product page
    new WebDriverWait(driver, 5).until((WebDriver dr) -> dr.findElement(By.cssSelector("h1.title"))); //wait till h1.title will be present
    System.out.println(driver.getCurrentUrl());

    WebElement productPage = driver.findElement(By.cssSelector("h1.title"));
    System.out.println(productPage.getText());
    assertEquals(productPage.getText(), nameMainPage); //assert the same names
    assertEquals(driver.findElement(By.cssSelector("strong.campaign-price")).getText(), priceCampaignsMainPage); //assert the same Campaign price
    assertEquals(driver.findElement(By.cssSelector("s.regular-price")).getText(), priceRegularMainPage); //assert the same Regular price

  }

  private void attributeCheck(String colorCampaigh, String fontCampaigh, String colorRegular, String textDecorationRegular) {
    //verify that Campaign price is red and bold
    //verify that Regular price is grey and line-through

    assertEquals(fontCampaigh, "bold");
    assertEquals(textDecorationRegular, "line-through");

    String[] redColorArray = {"rgb(204, 0, 0)", "#cc0000", "#c00"};
    String[] greyColorArray = {"rgb(119, 119, 119)","#777777" , "#777"};

    assertEquals(getAssert(redColorArray, colorCampaigh), true);
    assertEquals(getAssert(greyColorArray, colorRegular), true);
  }

  private boolean getAssert(String[] array, String color) {
    Boolean bool = false;
    for (int i=0; i<array.length; i++) {
      if (color.equals(array[i])) { bool = true;}
    }
    return bool;
  }

  private String getValue(WebElement firstProduct, By locator, String string) {
    return firstProduct.findElement(locator).getCssValue(string);
  }


  private Dimension getDimension(WebElement webElement, By locator) {
    return webElement.findElement(locator).getSize();
  }

  private Point getPoint(WebElement webElement, By locator) {
    return webElement.findElement(locator).getLocation();
  }

  private String getProductPrice(WebElement webElement, By locator) {
    return webElement.findElement(locator).getText();
  }

  private String getProductAttribute(WebElement firstProduct, String string) {
    return firstProduct.getAttribute(string);
  }

  private WebElement getFirstWebElementInCategory(By locator) {
    return driver.findElement(locator);
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }

}