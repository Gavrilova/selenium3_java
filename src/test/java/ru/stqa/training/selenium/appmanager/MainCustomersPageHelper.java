package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by irinagavrilova on 4/21/17.
 */
public class MainCustomersPageHelper extends HelperBase {

  public MainCustomersPageHelper(WebDriver driver) {
    super(driver);
    PageFactory.initElements(driver, this);
  }

  WebDriverWait wait = new WebDriverWait(driver, 10);

  @FindBy(css = "li.product")
  List<WebElement> productList;

  @FindBy(css = "h1.title")
  WebElement title;

  public void gotoCustomersHomePage() {
    driver.get("http://localhost/litecart/en/");
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("li.product")));
  }


  public void getFirstOne() { //choose first product from the list in the main customer's page
    List<WebElement> products = productList;
    String productName = products.get(0).findElement(By.cssSelector("div.name")).getText();
    products.get(0).click();
    wait.until(ExpectedConditions.visibilityOf(title));
    String productPageName = title.getText();
    assertEquals(productName, productPageName);
  }

}
