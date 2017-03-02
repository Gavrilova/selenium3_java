package ru.stqa.training.selenium.tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created by irinagavrilova on 3/1/17.
 */
public class CartTests extends TestBase {

  @Test
  public void testCart() {
    app.goTo().gotoPage(By.cssSelector("i.fa-chevron-circle-left"));
    app.cart().popular();



  }
}
