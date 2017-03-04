package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * Created by irinagavrilova on 3/2/17.
 */
public class CartHelper extends HelperBase {

  public CartHelper(ChromeDriver driver) {
    super(driver);
  }

  public void popular() {
    driver.findElement(By.cssSelector("div#box-most-popular.box li")).click();
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.name("default_category_id")));

  }
}
