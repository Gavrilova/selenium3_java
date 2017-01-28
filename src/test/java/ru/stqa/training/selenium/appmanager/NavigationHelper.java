package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by irinagavrilova on 1/28/17.
 */
public class NavigationHelper {
  private ChromeDriver driver;

  public NavigationHelper(ChromeDriver driver) {
    this.driver = driver;
  }

  protected void openNewSubMenu() {
    openCatalogMenu();
    driver.findElement(By.linkText("Big Ducks")).click();
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
  public void choosingProductInCategories() {
    openNewSubMenu();
    if (!driver.findElement(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[6]/td[1]/input")).isSelected()) {
      driver.findElement(By.xpath("//*[@id=\"content\"]/form/table/tbody/tr[6]/td[1]/input")).click();
    }
    if (!driver.findElement(By.xpath("//*[@id=\"content\"]/form/ul/li[3]/select/option[5]")).isSelected()) {
      driver.findElement(By.xpath("//*[@id=\"content\"]/form/ul/li[3]/select/option[5]")).click();
    }
  }


  public void openCatalogMenu() {
    gotoCatalogSubMenuPage();
    driver.findElement(By.linkText("Rubber Ducks")).click();
    driver.findElement(By.linkText("Subcategory")).click();
  }

  public void gotoCatalogSubMenuPage() {
    gotoCatalogPage();
    driver.findElement(By.xpath("//li[@id='doc-catalog']//span[.='Catalog']")).click();
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
}
