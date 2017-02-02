package ru.stqa.training.selenium.tests;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by irinagavrilova on 1/24/17.
 */

public class MyFirstTest {

  private WebDriver driver;
  private WebDriverWait wait;

  @Before
  public void start() {
    DesiredCapabilities caps = new DesiredCapabilities();
    caps.setCapability(FirefoxDriver.MARIONETTE, false);
    driver = new FirefoxDriver(caps);
    System.out.println(((HasCapabilities) driver).getCapabilities());
    wait = new WebDriverWait(driver, 10);
  }

  @Test
  public void myFirstTest() {
    driver.get("http://www.google.com");
//  driver.findElement(By.name("q")).sendKeys("webdriver");
    wait.until((WebDriver d) -> d.findElement(By.name("q"))).sendKeys("webdriver");
    driver.findElement(By.name("btnG")).click();
    wait.until(titleIs("webdriver - Google Search"));
  }

  @After
  public void stop() {
    driver.quit();
    driver = null;
  }

}