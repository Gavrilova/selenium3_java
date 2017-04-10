package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toCollection;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

/**
 * Created by irinagavrilova on 3/14/17.
 */
public class CountriesEditHelper extends HelperBase {
  public CountriesEditHelper(ChromeDriver driver) {
    super(driver);
  }

  WebDriverWait wait = new WebDriverWait(driver, 10);

  public void addNewCountries() {
    driver.findElement(By.cssSelector("a.button")).click();
    wait.until(visibilityOfAllElementsLocatedBy(By.cssSelector("i.fa-external-link")));
  }

  public ArrayList<String> links() {
    List<WebElement> web = driver.findElementsByCssSelector("form td a");
    return web.stream().filter(webElement -> !webElement.getText().equals("?"))
            .map((d) -> d.getAttribute("href")).collect(toCollection(ArrayList<String>::new));
  }

  public List<WebElement> listWebElements() {
    return driver.findElementsByCssSelector("form td a").stream()
            .filter(webElement -> !webElement.getText().equals("?"))
            .collect(Collectors.toList());
  }

  public void handleWindow(int i) {
    WebElement element = listWebElements().get(i);
    String mainWindow = driver.getWindowHandle();
    Set<String> oldWindows = driver.getWindowHandles();
    element.click();   //открываем новое окно
    Set<String> newWindows = driver.getWindowHandles();  //смотрим список идентификаторов окон
    newWindows.removeAll(oldWindows);
    String handle0 = newWindows.iterator().next();
    driver.switchTo().window(handle0);
    if (i != 3) {
      wait.until(visibilityOfAllElementsLocatedBy(By.cssSelector("h1")));
    } else {
      wait.until(visibilityOf(driver.findElement(By.cssSelector("input.no-margin-bottom"))));
      wait.until(visibilityOf(driver.findElement(By.cssSelector("div.marketo-formContent h1"))));
      assertTrue(driver.findElement(By.cssSelector("div.marketo-formContent h1"))
              .getText().equals("Sign up for free demo"));
    }

    driver.close();
    driver.switchTo().window(mainWindow);
  }

  public void assertTargetBlank() {// надо убедиться в том, что у ссылки есть атрибут target="_blank"
    listWebElements().stream()
            .forEach(webElement -> {
              assertTrue(webElement.getAttribute("target").equals("_blank"));
            });
  }
}
