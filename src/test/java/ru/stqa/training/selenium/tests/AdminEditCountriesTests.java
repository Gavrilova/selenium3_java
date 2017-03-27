package ru.stqa.training.selenium.tests;

import org.testng.annotations.Test;

/**
 * Created by irinagavrilova on 3/14/17.
 */
public class AdminEditCountriesTests extends TestBase {

  @Test
  public void testAdminEditCountries() {
    app.goTo().gotoCountriesPages();
    app.edit().addNewCountries();
    app.edit().test();
  }
}