package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import ru.stqa.training.selenium.model.ProductData;

/**
 * Created by irinagavrilova on 1/28/17.
 */
public class ProductHelper {
  private ChromeDriver driver;

  public ProductHelper(ChromeDriver driver) {
    this.driver = driver;
  }

  public void fillProductForm(ProductData productData) {
    driver.findElement(By.name("code")).click();
    driver.findElement(By.name("code")).clear();
    driver.findElement(By.name("code")).sendKeys(productData.getGeneralCode());

    driver.findElement(By.xpath("//div[@id='tab-general']/table/tbody/tr[3]/td")).click();
    driver.findElement(By.name("name[en]")).click();
    driver.findElement(By.name("name[en]")).clear();
    driver.findElement(By.name("name[en]")).sendKeys(productData.getNameEng());
    if (!driver.findElement(By.xpath("//div[@id='tab-general']/table/tbody/tr[4]/td/div/table/tbody/tr[4]/td[1]/input")).isSelected()) {
      driver.findElement(By.xpath("//div[@id='tab-general']/table/tbody/tr[4]/td/div/table/tbody/tr[4]/td[1]/input")).click();
    }
    if (driver.findElement(By.xpath("//div[@id='tab-general']/table/tbody/tr[4]/td/div/table/tbody/tr[3]/td[1]/input")).isSelected()) {
      driver.findElement(By.xpath("//div[@id='tab-general']/table/tbody/tr[4]/td/div/table/tbody/tr[3]/td[1]/input")).click();
    }
    if (!driver.findElement(By.name("product_groups[]")).isSelected()) {
      driver.findElement(By.name("product_groups[]")).click();
    }
    new Actions(driver).doubleClick(driver.findElement(By.name("product_groups[]"))).build().perform();
    driver.findElement(By.name("quantity")).click();
    driver.findElement(By.name("quantity")).sendKeys(productData.getQuantity());
    driver.findElement(By.name("quantity")).click();
    driver.findElement(By.linkText("Information")).click();
    driver.findElement(By.name("short_description[en]")).click();
    driver.findElement(By.name("short_description[en]")).clear();
    driver.findElement(By.name("short_description[en]")).sendKeys(productData.getShortDescriptionEng());
    driver.findElement(By.cssSelector("div.trumbowyg-editor")).click();
    driver.findElement(By.linkText("Data")).click();
    driver.findElement(By.name("sku")).click();
    driver.findElement(By.name("sku")).clear();
    driver.findElement(By.name("sku")).sendKeys(productData.getSKU());
    driver.findElement(By.xpath("//div[@id='tab-data']/table/tbody/tr[1]/td")).click();
    driver.findElement(By.linkText("Prices")).click();
    driver.findElement(By.name("prices[USD]")).click();
    driver.findElement(By.name("prices[USD]")).clear();
    driver.findElement(By.name("prices[USD]")).sendKeys(productData.getPriceUSD());
    driver.findElement(By.xpath("//a[@id='add-campaign']/i")).click();
    driver.findElement(By.name("campaigns[new_1][percentage]")).click();
    driver.findElement(By.name("campaigns[new_1][percentage]")).clear();
    driver.findElement(By.name("campaigns[new_1][percentage]")).sendKeys(productData.getCompaingsPercentage());
    driver.findElement(By.name("campaigns[new_1][percentage]")).click();
    driver.findElement(By.name("save")).click();
  }

  public void initEditProduct() {
    driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr[7]/td[5]/a/i")).click();
  }

  public void submitProductDuplicate() {
    driver.findElement(By.name("duplicate")).click();
  }
}
