package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * Created by irinagavrilova on 1/28/17.
 */
public class HelperBase {
  public WebDriver driver;

  public HelperBase(WebDriver driver) {

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

  protected void attach(By locator, File file) {
    if (file != null && file.isFile()) {
      driver.findElement(locator).sendKeys(file.getAbsolutePath());
    }
  }

  public boolean isElementPresent(By locator) {
    try {
      driver.findElement(locator);
      return true;
    } catch (NoSuchElementException ex) {
      return false;
    }
  }
  //Store variable of implicit waiting and change it's value from time to time; driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

  public boolean isElementPresent(WebDriver driver, By locator) { //проверка наличия (с ожиданием)
    try {
      driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      return driver.findElements(locator).size() > 0;
    } finally {
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
  }

  public boolean isElemPresent(WebDriver driver, By locator) {

    return driver.findElements(locator).size() > 0;
  }

  public boolean isElementNotPresent(WebDriver driver, By locator) {
    try {
      driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
      return driver.findElements(locator).size() == 0;
    } finally {
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }
  }

}






















