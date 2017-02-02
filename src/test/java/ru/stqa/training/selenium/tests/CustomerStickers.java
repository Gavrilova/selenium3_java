package ru.stqa.training.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by irinagavrilova on 1/31/17.
 */
public class CustomerStickers extends TestBase {

  @Test
  public void testCustomerStickers() {

    app.getNavigationHelper().gotoPage(By.cssSelector("i.fa-chevron-circle-left"));
    List<WebElement> productSet = app.getCategoryHelper().listElementsPresent(By.cssSelector("li.product"));
    for (WebElement element : productSet) {
      assertEquals((element.findElements(By.cssSelector("div.sticker")).size()) == 1, true);
    }
  }
}


