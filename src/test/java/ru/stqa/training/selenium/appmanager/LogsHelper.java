package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by irinagavrilova on 4/20/17.
 */
public class LogsHelper extends HelperBase {

    public LogsHelper(WebDriver driver) {
      super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, 10);

//performance, browser, driver, client

public void getBrowserLogs() {
  System.out.println(driver.manage().logs().getAvailableLogTypes());
  driver.manage().logs().get("browser").forEach(System.out::println);
}
  public void getPerformanceLogs() {
    System.out.println(driver.manage().logs().getAvailableLogTypes());
    driver.manage().logs().get("performance").forEach(System.out::println);
  }
  public void getDriverLogs() {
    System.out.println(driver.manage().logs().getAvailableLogTypes());
    driver.manage().logs().get("driver").forEach(System.out::println);
  }
  public void getClientLogs() {
    System.out.println(driver.manage().logs().getAvailableLogTypes());
    driver.manage().logs().get("client").forEach(System.out::println);
  }
  public void getLogs() {
    System.out.println(driver.manage().logs().getAvailableLogTypes());
    driver.manage().logs().get("browser").forEach(System.out::println);
    driver.manage().logs().get("performance").forEach(System.out::println);
    driver.manage().logs().get("client").forEach(System.out::println);
    driver.manage().logs().get("driver").forEach(System.out::println);
  }


}
