package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by irinagavrilova on 1/28/17.
 */
public class HelperBase {
  public ChromeDriver driver;

  public HelperBase(ChromeDriver driver) {

    this.driver = driver;
  }

  protected void click(By locator) {

    driver.findElement(locator).click();

  }

  protected void type(By locator, String text) {
    click(locator);
    driver.findElement(locator).clear();
    driver.findElement(locator).sendKeys(text);
  }

  protected void set(By locator, String quantity) {
    click(locator);
    driver.findElement(locator).sendKeys(quantity);
  }

  public boolean isElementPresent(By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
}
