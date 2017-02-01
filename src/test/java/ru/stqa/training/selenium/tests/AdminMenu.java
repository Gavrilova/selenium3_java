package ru.stqa.training.selenium.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by irinagavrilova on 1/30/17.
 */
public class AdminMenu extends TestBase {
  @Test
  public void testAdminMenu() {

    int commandMenuSize = app.getCategoryHelper().quantityElementsPresent(By.cssSelector("li#app-"));
    String[] commandText = app.getCategoryHelper().list(By.cssSelector("li#app-"), commandMenuSize);
    for (int j = 0; j < commandMenuSize; j++) {
      app.getNavigationHelper().gotoPage(By.linkText(commandText[j]));
      assertTrue(app.getNavigationHelper().isElementPresent(By.cssSelector("h1")));
      int k = app.getCategoryHelper().quantityElementsPresent(By.cssSelector("li#app-.selected span.name")) - 1;
      if (k > 0) {
        String[] commandSubText = app.getCategoryHelper().list(By.cssSelector("li#app-.selected ul.docs span.name"), k);
        for (int m = 0; m < k; m++) {
          app.getNavigationHelper().gotoPage(By.linkText(commandSubText[m]));
          assertTrue(app.getNavigationHelper().isElementPresent(By.cssSelector("h1")));
        }
      }
    }
    app.getNavigationHelper().gotoHomePage();
    app.getNavigationHelper().logoutAdminSession();
  }
}
