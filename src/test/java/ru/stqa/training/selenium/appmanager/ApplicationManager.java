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
  private RegisterHelper registerHelper;
  private CartHelper cartHelper;



  public void init() {
    driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    productHelper = new ProductHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    sessionHelper = new SessionHelper(driver);
    categoryHelper = new CategoryHelper(driver);
    countriesHelper = new CountriesHelper(driver);
    registerHelper = new RegisterHelper(driver);
    cartHelper = new CartHelper(driver);
    sessionHelper.login("admin", "admin");
  }

  public void stop() {
    driver.quit();
    driver = null;
  }

  public ProductHelper productPage() {
    return productHelper;
  }

  public NavigationHelper goTo() {return navigationHelper;
  }

  public CategoryHelper categoryPage() {
    return categoryHelper;
  }

  public CountriesHelper countriesPage() {return countriesHelper;}

  public RegisterHelper registerPage() {return registerHelper;}

  public CartHelper cart() { return cartHelper;}
}