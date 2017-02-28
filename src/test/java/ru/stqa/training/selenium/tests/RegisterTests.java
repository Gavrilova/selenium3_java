package ru.stqa.training.selenium.tests;

import org.testng.annotations.Test;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by irinagavrilova on 2/27/17.
 */
public class RegisterTests extends TestBase {
  @Test
  public void RegisterTests() throws InterruptedException {

    app.getNavigationHelper().gotoRegisterPage();
    String password = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    String name = "Irina" + password;
    String email = "gavrilova.irina." + password + "@gmail.com";
    app.getRegisterHelper().fillForm(name, email, password);
    app.getNavigationHelper().logoutCustomersSession();
    app.getRegisterHelper().customerLogin(email, password);
    app.getNavigationHelper().logoutCustomersSession();
  }

}
