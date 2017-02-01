package ru.stqa.training.selenium.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
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
    for (WebElement element: productSet) {
      System.out.println(element.getText());

      if (element.findElements(By.cssSelector("div.sticker")).size()>0) {
        int k = element.findElements(By.cssSelector("div.sticker")).size();
        //int k = (app.getCategoryHelper().quantityElementsPresent(By.cssSelector("div.sticker")));
        System.out.println("Quantity of stickers = " + k);
        assertEquals((element.findElements(By.cssSelector("li.product div.sticker")).size() == 1), true);
      } else {
        System.out.println(element.getText() + " has no sticker");
      }

 //     int k = element.findElements(By.cssSelector("div.sticker")).size();
 //     //int k = (app.getCategoryHelper().quantityElementsPresent(By.cssSelector("div.sticker")));
 //     System.out.println("Quantity of stickers = " + k);
 //     if (k==0) {
 //       System.out.println(element.getText() + " has no sticker");
 //     } else {
 //       assertEquals((element.findElements(By.cssSelector("li.product div.sticker")).size() ==  1), true);
 //     }

    }
  }
}
