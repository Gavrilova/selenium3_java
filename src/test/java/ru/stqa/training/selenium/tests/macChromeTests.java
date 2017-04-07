package ru.stqa.training.selenium.tests;

import org.junit.Assert;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

/**
 * Created by irinagavrilova on 4/7/17.
 */
public class macChromeTests {
  //https://bugs.chromium.org/p/chromedriver/issues/detail?id=985
  //List of Chromium Command Line Switches:
  //http://peter.sh/experiments/chromium-command-line-switches/

@Test
  public void driverChrome1() {
    ChromeOptions options = new ChromeOptions();
    options.addArguments("start-maximized");
    ChromeDriver driver = new ChromeDriver(options);
    driver.get("https://www.yandex.ru/");
    driver.close();
  }
  @Test
  public void driverChrome2() {

    ChromeOptions options = new ChromeOptions();
    options.addArguments("start-fullscreen");
    ChromeDriver driver = new ChromeDriver(options);
    driver.get("https://www.yandex.ru/");
    driver.close();
  }
  @Test
  public void driverChrome3() {

    ChromeOptions options = new ChromeOptions();
    options.addArguments("kiosk");
    ChromeDriver driver = new ChromeDriver(options);
    driver.get("https://www.yandex.ru/");
    driver.close();
  }
}
