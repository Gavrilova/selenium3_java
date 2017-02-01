package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;

/**
 * Created by irinagavrilova on 1/28/17.
 */
public class NavigationHelper extends HelperBase {

  public NavigationHelper(ChromeDriver driver) {
    super(driver);
  }

  protected void openNewSubMenu() {
    openCatalogMenu();
    click(By.linkText("Big Ducks"));
  }

  public void choosingProductFromCategories() {
    openCatalogMenu();
    if (!driver.findElement(By.name("products[2]")).isSelected()) {
      click(By.name("products[2]"));
    }
    if (!driver.findElement(By.xpath("//ul[@class='list-horizontal']/li[3]/select//option[4]")).isSelected()) {
      click(By.xpath("//ul[@class='list-horizontal']/li[3]/select//option[4]"));
    }
  }

  public void choosingProductInCategories() {
    openNewSubMenu();
    if (!driver.findElement(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[6]/td[1]/input")).isSelected()) {
      click(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[6]/td[1]/input"));
    }
    if (!driver.findElement(By.xpath("//*[@id=\"content\"]/form/ul/li[3]/select/option[5]")).isSelected()) {
      click(By.xpath("//*[@id=\"content\"]/form/ul/li[3]/select/option[5]"));
    }
  }

  public void gotoCatalogPage() {
    click(By.linkText("Catalog"));
  }

  public void openCatalogMenu() {
    gotoCatalogSubMenuPage();
    click(By.linkText("Rubber Ducks"));
    click(By.linkText("Subcategory"));
  }

  public void gotoCatalogSubMenuPage() {
    gotoCatalogPage();
    click(By.xpath("//li[@id='doc-catalog']//span[.='Catalog']"));
  }

  public void choosingCategoryToEdit() {
    openNewSubMenu();
    click(By.xpath("//table[@class='dataTable']/tbody/tr[5]/td[5]/a/i"));
  }

  public void gotoProductGroupPage() {

    click(By.linkText("Product Groups"));
  }

  public void gotoAcmeCorpPage() {
    click(By.linkText("ACME Corp."));
    click(By.linkText("Information"));
  }

  public void gotoManufacturesPage() {

    click(By.linkText("Manufacturers"));
  }
  public void gotoHomePage() {

    click(By.cssSelector("i.fa-home"));
  }


  public void gotoPage(By locator) {
   click(locator);
 }

  public void logoutAdminSession() {

    driver.findElement(By.xpath("//td[@id='sidebar']/div[2]/a[5]/i")).click();
  }
}
