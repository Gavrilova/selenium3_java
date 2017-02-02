package ru.stqa.training.selenium.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

/**
 * Created by irinagavrilova on 2/1/17.
 */
public class Stickers {
  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start() {

    driver = new ChromeDriver();
    wait = new WebDriverWait(driver, 10);
  }

  @Test
  public void stickersTest() {
    driver.get("http://localhost/litecart/en/");
    List<WebElement> productSet = driver.findElements(By.cssSelector("li.product"));
    for (WebElement element : productSet) {
      Assert.assertEquals((element.findElements(By.cssSelector("div.sticker")).size()) == 1, true);
    }
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }

}
