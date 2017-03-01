package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.model.ProductData;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.*;

/**
 * Created by irinagavrilova on 1/28/17.
 */
public class ProductHelper extends HelperBase {

  public ProductHelper(ChromeDriver driver) {
    super(driver);
  }

  public void fillProductForm(ProductData productData) {
    type(By.name("code"), productData.getGeneralCode());
    click(By.xpath("//div[@id='tab-general']/table/tbody/tr[3]/td"));
    type(By.name("name[en]"), productData.getNameEng());
    if (!driver.findElement(By.xpath("//div[@id='tab-general']/table/tbody/tr[4]/td/div/table/tbody/tr[4]/td[1]/input")).isSelected()) {
      click(By.xpath("//div[@id='tab-general']/table/tbody/tr[4]/td/div/table/tbody/tr[4]/td[1]/input"));
    }
    if (driver.findElement(By.xpath("//div[@id='tab-general']/table/tbody/tr[4]/td/div/table/tbody/tr[3]/td[1]/input")).isSelected()) {
      click(By.xpath("//div[@id='tab-general']/table/tbody/tr[4]/td/div/table/tbody/tr[3]/td[1]/input"));
    }
    if (!driver.findElement(By.name("product_groups[]")).isSelected()) {
      click(By.name("product_groups[]"));

    }

    new Actions(driver).doubleClick(driver.findElement(By.name("product_groups[]"))).build().perform();

    set(By.name("quantity"), productData.getQuantity());
    click(By.name("quantity"));
    click(By.linkText("Information"));

    type(By.name("short_description[en]"), productData.getShortDescriptionEng());
    click(By.cssSelector("div.trumbowyg-editor"));
    click(By.linkText("Data"));


    type(By.name("sku"), productData.getSKU());
    click(By.xpath("//div[@id='tab-data']/table/tbody/tr[1]/td"));
    click(By.linkText("Prices"));


    type(By.name("prices[USD]"), productData.getPriceUSD());
    click(By.xpath("//a[@id='add-campaign']/i"));

    type(By.name("campaigns[new_1][percentage]"), productData.getCompaingsPercentage());
    click(By.name("campaigns[new_1][percentage]"));
    click(By.name("save"));
  }

  public void initEditProduct() {
    click(By.xpath("//table[@class='dataTable']/tbody/tr[7]/td[5]/a/i"));
  }

  public void submitProductDuplicate() {
    click(By.name("duplicate"));
  }

  public void submitProductDeleting() {
    click(By.name("delete"));
    driver.switchTo().alert().accept();
  }
  public void initAddNewProduct(){
    click(By.xpath("//*[@id=\"content\"]/div[1]/a[2]"));
  }
  public void submitAddNewProduct(){
    click(By.cssSelector("i.fa.fa-floppy-o"));
  }
  public void SetDatepicker(String cssSelector, String date) {//C# code: new WebDriverWait(driver, SECONDS).Until<boolean>(d => driver.findElement(By.cssSelector(cssSelector)).isDisplayed();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.findElement(By.cssSelector(cssSelector)).sendKeys(date);
    driver.findElement(By.cssSelector("body")).click();
  }

  public void fillGeneralTab(){
    click(By.xpath("//*[@id=\"content\"]/form/div/ul/li[1]/a"));
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.name("default_category_id")));
    if (!driver.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[1]/td/label[1]/input")).isEnabled()){
      click(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[1]/td/label[1]/input"));








    }
  }
  public void fillInformationTab(){
    click(By.xpath("//*[@id=\"content\"]/form/div/ul/li[2]/a")); //gotoInformationTab
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.name("manufacturer_id")));




  }

  public void fillPricesTab(){
    click(By.xpath("//*[@id=\"content\"]/form/div/ul/li[4]/a"));     //gotoPriceTab
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.name("tax_class_id")));





  }

  public void fillThreeTabsProductForm() {
    fillGeneralTab();
    fillInformationTab();
    fillPricesTab();
    submitAddNewProduct();
  }
}
