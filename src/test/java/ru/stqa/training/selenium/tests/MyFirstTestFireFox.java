package ru.stqa.training.selenium.tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;


/**
 * Created by irinagavrilova on 2/2/17.
 */

public class MyFirstTestFireFox {

  public static final String campaignSelector = "strong.campaign-price";
  public static final String regularSelector = "s.regular-price";
  private WebDriver driver;

  @Before
  public void start() {
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability(FirefoxDriver.MARIONETTE, false);
    driver = new FirefoxDriver(caps);
    System.out.println(((HasCapabilities) driver).getCapabilities());
  }

  @Test
  public void myFirstTest() throws InterruptedException {
    driver.get("http://localhost/litecart/en/");

    WebElement firstProduct
            = getFirstWebElementInCategory(By.cssSelector("div#box-campaigns li.product a.link"));

    String nameMainPage = getProductAttribute(firstProduct, "title");
    String priceCampaignsMainPage = getProductPrice(firstProduct, By.cssSelector(campaignSelector));
    String priceRegularMainPage = getProductPrice(firstProduct, By.cssSelector(regularSelector));
    String colorCampaigh = getValue(firstProduct, By.cssSelector(campaignSelector), "color");
    String fontCampaigh = getValue(firstProduct, By.cssSelector(campaignSelector), "font-weight");
    String colorRegular = getValue(firstProduct, By.cssSelector(regularSelector), "color");
    String textDecorationRegular = getValue(firstProduct, By.cssSelector(regularSelector), "text-decoration");

    attributeCheck(colorCampaigh, fontCampaigh, colorRegular, textDecorationRegular);
    dimentionCheck(firstProduct);

    firstProduct.click();  //go to Product page
    new WebDriverWait(driver, 5).until((WebDriver dr) -> dr.findElement(By.cssSelector("h1.title"))); //wait till h1.title will be present
    assertEquals(driver.findElement(By.cssSelector("h1.title")).getText(), nameMainPage); //assert the same names

    WebElement product = driver.findElement(By.cssSelector("div#box-product div.content"));
    assertEquals(getProductPrice(product, By.cssSelector(campaignSelector)), priceCampaignsMainPage); //assert the same Campaign price
    assertEquals(getProductPrice(product, By.cssSelector(regularSelector)), priceRegularMainPage); //assert the same Regular price
    String colorCampaighProduct = getValue(product, By.cssSelector(campaignSelector), "color");
    String colorRegularProduct = getValue(product, By.cssSelector(regularSelector), "color");
    String fontCampaighProduct = getValue(product, By.cssSelector(campaignSelector), "font-weight");
    String textDecorationRegularProduct = getValue(product, By.cssSelector(regularSelector), "text-decoration");
    attributeCheck(colorCampaighProduct, fontCampaighProduct, colorRegularProduct, textDecorationRegularProduct);
    dimentionCheck(product);


  }

  private void dimentionCheck(WebElement webElement) {
    double heightCampaign = getDimension(webElement, By.cssSelector(campaignSelector)).getHeight();
    double widthCampaign = getDimension(webElement, By.cssSelector(campaignSelector)).getWidth();
    double heightRegular = getDimension(webElement, By.cssSelector(regularSelector)).getHeight();
    double widthRegular = getDimension(webElement, By.cssSelector(regularSelector)).getWidth();
    assertEquals((getSquare(heightCampaign, widthCampaign) - getSquare(heightRegular, widthRegular) > 0), true);
  }

  private double getSquare(double height, double width) {
    return (height * height + width * width);
  }


  private void attributeCheck(
          String colorCampaigh, String fontCampaigh, String colorRegular, String textDecorationRegular) {
    //verify that Campaign price is red and bold
    //verify that Regular price is grey and line-through


    assertEquals(textDecorationRegular, "line-through");

    String[] redColorArray = {"rgb(204, 0, 0)", "#cc0000", "#c00", "rgba(204, 0, 0, 1)"};
    String[] greyColorArray = {"rgb(119, 119, 119)", "#777777", "#777", "#666", "#666666", "rgb(102, 102, 102)", "rgba(119, 119, 119, 1)",
            "rgba(102, 102, 102, 1)"};
    String[] fontCampaignArray = {"bold", "900", "700"};

    assertEquals(getAssert(fontCampaignArray, fontCampaigh), true);
    assertEquals(getAssert(redColorArray, colorCampaigh), true);
    assertEquals(getAssert(greyColorArray, colorRegular), true);
  }

  private boolean getAssert(String[] array, String color) {
    Boolean bool = false;
    for (int i = 0; i < array.length; i++) {
      if (color.equals(array[i]))
        bool = true;
    }
    return bool;
  }

  private String getValue(WebElement firstProduct, By locator, String string) {
    return firstProduct.findElement(locator).getCssValue(string);
  }


  private Dimension getDimension(WebElement webElement, By locator) {
    return webElement.findElement(locator).getSize();
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