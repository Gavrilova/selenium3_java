package ru.stqa.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

/**
 * Created by irinagavrilova on 1/28/17.
 */
public class TestBase {
  ChromeDriver driver;

  @BeforeMethod
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    login("admin", "admin");
  }

  protected void choosingCategoryToEdit() {
    openNewSubMenu();
    driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr[5]/td[5]/a/i")).click();
  }

  protected void choosingProductInCategories() {
    openNewSubMenu();
    if (!driver.findElement(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[6]/td[1]/input")).isSelected()) {
      driver.findElement(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[6]/td[1]/input")).click();
    }
    if (!driver.findElement(By.xpath("//*[@id=\"content\"]/form/ul/li[3]/select/option[5]")).isSelected()) {
      driver.findElement(By.xpath("//*[@id=\"content\"]/form/ul/li[3]/select/option[5]")).click();
    }
  }

  protected void submitDeleting() {
    driver.findElement(By.name("delete")).click();
    driver.switchTo().alert().accept();
  }

  private void openNewSubMenu() {
    openCatalogMenu();
    driver.findElement(By.linkText("Big Ducks")).click();
  }

  protected void fillProductForm(ProductData productData) {
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

  protected void initEditProduct() {
    driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr[7]/td[5]/a/i")).click();
  }

  protected void submitProductDuplicate() {
    driver.findElement(By.name("duplicate")).click();
  }

  protected void choosingProductFromCategories() {
    openCatalogMenu();
    if (!driver.findElement(By.name("products[2]")).isSelected()) {
      driver.findElement(By.name("products[2]")).click();
    }
    if (!driver.findElement(By.xpath("//ul[@class='list-horizontal']/li[3]/select//option[4]")).isSelected()) {
      driver.findElement(By.xpath("//ul[@class='list-horizontal']/li[3]/select//option[4]")).click();
    }
  }

  protected void openCatalogMenu() {
    gotoCatalogSubMenuPage();
    driver.findElement(By.linkText("Rubber Ducks")).click();
    driver.findElement(By.linkText("Subcategory")).click();
  }

  protected void fillCategoryForm(CategoryData categoryData) {
    if (!driver.findElement(By.name("status")).isSelected()) {
      driver.findElement(By.name("status")).click();
    }
    driver.findElement(By.name("name[en]")).click();
    driver.findElement(By.name("name[en]")).clear();
    driver.findElement(By.name("name[en]")).sendKeys(categoryData.getCategoryNameEng());
    driver.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[4]/td/select/option[4]")).click();
    driver.findElement(By.name("save")).click();
  }

  protected void initAddNewCategory() {
    gotoCatalogSubMenuPage();
    driver.findElement(By.linkText("Add New Category")).click();
  }

  private void gotoCatalogSubMenuPage() {
    gotoCatalogPage();
    driver.findElement(By.xpath("//li[@id='doc-catalog']//span[.='Catalog']")).click();
  }

  protected void logoutAdminSession() {
    driver.findElement(By.xpath("//td[@id='sidebar']/div[2]/a[5]/i")).click();
  }

  protected void gotoProductGroupPage() {
    driver.findElement(By.linkText("Product Groups")).click();
  }

  protected void gotoAcmeCorpPage() {
    driver.findElement(By.linkText("ACME Corp.")).click();
    driver.findElement(By.linkText("Information")).click();
  }

  protected void gotoManufacturesPage() {
    driver.findElement(By.linkText("Manufacturers")).click();
  }

  protected void gotoCatalogPage() {
    driver.findElement(By.linkText("Catalog")).click();
  }

  private void login(String username, String password) {
    driver.get("http://admin:admin@localhost/litecart/admin/login.php");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys(username);
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys(password);
    driver.findElement(By.name("login")).click();
  }

  @AfterMethod
  public void stop() {
    driver.quit();
    driver = null;
  }
}
