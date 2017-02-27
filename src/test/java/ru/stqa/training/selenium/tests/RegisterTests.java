package ru.stqa.training.selenium.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;
import ru.stqa.training.selenium.model.RegisterData;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by irinagavrilova on 2/27/17.
 */
public class RegisterTests extends TestBase {
  @Test
  public void RegisterTests() throws InterruptedException {

    app.getNavigationHelper().gotoRegisterPage();
    String pass = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    String name = "Irina" + pass;
    String email = "gavrilova.irina."+pass+ "@gmail.com";
    System.out.println(pass);
    app.getRegisterHelper().fillForm(name, email, pass);
    app.getNavigationHelper().logoutCustomersSession();
    app.getRegisterHelper().customerLogin(email, pass);
    app.getNavigationHelper().logoutCustomersSession();
  }

}
