package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by irinagavrilova on 1/28/17.
 */
public class SessionHelper extends HelperBase {

  public SessionHelper(WebDriver driver) {
    super(driver);

  }


  public void login(String username, String password) {
    driver.get("http://admin:admin@localhost/litecart/admin/login.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.name("login"));
  }


}
