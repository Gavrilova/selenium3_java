package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.HashSet;
import java.util.List;
import java.util.Random;

import static java.lang.Integer.valueOf;
import static java.util.stream.Collectors.toCollection;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

/**
 * Created by irinagavrilova on 3/2/17.
 */

public class CartHelper extends HelperBase {

  public CartHelper(ChromeDriver driver) {
    super(driver);
  }

  WebDriverWait wait = new WebDriverWait(driver, 10);


  public void firstPopular() {//find first product in Popular group
    // new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.name("h3.title")));
    String productName = driver.findElement(By.cssSelector("div.name")).getText();
    driver.findElement(By.cssSelector("div#box-most-popular.box li")).click();
    // new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.name("h1.title")));
    String productPageName = driver.findElement(By.cssSelector("h1.title")).getText();
    assertEquals(productName, productPageName);
    System.out.println(productName);
  }

  public void chooseRandom() { //choose randomly the product from the list
    Random rand = new Random();
    List<WebElement> products = driver.findElements(By.cssSelector("li.product"));
    int index = rand.nextInt(products.size());
    products.get(index).click();
  }

  public void getFirstOne() { //choose first product from the list in the main page
    List<WebElement> products = driver.findElements(By.cssSelector("li.product"));
    products.get(0).click();
  }


  public void addToCart() throws InterruptedException {
    int cartQuantity = cartQuantity();
//    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.name("td.quantity button")));
    if (isElementPresent(By.cssSelector("select"))) {
      new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElements(By.cssSelector("select option")).get(1));
      for (WebElement element : driver.findElements(By.cssSelector("select option"))) {
        System.out.println(element.getText());
      }
      new Select(driver.findElement(By.cssSelector("select"))).selectByValue("Small");
    }
    driver.findElement(By.cssSelector("td.quantity button")).click();
    wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(cartQuantity + 1)));
  }

  private int cartQuantity() {
    return valueOf(driver.findElement(By.cssSelector("span.quantity")).getText());
  }

  public void yellowDuck() {
    driver.get("http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");
  }

  public void checkOut() {
    driver.findElement(By.cssSelector("div#cart a.link")).click();
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.cssSelector("table.dataTable")));
  }

  public void emptyCart() {

    int before = quantity();
    System.out.println(before);

    for (int i = 0; i < before; i++) {
      HashSet<String> beforeSKU = getSKUSet();
      if (driver.findElements(By.cssSelector("li.shortcut")).size() != 0) {
        List<WebElement> elements = driver.findElements(By.cssSelector("li.shortcut a"));
        WebElement elementFirst = elements.get(0);
        elementFirst.click();
        wait.until(ExpectedConditions.attributeContains(elementFirst, "class", "inact act"));
      }
      String text = getStringSKU();
      driver.findElement(By.name("remove_cart_item")).click();
      if (quantity() > 0) {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("form img")))); //element is moving, not vanished!
      } else {
        isElementPresent(driver, By.cssSelector("em"));
      }

      assertEquals(before - i - 1, quantity()); //assertion that one element less in the cart
      assertEquals(text, after(beforeSKU)); //assertion that exactl element was deleted
    }
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.cssSelector("em")));
    assertTrue(isElementPresent(By.cssSelector("em")));
  }

  private String after(HashSet<String> beforeSKU) {
    HashSet<String> afterSKU = getSKUSet();
    System.out.println("before: " + beforeSKU);
    System.out.println("after: " + afterSKU);
    beforeSKU.removeAll(afterSKU);
    System.out.println(beforeSKU.iterator().next());
    return beforeSKU.iterator().next();
  }

  private String getStringSKU() {
    String stringSku = driver.findElements(By.cssSelector("div p span")).get(0).getText();
    System.out.println(stringSku);
    String text = stringSku.replace("[SKU: ", "").replace("]", "");
    System.out.println(text);
    return text;
  }

  private HashSet<String> getSKUSet() {
    return driver.findElements(By.cssSelector("td.sku")).stream()
            .map(WebElement::getText).collect(toCollection(HashSet<String>::new));
  }

  private int quantity() {
    return driver.findElements(By.cssSelector("td.sku")).size();
  }
}

