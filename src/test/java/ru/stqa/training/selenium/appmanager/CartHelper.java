package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;

import static java.lang.Integer.valueOf;
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

      if (isElementPresent(By.cssSelector("ul.shortcuts"))) {
        // WebElement demo = driver.findElement(By.cssSelector("ul.shortcuts"));
        List<WebElement> elements = driver.findElements(By.cssSelector("li.shortcut a"));
        //WebElement cart = driver.findElementByCssSelector("table.dataTable");
        WebElement elementFirst = elements.get(0);
        elementFirst.click();
        //System.out.println(elementFirst.getAttribute("class"));
        wait.until(ExpectedConditions.attributeContains(elementFirst, "class", "inact act"));
      }

      driver.findElement(By.name("remove_cart_item")).click();
      if (quantity() > 0) {
        wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("form img")))); //element is moving, not vanished!
      }

      assertEquals(before - i - 1, quantity()); //assertion that one element less in the cart
      //assertion that exactly element was deleted

    }
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.cssSelector("em")));
    assertTrue(isElementPresent(By.cssSelector("em")));
  }


  private int quantity() {
    return driver.findElements(By.cssSelector("td.sku")).size();
  }
}

