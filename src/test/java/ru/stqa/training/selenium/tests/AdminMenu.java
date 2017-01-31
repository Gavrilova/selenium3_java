package ru.stqa.training.selenium.tests;

import org.testng.annotations.Test;

/**
 * Created by irinagavrilova on 1/30/17.
 */
public class AdminMenu extends TestBase {
  @Test
  public void testAdminMenu() {

    app.getNavigationHelper().gotoCatalogPage();

    System.out.println();
    app.getNavigationHelper().gotoManufacturesPage();
    app.getNavigationHelper().logoutAdminSession();
  }
}
