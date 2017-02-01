package ru.stqa.training.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertTrue;

/**
 * Created by irinagavrilova on 1/30/17.
 */
public class AdminMenu extends TestBase {
  @Test
  public void testAdminMenu() {

    int commandMenuSize = app.getCategoryHelper().quantityElementsPresent(By.cssSelector("li#app-"));
    System.out.println("commandMenuSize = " + commandMenuSize);


    String[] commandText = app.getCategoryHelper().list(By.cssSelector("li#app-"), commandMenuSize);

    for (int j = 0; j< commandMenuSize; j++) {
      String srt = commandText[j];
      app.getNavigationHelper().gotoPage(By.linkText(srt));
      assertTrue(app.getNavigationHelper().isElementPresent(By.cssSelector("h1")));
     int k = app.getCategoryHelper().quantityElementsPresent(By.cssSelector("li#app-.selected span.name"));
      if (k > 1) {
        String[] commandSubText = app.getCategoryHelper().list(By.cssSelector("li#app-.selected ul.docs span.name"), k-1);
        for (int m = 0; m < (k-1); m++) {
          String srtSub = commandSubText[m];
          app.getNavigationHelper().gotoPage(By.linkText(srtSub));
          assertTrue(app.getNavigationHelper().isElementPresent(By.cssSelector("h1")));

        }
      }
    }

    app.getNavigationHelper().gotoHomePage();
    app.getNavigationHelper().logoutAdminSession();
  }
}
