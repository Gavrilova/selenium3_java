package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.lang.Integer.valueOf;
import static org.testng.Assert.assertEquals;

/**
 * Created by irinagavrilova on 3/2/17.
 */

public class CartHelper extends HelperBase {

  public CartHelper(ChromeDriver driver) {
    super(driver);
  }

  public void firstPopular() {//find first product in Popular group
    // new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.name("h3.title")));
    String productName = driver.findElement(By.cssSelector("div.name")).getText();
    driver.findElement(By.cssSelector("div#box-most-popular.box li")).click();
    // new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.name("h1.title")));
    String productPageName = driver.findElement(By.cssSelector("h1.title")).getText();
    assertEquals(productName, productPageName);
    System.out.println(productName);
  }

  public void addToCart() throws InterruptedException {
    int cartQuantity = cartQuantity();
//    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.name("td.quantity button")));
    if (isElementPresent(By.cssSelector("select")))
    //driver.findElements(By.cssSelector("select.options[Size]")).size() > 0)
    {
      new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElements(By.cssSelector("select option")).get(1));
      // String selectedValue = driver.findElement(By.cssSelector("select.options[Size].option")).getAttribute("value");
      // System.out.println(selectedValue);
      for (WebElement element : driver.findElements(By.cssSelector("select option"))) {
        System.out.println(element.getText());
      }
      new Select(driver.findElement(By.cssSelector("select"))).selectByValue("Small");
    }
    //new WebDriverWait(driver, 5000);
    driver.findElement(By.cssSelector("td.quantity button")).click();
    //елемент изменяется! нужно проверка на изменяющийся элемент
    Thread.sleep(4000);
    int cartQuantityAfter = cartQuantity();
    assertEquals(cartQuantity + 1, cartQuantityAfter);  //verify that product was added to the cart;
  }

  public int cartQuantity() {
    return valueOf(driver.findElement(By.cssSelector("span.quantity")).getText());
  }

  public void yellowDuck() {
    driver.get("http://localhost/litecart/en/rubber-ducks-c-1/subcategory-c-2/yellow-duck-p-1");
  }

}

