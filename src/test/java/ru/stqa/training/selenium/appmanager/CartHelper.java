package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by irinagavrilova on 3/2/17.
 */
public class CartHelper extends HelperBase{

  public CartHelper(ChromeDriver driver) {
    super(driver);
  }

  public void popular() {
    driver.findElements(By.cssSelector("div#box-most-popular.box li"));
  }
}
