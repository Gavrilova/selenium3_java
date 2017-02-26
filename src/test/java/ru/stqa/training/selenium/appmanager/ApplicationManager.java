package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.chrome.ChromeDriver;

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
  private CountriesHelper countriesHelper;

  public void init() {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    productHelper = new ProductHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    sessionHelper = new SessionHelper(driver);
    categoryHelper = new CategoryHelper(driver);
    countriesHelper = new CountriesHelper(driver);
    sessionHelper.login("admin", "admin");
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

  public CountriesHelper getCountriesHelper() {return countriesHelper;}
}