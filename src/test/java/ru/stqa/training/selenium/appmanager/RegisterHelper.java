package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.training.selenium.model.RegisterData;

/**
 * Created by irinagavrilova on 2/27/17.
 */
public class RegisterHelper extends HelperBase {
  public RegisterHelper(WebDriver driver) {
    super(driver);
  }

  WebDriverWait wait = new WebDriverWait(driver, 10);

  public void fillRegisterForm(RegisterData registerData) throws InterruptedException {
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.name("firstname"))); //wait till field firstname will be present
    click(By.name("firstname"));
    type(By.name("firstname"), registerData.getFirstName());
    type(By.name("lastname"), registerData.getLastName());
    type(By.name("address1"), registerData.getAddress1());
    type(By.name("postcode"), registerData.getZipCode());
    type(By.name("city"), registerData.getCity());
    //country
    //zone-province
    type(By.name("email"), registerData.getEmail());
    type(By.name("phone"), registerData.getPhone());
    type(By.name("password"), registerData.getPassword());
    type(By.name("confirmed_password"), registerData.getConfirmedPassword());
    click(By.name("create_account"));
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.cssSelector("div.notice.success")));
  }

  public void fillForm(String name, String email, String pass) {
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.name("firstname")));
    click(By.name("firstname"));
    type(By.name("firstname"), name);
    type(By.name("lastname"), pass);
    type(By.name("address1"), "221b Baker Street");
    type(By.name("postcode"), "78749");
    type(By.name("city"), "A");
    selectCountry("US");
    selectZone("TX");
    type(By.name("email"), email);
    type(By.name("phone"), "+15122039062");
    type(By.name("password"), pass);
    type(By.name("confirmed_password"), pass);
    click(By.name("create_account"));
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.cssSelector("div.notice.success")));
  }

  public void customerLogin(String email, String pass) {
    driver.get("http://localhost/litecart/en/");
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.name("email")));
    type(By.name("email"), email);
    type(By.name("password"), pass);
    click(By.name("login"));
    new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElement(By.cssSelector("div.notice.success")));
  }

  public void selectCountry(String country) {
    new Select(driver.findElement(By.cssSelector("select.select2-hidden-accessible"))).selectByValue(country);
  }

  public void selectZone(String zone) {
    //Проблемы с ожиданиями: список зон подгружается динамически после выбора...
    //нужно добавить ожидание, чтобы успел загрузить список зон (штатов)
    //if (country.equal("US") || country.equal("CA")) {//надо передавать параметр страны
    String country = driver.findElement(By.cssSelector("select.select2-hidden-accessible")).getAttribute("value");
   // System.out.println(country);
    if (country.equals("US") || country.equals("CA")) {
      wait.until(ExpectedConditions.visibilityOf(driver.findElements(By.cssSelector("select")).get(1)));
      new WebDriverWait(driver, 20).until((WebDriver dr) -> dr.findElements(By.cssSelector("select")).get(1));
      new Select(driver.findElements(By.cssSelector(("select"))).get(1)).selectByValue(zone);
    }
  }
}
