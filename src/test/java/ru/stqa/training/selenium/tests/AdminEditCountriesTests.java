package ru.stqa.training.selenium.tests;

import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created by irinagavrilova on 3/14/17.
 */
public class AdminEditCountriesTests extends TestBase {

  @Test
  public void testAdminEditCountries() {
    app.goTo().gotoCountriesPages();
    app.edit().addNewCountries();
    ArrayList<String> list = app.edit().links();
    app.edit().assertTargetBlank();
    for (int i = 0; i < list.size(); i++) {
      app.edit().handleWindow(i);
    }
  }
}