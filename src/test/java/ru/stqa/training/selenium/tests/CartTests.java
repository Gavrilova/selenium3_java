package ru.stqa.training.selenium.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

/**
 * Created by irinagavrilova on 3/1/17.
 */
public class CartTests extends TestBase {

  @Test
  public void testCart1() throws InterruptedException {
    for (int i = 0; i < 3; i++) {
      app.goTo().gotoCustomersHomePage();
      app.cart().getFirstOne();
      app.cart().addToCart();
    }
    app.cart().checkOut();
    app.cart().emptyCart();
  }

  @Test
  public void testCart2() throws InterruptedException {
    app.goTo().gotoPage(By.cssSelector("i.fa-chevron-circle-left"));
    app.cart().yellowDuck();
    app.cart().addToCart();
  }

  @Test
  public void testCart3() throws InterruptedException {
    app.goTo().gotoPage(By.cssSelector("i.fa-chevron-circle-left"));
    app.cart().chooseRandom();
    app.cart().addToCart();

  }

  @Test
  public void testGetCartEmpty() throws InterruptedException {
    app.cart().checkOut();
    app.cart().emptyCart();
  }

}
