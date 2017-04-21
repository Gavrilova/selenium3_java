package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
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
    PageFactory.initElements(driver, this);
  }

  WebDriverWait wait = new WebDriverWait(driver, 10);

  @FindBy(css = "span.quantity")
  WebElement cartQuantity;

  @FindBy(css = "select option")
  List<WebElement> options;

  @FindBy(css = "td.quantity button")
  WebElement addToCartButton;

  @FindBy(css = "select")
  WebElement selectElement;

  @FindBy(css = "select")
  List<WebElement> selectList;

  @FindBy(css = "div#cart a.link")
  WebElement checkOut;

  @FindBy(css = "table.dataTable")
  WebElement form;

  @FindBy(css = "em")
  WebElement emptyMessage;

  public void addToCart() throws InterruptedException {
    int cartQuantity = cartQuantity();
    new WebDriverWait(driver, 20).until((WebDriver dr) -> addToCartButton);
    if (isElementPresent(By.cssSelector("select"))) {
      new WebDriverWait(driver, 20).until((WebDriver dr) -> options.get(1));
      new Select(selectElement).selectByValue(selectOptions());
    }
    addToCartButton.click();
    wait.until(ExpectedConditions.textToBe(By.cssSelector("span.quantity"), String.valueOf(cartQuantity + 1)));
  }


  private String selectOptions() {
    Random rnd = new Random();
    List<String> list = options
            .stream().map((d) -> d.getAttribute("value"))
            .collect(Collectors.toList());
    int index = 1 + rnd.nextInt(list.size() - 1);
    return list.get(index);
  }

  public void checkOut() {
    int quantity = cartQuantity();
    checkOut.click();
    if (quantity != 0) {
      new WebDriverWait(driver, 20).until((WebDriver dr) -> checkOut);
    } else {
      new WebDriverWait(driver, 20).until((WebDriver dr) -> emptyMessage);
    }
  }


  private int cartQuantity() {
    return valueOf(cartQuantity.getText());
  }

}
