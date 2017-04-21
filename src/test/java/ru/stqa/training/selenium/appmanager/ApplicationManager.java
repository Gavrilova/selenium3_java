package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

/**
 * Created by irinagavrilova on 1/28/17.
 */
public class ApplicationManager {

  WebDriver driver;
  WebDriverWait wait;
  //ChromeDriver driver;

  private CategoryHelper categoryHelper;
  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private ProductHelper productHelper;
  private CountriesHelper countriesHelper;
  private RegisterHelper registerHelper;
  private CartHelper cartHelper;
  private CountriesEditHelper countriesEditHelper;
  private LogsHelper logsHelper;
  private CartPageHelper cartPageHelper;
  private ProductPageHelper productPageHelper;
  private MainCustomersPageHelper mainCustomersPageHelper;


  public void init() {

    DesiredCapabilities cap = DesiredCapabilities.chrome();
    LoggingPreferences logPrefs = new LoggingPreferences();
    logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
    cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
    driver = new EventFiringWebDriver(new ChromeDriver(cap));
    wait = new WebDriverWait(driver, 10);
    //driver = new ChromeDriver();
    driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
    productHelper = new ProductHelper(driver);
    navigationHelper = new NavigationHelper(driver);
    sessionHelper = new SessionHelper(driver);
    categoryHelper = new CategoryHelper(driver);
    countriesHelper = new CountriesHelper(driver);
    registerHelper = new RegisterHelper(driver);
    cartHelper = new CartHelper(driver);
    countriesEditHelper = new CountriesEditHelper(driver);
    logsHelper = new LogsHelper(driver);
    mainCustomersPageHelper = new MainCustomersPageHelper(driver);
    productPageHelper = new ProductPageHelper(driver);
    cartPageHelper = new CartPageHelper(driver);
    sessionHelper.login("admin", "admin");
  }

  public void stop() {
    driver.quit();
    driver = null;
  }

  public ProductHelper product() {
    return productHelper;
  }

  public NavigationHelper goTo() {
    return navigationHelper;
  }

  public CategoryHelper categoryPage() {
    return categoryHelper;
  }

  public CountriesHelper countriesPage() {
    return countriesHelper;
  }

  public RegisterHelper registerPage() {
    return registerHelper;
  }

  public CartHelper cart() {
    return cartHelper;
  }

  public CountriesEditHelper edit() {
    return countriesEditHelper;
  }

  public LogsHelper logsHelper() {
    return logsHelper;
  }

  public MainCustomersPageHelper customersPage() {
    return mainCustomersPageHelper;
  }

  public ProductPageHelper productPage() {
    return productPageHelper;
  }

  public CartPageHelper cartPage() {
    return cartPageHelper;
  }
}