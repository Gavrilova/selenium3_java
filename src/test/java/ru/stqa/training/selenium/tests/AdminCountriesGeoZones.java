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
    app.goTo().gotoPage(By.linkText("Countries"));
    app.countriesPage().countriesCollection();
    ArrayList<String> zones = app.countriesPage().countriesZones();
    app.countriesPage().checkZones(zones);
    app.goTo().gotoHomePage();
    app.goTo().gotoPage(By.linkText("Geo Zones"));
    ArrayList<String> countries = app.countriesPage().geoZones();
    app.countriesPage().checkGeoZones(countries);
  }
}
