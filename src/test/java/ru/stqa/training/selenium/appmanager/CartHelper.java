package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.lang.Integer.valueOf;
import static java.util.stream.Collectors.toCollection;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.*;
import static org.testng.Assert.assertEquals;

/**
 * Created by irinagavrilova on 3/2/17.
 */

public class CartHelper extends HelperBase {

    public CartHelper(WebDriver driver) {
      super(driver);
    }

    WebDriverWait wait = new WebDriverWait(driver, 10);


  public void firstPopular() {//find first product in Popular group
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.cssSelector("h3.title")));
    String productName = driver.findElement(By.cssSelector("div.name")).getText();
    driver.findElement(By.cssSelector("div#box-most-popular.box li")).click();
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.cssSelector("h1.title")));
    String productPageName = driver.findElement(By.cssSelector("h1.title")).getText();
    assertEquals(productName, productPageName);
    System.out.println(productName);
  }

  public void chooseRandom() { //choose randomly the product from the list
    Random rnd = new Random();
    List<WebElement> products = driver.findElements(By.cssSelector("li.product"));
    int index = rnd.nextInt(products.size());
    products.get(index).click();
  }

  public void getFirstOne() { //choose first product from the list in the main page
    List<WebElement> products = driver.findElements(By.cssSelector("li.product"));
    String productName = products.get(0).findElement(By.cssSelector("div.name")).getText();
    products.get(0).click();
    wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("h1.title"))));
    String productPageName = driver.findElement(By.cssSelector("h1.title")).getText();
    assertEquals(productName, productPageName);
  }

  public void addToCart() throws InterruptedException {
    int cartQuantity = cartQuantity();
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.cssSelector("td.quantity button")));
    if (isElementPresent(By.cssSelector("select"))) {
      new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElements(By.cssSelector("select option")).get(1));
      new Select(driver.findElement(By.cssSelector("select"))).selectByValue(selectOptions());
    }
    driver.findElement(By.cssSelector("td.quantity button")).click();
    wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(cartQuantity + 1)));
  }

  private int cartQuantity() {
    return valueOf(driver.findElement(By.cssSelector("span.quantity")).getText());
  }

  private String selectOptions() {
    Random rnd = new Random();
    List<String> list = driver.findElements(By.cssSelector("select option"))
            .stream().map((d) -> d.getAttribute("value"))
            .collect(Collectors.toList());
    int index = 1 + rnd.nextInt(list.size() - 1);
    return list.get(index);
  }

  public void yellowDuck() {
    driver.get("http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");
  }

  public void checkOut() {
    int quantity = cartQuantity();
    driver.findElement(By.cssSelector("div#cart a.link")).click();
    if (quantity != 0) {
      new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.cssSelector("table.dataTable")));
    } else {
      new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.cssSelector("em")));
    }
  }

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

