package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import static java.lang.Integer.valueOf;

/**
 * Created by irinagavrilova on 4/21/17.
 */
public class ProductPageHelper extends HelperBase {

  public ProductPageHelper(WebDriver driver) {
    super(driver);
  }

  WebDriverWait wait = new WebDriverWait(driver, 10);

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


  private String selectOptions() {
    Random rnd = new Random();
    List<String> list = driver.findElements(By.cssSelector("select option"))
            .stream().map((d) -> d.getAttribute("value"))
            .collect(Collectors.toList());
    int index = 1 + rnd.nextInt(list.size() - 1);
    return list.get(index);
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

  private int cartQuantity() {
    return valueOf(driver.findElement(By.cssSelector("span.quantity")).getText());
  }
}
