package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by irinagavrilova on 2/26/17.
 */
public class CountriesHelper extends HelperBase {
  public CountriesHelper(WebDriver driver) {
    super(driver);
  }

  public void countriesCollection() {
    ArrayList<String> countries = new ArrayList<>();
    // System.out.println(driver.findElements(By.cssSelector("tr.row")).size());
    for (int i = 2; i < (driver.findElements(By.cssSelector("tr.row")).size() + 2); i++) {
      String name = driver.findElement(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[" + (i) + "]/td[5]/a")).getText();
      countries.add(name);
    }
    print(countries);
    ArrayList<String> countriesSorted = createCollectionSorted(countries);
    checkCollectionSorted(countries, countriesSorted);

  }

  private void checkCollectionSorted(ArrayList<String> countries, ArrayList<String> countriesSorted) {
    for (int i = 0; i < countries.size(); i++) {
      String c1 = countries.get(i);
      String c2 = countriesSorted.get(i);
      assertEquals(c1, c2);
    }
  }

  private ArrayList<String> createCollectionSorted(ArrayList<String> countries) {
    ArrayList<String> countriesSorted = countries;
    Collections.sort(countriesSorted, String.CASE_INSENSITIVE_ORDER);
    //print(countriesSorted);
    return countriesSorted;
  }

  private void print(ArrayList<String> countries) {
    for (int i = 0; i < countries.size(); i++)
      System.out.println(countries.get(i));
  }


  public ArrayList<String> countriesZones() {
    ArrayList<String> zones = new ArrayList<>();
    List<Integer> countriesZones = new ArrayList<Integer>();

    for (int i = 2; i < (driver.findElements(By.cssSelector("tr.row")).size() + 2); i++) {
      String name = driver.findElement(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[" + i + "]/td[6]")).getText();
      if (Integer.valueOf(name) > 0) {
        countriesZones.add(i);
        String href = driver.findElement(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[" + i + "]/td[7]/a")).getAttribute("href");
        // System.out.println(href);
        zones.add(href);
        // System.out.println(i + " " + Integer.valueOf(name));
      }
    }
    return zones;
  }

  public void checkZones(ArrayList<String> zones) throws InterruptedException {
    for (int i = 0; i < zones.size(); i++) {
      driver.get(zones.get(i));
      new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.cssSelector("table#table-zones.dataTable"))); //wait till table-zones will be present
      ArrayList<String> geoZones = new ArrayList<>();
      System.out.println(driver.findElements(By.cssSelector("i.fa.fa-times-circle.fa-lg")).size());
      for (int j = 2; j < (driver.findElements(By.cssSelector("i.fa.fa-times-circle.fa-lg")).size() + 2); j++) {
        String name = driver.findElement(By.xpath("//*[@id=\"table-zones\"]/tbody/tr[" + j + "]/td[3]")).getText();
        geoZones.add(name);
      }
      // print(geoZones);
      ArrayList<String> geoZonesSorted = createCollectionSorted(geoZones);
      checkCollectionSorted(geoZones, geoZonesSorted);
    }
  }

  public ArrayList<String> geoZones() {
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.cssSelector("table.dataTable")));
    ArrayList<String> zones = new ArrayList<>();
    for (int i = 2; i < (driver.findElements(By.cssSelector("tr.row")).size() + 2); i++) {
      String href = driver.findElement(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[" + i + "]/td[5]/a")).getAttribute("href");
      //  System.out.println(href);
      zones.add(href);
    }
    return zones;
  }

  public void checkGeoZones(ArrayList<String> countries) {
    for (int i = 0; i < countries.size(); i++) {
      driver.get(countries.get(i));
      new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.cssSelector("table#table-zones.dataTable"))); //wait till table-zones will be present
      ArrayList<String> geoZones = new ArrayList<>();
      System.out.println(driver.findElements(By.cssSelector("i.fa.fa-times-circle.fa-lg")).size());
      for (int j = 2; j < (driver.findElements(By.cssSelector("i.fa.fa-times-circle.fa-lg")).size() + 2); j++) {

        Select sel = new Select(driver.findElement(By.xpath("//*[@id=\"table-zones\"]/tbody/tr[" + j + "]/td[3]/select")));
        String name = sel.getFirstSelectedOption().getText();


        //System.out.println(name);
        geoZones.add(name);
      }
      //  print(geoZones);
      ArrayList<String> geoZonesSorted = createCollectionSorted(geoZones);
      checkCollectionSorted(geoZones, geoZonesSorted);
    }
  }
}
