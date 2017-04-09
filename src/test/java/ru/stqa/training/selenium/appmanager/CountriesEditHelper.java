package ru.stqa.training.selenium.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

  public ArrayList<String> test() {
    ArrayList<String> list = links();
    list.stream().forEach(System.out::println);
    return list;
  }

  public void addNewCountries() {
    driver.findElement(By.cssSelector("a.button")).click();
    wait.until(visibilityOfAllElementsLocatedBy(By.cssSelector("i.fa-external-link")));
  }

  public ArrayList<String> links() {
    List<WebElement> web = driver.findElementsByCssSelector("form td a");
    //  ArrayList<WebElement> temp = web;
    //  for (WebElement wb: temp) {
    //    if (wb.getText().equals("?")) {web.remove(wb);}
    //  }
    return web.stream()
            .map((d) -> d.getAttribute("href")).collect(Collectors.toCollection(ArrayList<String>::new));
  }

  public void handleWindow(int i) {

    WebElement elements = driver.findElements(By.cssSelector("form td a")).get(i);
    if (elements.getText().equals("?")) {
    } else {
      String mainWindow = driver.getWindowHandle();
      Set<String> oldWindows = driver.getWindowHandles();
      ArrayList<String> list = test();
      elements.click();   //открываем новое окно

      Set<String> newWindows = driver.getWindowHandles();  //смотрим список идентификаторов окон
      newWindows.removeAll(oldWindows);
      String handle0 = newWindows.iterator().next();
      driver.switchTo().window(handle0);

      System.out.println("CurrentURL = " + driver.getCurrentUrl());
      System.out.println("links." + i + ". = " + list.get(i));
      System.out.println((driver.getCurrentUrl().equals(list.get(i)))); //убеждаемся, что открыли нужное окно
      if (i != 4) {
        wait.until(visibilityOfAllElementsLocatedBy(By.cssSelector("h1")));
      } else {
        wait.until(visibilityOf(driver.findElement(By.cssSelector("div.marketo-formContent h1"))));
        wait.until(visibilityOf(driver.findElement(By.cssSelector("input.no-margin-bottom"))));
        WebElement element1 = driver.findElement(By.cssSelector("h1"));
        assertTrue(element1.getText().equals("Sign up for free demo"));
      }

      driver.close();
      driver.switchTo().window(mainWindow);
    }
  }

  public void assertTargetBlank() {// убедиться в том, что у ссылки есть атрибут target="_blank"
    for (WebElement webElement : driver.findElements(By.cssSelector("form td a"))) {
      if (webElement.getText().equals("?")) {
      } else {
        Assert.assertTrue(webElement.getAttribute("target").equals("_blank"));
      }
    }
  }

  public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {
    return new ExpectedCondition<String>() {
      public String apply(WebDriver driver) {
        Set<String> handles = driver.getWindowHandles();
        handles.removeAll(oldWindows);
        return handles.size() > 0 ? handles.iterator().next() : null;
      }
    };
  }
}
