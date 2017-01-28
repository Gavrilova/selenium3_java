package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import ru.stqa.training.selenium.model.CategoryData;
import ru.stqa.training.selenium.model.ProductData;

import java.util.concurrent.TimeUnit;

/**
 * Created by irinagavrilova on 1/28/17.
 */
public class ApplicationManager {
  ChromeDriver driver;

  public void init() {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    login("admin", "admin");
  }

  public void choosingCategoryToEdit() {
    openNewSubMenu();
    driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr[5]/td[5]/a/i")).click();
  }

  public void choosingProductInCategories() {
    openNewSubMenu();
    if (!driver.findElement(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[6]/td[1]/input")).isSelected()) {
      driver.findElement(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[6]/td[1]/input")).click();
    }
    if (!driver.findElement(By.xpath("//*[@id=\"content\"]/form/ul/li[3]/select/option[5]")).isSelected()) {
      driver.findElement(By.xpath("//*[@id=\"content\"]/form/ul/li[3]/select/option[5]")).click();
    }
  }

  public void submitDeleting() {
    driver.findElement(By.name("delete")).click();
    driver.switchTo().alert().accept();
  }

  private void openNewSubMenu() {
    openCatalogMenu();
    driver.findElement(By.linkText("Big Ducks")).click();
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

  public void choosingProductFromCategories() {
    openCatalogMenu();
    if (!driver.findElement(By.name("products[2]")).isSelected()) {
      driver.findElement(By.name("products[2]")).click();
    }
    if (!driver.findElement(By.xpath("//ul[@class='list-horizontal']/li[3]/select//option[4]")).isSelected()) {
      driver.findElement(By.xpath("//ul[@class='list-horizontal']/li[3]/select//option[4]")).click();
    }
  }

  public void openCatalogMenu() {
    gotoCatalogSubMenuPage();
    driver.findElement(By.linkText("Rubber Ducks")).click();
    driver.findElement(By.linkText("Subcategory")).click();
  }

  public void fillCategoryForm(CategoryData categoryData) {
    if (!driver.findElement(By.name("status")).isSelected()) {
      driver.findElement(By.name("status")).click();
    }
    driver.findElement(By.name("name[en]")).click();
    driver.findElement(By.name("name[en]")).clear();
    driver.findElement(By.name("name[en]")).sendKeys(categoryData.getCategoryNameEng());
    driver.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[4]/td/select/option[4]")).click();
    driver.findElement(By.name("save")).click();
  }

  public void initAddNewCategory() {
    gotoCatalogSubMenuPage();
    driver.findElement(By.linkText("Add New Category")).click();
  }

  public void gotoCatalogSubMenuPage() {
    gotoCatalogPage();
    driver.findElement(By.xpath("//li[@id='doc-catalog']//span[.='Catalog']")).click();
  }

  public void logoutAdminSession() {
    driver.findElement(By.xpath("//td[@id='sidebar']/div[2]/a[5]/i")).click();
  }

  public void gotoProductGroupPage() {
    driver.findElement(By.linkText("Product Groups")).click();
  }

  public void gotoAcmeCorpPage() {
    driver.findElement(By.linkText("ACME Corp.")).click();
    driver.findElement(By.linkText("Information")).click();
  }

  public void gotoManufacturesPage() {
    driver.findElement(By.linkText("Manufacturers")).click();
  }

  public void gotoCatalogPage() {
    driver.findElement(By.linkText("Catalog")).click();
  }

  public void login(String username, String password) {
    driver.get("http://admin:admin@localhost/litecart/admin/login.php");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(username);
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.name("login")).click();
  }

  public void stop() {
    driver.quit();
    driver = null;
  }
}
