package ru.stqa.training.selenium;


import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


public class AdminUsage {


  ChromeDriver driver;

  @BeforeMethod
  public void setUp() throws Exception {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    login();
  }


  @Test
  public void testAdminUsage() {
    gotoCatalogPage();
    gotoManufacturesPage();
    gotoAcmeCorpPage();
    gotoCatalogPage();
    gotoProductGroupPage();
    openCatalogMenu();
    initAddNewCategory();
    fillCategoryForm();
    choosingProductFromCategories();
    submitProductDuplicate();
    initEditProduct();
    fillProductForm();
    choosingProductInCategories();
    submitDeleting();
    choosingCategoryToEdit();
    submitDeleting();
    logoutAdminSession();
  }

  private void choosingCategoryToEdit() {
    openNewSubMenu();
    driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr[5]/td[5]/a/i")).click();
  }

  private void choosingProductInCategories() {
    openNewSubMenu();
    if (!driver.findElement(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[6]/td[1]/input")).isSelected()) {
      driver.findElement(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[6]/td[1]/input")).click();
    }
    if (!driver.findElement(By.xpath("//*[@id=\"content\"]/form/ul/li[3]/select/option[5]")).isSelected()) {
      driver.findElement(By.xpath("//*[@id=\"content\"]/form/ul/li[3]/select/option[5]")).click();
    }
  }

  private void submitDeleting() {
    driver.findElement(By.name("delete")).click();
    driver.switchTo().alert().accept();
  }

  private void openNewSubMenu() {
    openCatalogMenu();
    driver.findElement(By.linkText("Big Ducks")).click();
  }

  private void fillProductForm() {
    driver.findElement(By.name("code")).click();
    driver.findElement(By.name("code")).clear();
    driver.findElement(By.name("code")).sendKeys("RD009");

    driver.findElement(By.xpath("//div[@id='tab-general']/table/tbody/tr[3]/td")).click();
    driver.findElement(By.name("name[en]")).click();
    driver.findElement(By.name("name[en]")).clear();
    driver.findElement(By.name("name[en]")).sendKeys("Big Green Duck");
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
    driver.findElement(By.name("quantity")).sendKeys("\\undefined");
    driver.findElement(By.name("quantity")).click();
    driver.findElement(By.linkText("Information")).click();
    driver.findElement(By.name("short_description[en]")).click();
    driver.findElement(By.name("short_description[en]")).clear();
    driver.findElement(By.name("short_description[en]")).sendKeys("Best big green duck ever");
    driver.findElement(By.cssSelector("div.trumbowyg-editor")).click();
    driver.findElement(By.linkText("Data")).click();
    driver.findElement(By.name("sku")).click();
    driver.findElement(By.name("sku")).clear();
    driver.findElement(By.name("sku")).sendKeys("RD009");
    driver.findElement(By.xpath("//div[@id='tab-data']/table/tbody/tr[1]/td")).click();
    driver.findElement(By.linkText("Prices")).click();
    driver.findElement(By.name("prices[USD]")).click();
    driver.findElement(By.name("prices[USD]")).clear();
    driver.findElement(By.name("prices[USD]")).sendKeys("10.0000");
    driver.findElement(By.xpath("//a[@id='add-campaign']/i")).click();
    driver.findElement(By.name("campaigns[new_1][percentage]")).click();
    driver.findElement(By.name("campaigns[new_1][percentage]")).sendKeys("\\undefined");
    driver.findElement(By.name("campaigns[new_1][percentage]")).click();
    driver.findElement(By.name("campaigns[new_1][percentage]")).clear();
    driver.findElement(By.name("campaigns[new_1][percentage]")).sendKeys("4");
    driver.findElement(By.name("campaigns[new_1][percentage]")).click();
    driver.findElement(By.name("campaigns[new_1][percentage]")).clear();
    driver.findElement(By.name("campaigns[new_1][percentage]")).sendKeys("6");
    driver.findElement(By.name("campaigns[new_1][percentage]")).click();
    driver.findElement(By.name("campaigns[new_1][percentage]")).clear();
    driver.findElement(By.name("campaigns[new_1][percentage]")).sendKeys("8");
    driver.findElement(By.name("campaigns[new_1][percentage]")).click();
    driver.findElement(By.name("campaigns[new_1][percentage]")).clear();
    driver.findElement(By.name("campaigns[new_1][percentage]")).sendKeys("10");
    driver.findElement(By.name("campaigns[new_1][percentage]")).click();
    driver.findElement(By.name("save")).click();
  }

  private void initEditProduct() {
    driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr[7]/td[5]/a/i")).click();
  }

  private void submitProductDuplicate() {
    driver.findElement(By.name("duplicate")).click();
  }

  private void choosingProductFromCategories() {
    openCatalogMenu();
    if (!driver.findElement(By.name("products[2]")).isSelected()) {
      driver.findElement(By.name("products[2]")).click();
    }
    if (!driver.findElement(By.xpath("//ul[@class='list-horizontal']/li[3]/select//option[4]")).isSelected()) {
      driver.findElement(By.xpath("//ul[@class='list-horizontal']/li[3]/select//option[4]")).click();
    }
  }

  private void openCatalogMenu() {
    gotoCatalogSubMenuPage();
    driver.findElement(By.linkText("Rubber Ducks")).click();
    driver.findElement(By.linkText("Subcategory")).click();
  }

  private void fillCategoryForm() {
    if (!driver.findElement(By.name("status")).isSelected()) {
      driver.findElement(By.name("status")).click();
    }
    driver.findElement(By.name("name[en]")).click();
    driver.findElement(By.name("name[en]")).clear();
    driver.findElement(By.name("name[en]")).sendKeys("Big Ducks");
    driver.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[4]/td/select/option[4]")).click();
    driver.findElement(By.name("save")).click();
  }

  private void initAddNewCategory() {
    gotoCatalogSubMenuPage();
    driver.findElement(By.linkText("Add New Category")).click();
  }

  private void gotoCatalogSubMenuPage() {
    gotoCatalogPage();
    driver.findElement(By.xpath("//li[@id='doc-catalog']//span[.='Catalog']")).click();
  }

  private void logoutAdminSession() {
    driver.findElement(By.xpath("//td[@id='sidebar']/div[2]/a[5]/i")).click();
  }

  private void gotoProductGroupPage() {
    driver.findElement(By.linkText("Product Groups")).click();
  }

  private void gotoAcmeCorpPage() {
    driver.findElement(By.linkText("ACME Corp.")).click();
    driver.findElement(By.linkText("Information")).click();
  }

  private void gotoManufacturesPage() {
    driver.findElement(By.linkText("Manufacturers")).click();
  }

  private void gotoCatalogPage() {
    driver.findElement(By.linkText("Catalog")).click();
  }

  private void login() {
    driver.get("http://admin:admin@localhost/litecart/admin/login.php");
    driver.findElement(By.name("username")).click();
    driver.findElement(By.name("username")).clear();
    driver.findElement(By.name("username")).sendKeys("admin");
    driver.findElement(By.name("password")).click();
    driver.findElement(By.name("password")).clear();
    driver.findElement(By.name("password")).sendKeys("admin");
    driver.findElement(By.name("login")).click();
  }

  @AfterMethod
  public void stop() {
    driver.quit();
    driver = null;
  }
}
