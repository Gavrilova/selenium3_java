package ru.stqa.training.selenium.tests;

import org.testng.annotations.Test;

/**
 * Created by irinagavrilova on 2/28/17.
 */
public class ProductCreationTests extends TestBase {

  @Test
  public void testProductCreation() {
    app.goTo().gotoCatalogPage();
    app.productPage().initAddNewProduct();
    app.productPage().fillThreeTabs();

  }
}
