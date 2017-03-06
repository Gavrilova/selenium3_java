package ru.stqa.training.selenium.tests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by irinagavrilova on 3/1/17.
 */
public class CartTests extends TestBase {

  @Test
  public void testCart1() throws InterruptedException {
    app.goTo().gotoPage(By.cssSelector("i.fa-chevron-circle-left"));
    app.cart().firstPopular();
    app.cart().addToCart();
  }

  @Test
  public void testCart2() throws InterruptedException {
    app.goTo().gotoPage(By.cssSelector("i.fa-chevron-circle-left"));
    app.cart().yellowDuck();
    app.cart().addToCart();
  }
}
