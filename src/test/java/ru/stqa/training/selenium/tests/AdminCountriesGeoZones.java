package ru.stqa.training.selenium.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created by irinagavrilova on 2/26/17.
 */
public class AdminCountriesGeoZones extends TestBase {

  @Test
  public void AdminCountriesGeoZones() throws InterruptedException {
    app.getNavigationHelper().gotoPage(By.linkText("Countries"));
    app.getCountriesHelper().countriesCollection();
    ArrayList<String> zones = app.getCountriesHelper().countriesZones();
    app.getCountriesHelper().checkZones(zones);

  }
}
