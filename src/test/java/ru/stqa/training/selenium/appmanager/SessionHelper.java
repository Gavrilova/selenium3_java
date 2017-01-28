package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by irinagavrilova on 1/28/17.
 */
public class SessionHelper {
  private ChromeDriver driver;

  public SessionHelper(ChromeDriver driver) {

    this.driver = driver;
  }


  public void login(String username, String password) {
    driver.get("http://admin:admin@localhost/litecart/admin/login.php");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(username);
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.name("login")).click();
  }

}
