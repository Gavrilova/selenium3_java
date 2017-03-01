package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.model.ProductData;

import java.util.concurrent.TimeUnit;

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

    type(By.name("campaigns[new_1][percentage]"), productData.getCampaignsPercentage());
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

  public void initAddNewProduct() {
    click(By.xpath("//*[@id=\"content\"]/div[1]/a[2]"));
  }

  public void submitAddNewProduct() {
    click(By.cssSelector("i.fa.fa-floppy-o"));
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.cssSelector("div.notice.success")));
  }

  public void setDatepicker(By locator, String date) {//C# code: new WebDriverWait(driver, SECONDS).Until<boolean>(d => driver.findElement(By.cssSelector(cssSelector)).isDisplayed();
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    //driver.findElement(locator).clear();
    driver.findElement(locator).sendKeys(date);
    driver.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[11]/td/strong")).click();
  }

  private void setDecimalType(By locator, String data) {
    click(locator);
    driver.findElement(locator).clear();
    set(locator, data);
  }

  private void fillQuantity(ProductData productData) {
    click(By.name("quantity"));
    driver.findElement(By.name("quantity")).clear();
    set(By.name("quantity"), productData.getQuantity());
  }

  public void fillGeneralTab(ProductData productData) {
    click(By.xpath("//*[@id=\"content\"]/form/div/ul/li[1]/a"));
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.name("default_category_id")));
    driver.findElements(By.name("status")).get(0).click();
    type(By.name("name[en]"), productData.getNameEng());
    type(By.name("code"), productData.getGeneralCode());
    //checked
    if (!driver.findElements(By.name("categories[]")).get(2).isSelected()) {
      driver.findElements(By.name("categories[]")).get(2).click();
    }
    driver.findElements(By.name("product_groups[]")).get(1).click();
    setDecimalType(By.name("quantity"), productData.getQuantity());
    click(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[8]/td/table/tbody/tr/td[2]/strong"));
    attach(By.name("new_images[]"), productData.getPhoto());
    setDatepicker(By.name("date_valid_from"), productData.getDataValidFrom());
    setDatepicker(By.name("date_valid_to"), productData.getDataValidTo());

  }


  public void fillInformationTab(ProductData productData) {
    click(By.xpath("//*[@id=\"content\"]/form/div/ul/li[2]/a")); //gotoInformationTab
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.name("manufacturer_id")));
    new Select(driver.findElement(By.name("manufacturer_id"))).selectByValue("1");
    type(By.name("keywords"), productData.getKeywords());
    type(By.name("short_description[en]"), productData.getShortDescriptionEng());
    type(By.cssSelector("div.trumbowyg-editor"), productData.getDescriptionEng());
    //head_title[en]
    type(By.name("head_title[en]"), productData.getHeadTitle());
    //meta_description[en]
    type(By.name("meta_description[en]"), productData.getMetaDescriptionEng());
  }

  public void fillPricesTab(ProductData productData) {
    click(By.xpath("//*[@id=\"content\"]/form/div/ul/li[4]/a"));     //gotoPriceTab
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.name("tax_class_id")));
    //purchase_price
    setDecimalType(By.name("purchase_price"), productData.getPurchasePrice());
    click(By.cssSelector("h2")); //h2 - not unique locator!
    //purchase_price_currency_code
    new Select(driver.findElement(By.name("purchase_price_currency_code"))).selectByValue("USD");
    //prices[USD]
    setDecimalType(By.name("prices[USD]"), productData.getPriceUSD());
    click(By.cssSelector("h2"));
    //gross_prices[USD]
    // after clicking on the Save button the value of gross_prices[USD] will be reset to zero
    setDecimalType(By.name("gross_prices[USD]"), productData.getGrossPricesUSD());
    click(By.cssSelector("h2"));
    //prices[EUR]
    setDecimalType(By.name("prices[EUR]"), productData.getPriceEUR());
    click(By.cssSelector("h2"));
    //gross_prices[EUR]
    // after clicking on the Save button the value of gross_prices[USD] will be reset to zero
    setDecimalType(By.name("gross_prices[EUR]"), productData.getGrossPricesEUR());
    click(By.cssSelector("h2"));
    //set Campaigns
    click(By.xpath("//a[@id='add-campaign']/i"));
    type(By.name("campaigns[new_1][percentage]"), productData.getCampaignsPercentage());
    click(By.name("campaigns[new_1][percentage]"));
  }


  public void fillThreeTabs(ProductData productData) {
    fillGeneralTab(productData);
    fillInformationTab(productData);
    fillPricesTab(productData);
    submitAddNewProduct();
  }
}
