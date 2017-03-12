package ru.stqa.training.selenium.tests;

import org.testng.annotations.Test;

/**
 * Created by irinagavrilova on 3/1/17.
 */
public class CartTests extends TestBase {

  @Test
  public void testCart() throws InterruptedException {
    for (int i = 0; i < 3; i++) {
      app.goTo().gotoCustomersHomePage();
      app.cart().getFirstOne();
      app.cart().addToCart();
    }
    app.cart().checkOut();
    app.cart().emptyCart();
  }
}