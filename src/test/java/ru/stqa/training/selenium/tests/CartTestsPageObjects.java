package ru.stqa.training.selenium.tests;

import org.testng.annotations.Test;

/**
 * Created by irinagavrilova on 4/20/17.
 */
public class CartTestsPageObjects extends TestBase {
  @Test
  public void testCart() throws InterruptedException {
    for (int i = 0; i < 3; i++) {
      app.customersPage().gotoCustomersHomePage();
      app.customersPage().getFirstOne();
      app.productPage().addToCart();
    }
    app.productPage().checkOut();
    app.cartPage().emptyCart();
  }
}


