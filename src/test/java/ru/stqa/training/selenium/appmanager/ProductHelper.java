package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import ru.stqa.training.selenium.model.ProductData;

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

}
