package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.List;

import static java.util.stream.Collectors.toCollection;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by irinagavrilova on 4/21/17.
 */
public class CartPageHelper extends HelperBase {
  public CartPageHelper(WebDriver driver) {
    super(driver);
  }

  WebDriverWait wait = new WebDriverWait(driver, 10);


  public void emptyCart() {
    int before = quantity();
    for (int i = 0; i < before; i++) {
      HashSet<String> beforeSKU = getSKUSet();
      if (driver.findElements(By.cssSelector("li.shortcut")).size() != 0) {
        List<WebElement> elements = driver.findElements(By.cssSelector("li.shortcut a"));
        WebElement elementFirst = elements.get(0);
        elementFirst.click();
        wait.until(attributeContains(elementFirst, "class", "inact act"));
      }
      String stringSKU = getStringSKU();
      driver.findElement(By.name("remove_cart_item")).click();
      if (quantity() > 0) {
        wait.until(stalenessOf(driver.findElement(By.cssSelector("form img")))); //element is moving, not vanished!
      } else {
        wait.until(visibilityOf(driver.findElement(By.cssSelector("em"))));
      }
      assertEquals(before - i - 1, quantity());      //assertion that one element less in the cart
      assertEquals(stringSKU, after(beforeSKU));     //assertion that exact element was deleted
    }
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.cssSelector("em")));
    assertTrue(isElementPresent(By.cssSelector("em")));
  }

  private String after(HashSet<String> beforeSKU) {
    HashSet<String> afterSKU = getSKUSet();
    beforeSKU.removeAll(afterSKU);
    return beforeSKU.iterator().next();
  }

  private String getStringSKU() {
    String stringSku = driver.findElements(By.cssSelector("div p span")).get(0).getText();
    return stringSku.replace("[SKU: ", "").replace("]", "");
  }

  private HashSet<String> getSKUSet() {
    return driver.findElements(By.cssSelector("td.sku")).stream()
            .map(WebElement::getText).collect(toCollection(HashSet<String>::new));
  }

  private int quantity() {
    return driver.findElements(By.cssSelector("td.sku")).size();
  }

}
