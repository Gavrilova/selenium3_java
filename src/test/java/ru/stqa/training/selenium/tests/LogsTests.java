package ru.stqa.training.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.logging.Level;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by irinagavrilova on 4/10/17.
 */

public class LogsTests {
  private WebDriver driver;
  private WebDriverWait wait;

  @BeforeClass
  public void start() {
    DesiredCapabilities cap = DesiredCapabilities.chrome();
    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
    cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
    driver = new EventFiringWebDriver(new ChromeDriver(cap));
    wait = new WebDriverWait(driver, 10);
  }

  @Test
  public void getBrowserLogs() {
    driver.get("https://selenium2.ru/");
    driver.navigate().to("https://selenium2.ru/");
    System.out.println(driver.manage().logs().getAvailableLogTypes());
    driver.manage().logs().get("browser").forEach(System.out::println);
    driver.manage().logs().get("performance").forEach(System.out::println);
  }

  @Test(enabled = false)
  public void myFirstTest() {
    driver.navigate().to("http://www.google.com");
    wait.until((WebDriver d) -> d.findElement(By.name("q"))).sendKeys("webdriver");
    driver.findElement(By.name("btnG")).click();
    wait.until(titleIs("webdriver - Google Search"));
    driver.manage().logs().get("browser").forEach(System.out::println);
    driver.manage().logs().get("performance").forEach(System.out::println);
  }

  @AfterClass
  public void stop() {
    driver.quit();
    driver = null;
  }
}

