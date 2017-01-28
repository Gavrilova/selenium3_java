package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.training.selenium.model.CategoryData;

import java.util.concurrent.TimeUnit;

/**
 * Created by irinagavrilova on 1/28/17.
 */
public class ApplicationManager {


  ChromeDriver driver;
  private CategoryHelper categoryHelper;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private ProductHelper productHelper;

  public void init() {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    productHelper = new ProductHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    sessionHelper = new SessionHelper(driver);
    categoryHelper = new CategoryHelper(driver);
    sessionHelper.login("admin", "admin");
  }

  public void choosingCategoryToEdit() {
    navigationHelper.openNewSubMenu();
    driver.findElement(By.xpath("//table[@class='dataTable']/tbody/tr[5]/td[5]/a/i")).click();
  }

  public void submitDeleting() {
    driver.findElement(By.name("delete")).click();
    driver.switchTo().alert().accept();
  }

  public void initAddNewCategory() {
    navigationHelper.gotoCatalogSubMenuPage();
    driver.findElement(By.linkText("Add New Category")).click();
  }

  public void logoutAdminSession() {
    driver.findElement(By.xpath("//td[@id='sidebar']/div[2]/a[5]/i")).click();
  }

  public void stop() {
    driver.quit();
    driver = null;
  }

  public ProductHelper getProductHelper() {
    return productHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }

  public CategoryHelper getCategoryHelper() {
    return categoryHelper;
  }
}
