package ru.stqa.training.selenium.appmanager;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

/**
 * Created by irinagavrilova on 3/14/17.
 */
public class CountriesEditHelper extends HelperBase {
  public CountriesEditHelper(ChromeDriver driver) {
    super(driver);
  }

  WebDriverWait wait = new WebDriverWait(driver, 50);

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
    return driver.findElementsByCssSelector("form td a").stream()
            .map((d) -> d.getAttribute("href")).collect(Collectors.toCollection(ArrayList<String>::new));

  }

  public void handleWindow(int i) {
    System.out.println("i =" + i + driver.findElements(By.cssSelector("form td a")).get(i).getText());
    if (driver.findElements(By.cssSelector("form td a")).get(i).getText().equals("?")) {
    } else {
      String mainWindow = driver.getWindowHandle();
      Set<String> oldWindows = driver.getWindowHandles();
      System.out.println("old");
      for (String handle : oldWindows) {
        System.out.println(handle);
      }

      driver.findElements(By.cssSelector("form td a")).get(i).click();

      //driver.get(link); //открываем новое окно
      Set<String> newWindows = driver.getWindowHandles(); //смотрим список идентификаторов окон
      //driver.getWindowHandle(); //идентификатор текущего окна
      System.out.println("new");
      for (String handle : newWindows) {
        System.out.println(handle);
      }
      newWindows.removeAll(oldWindows);
      System.out.println("After removingAll");
      for (String handle : newWindows) {
        System.out.println(handle);
      }
      String handle0 = newWindows.iterator().next();
      driver.switchTo().window(handle0);

      if (i != 4) {
        wait.until(visibilityOfAllElementsLocatedBy(By.cssSelector("h1")));
      } else {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div.marketo-formContent h1"))));
        //String st = "div#mbox-target-global-mbox-1491740222617-708477.mboxDefault";
        String st = " input.no-margin-bottom";
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(st))));
        WebElement element1 = driver.findElement(By.cssSelector("h1"));
        Assert.assertTrue(element1.getText().equals("Sign up for free demo"));
      }


      // получаем идентификатор ведущего окна
      //link.click(); // открывает новое окно
      // ожидание появления нового окна,
// идентификатор которого отсутствует в списке oldWindows,
// остаётся в качестве самостоятельного упражнения
      // String newWindow = wait.until(thereIsWindowOtherThan(oldWindows));
      // driver.switchTo().window(newWindow);
// ...
      driver.close();
      driver.switchTo().window(mainWindow);
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

  /*
  public void closeWindow(URL url) {
    String newWindow = wait.until(thereIsWindowOtherThan(oldWindows));
    driver.switchTo().window(newWindow);
    driver.close();
    driver.switchTo().window(mainWindow);
  }

  public void handleWindow

  {
    getWindowHandle
    метод anyWindowOtherThan который идет в параметр wait.until ?
//запоминаем идентификатор текущего окна
          originalWindow = driver.getWindowHandle();
//запоминаем идентификаторы уже открытых окон
    existingWindows = driver.getWindowHandles();
//кликаем кнопку, которая открывает новое окно
    driver.findElement(By.id("button")).click;
//ждем появления нового окна с новым идентификатором
    newWindow = wait.until(anyWindowOtherThan(existingWindows))
//переключаемся в новое окно
    driver.switchTo().window(newWindow);
//закрываем его
    driver.close();
//возвращаемся в исходное окно
    driver.switchTo().window(originalWindow);
  }
  */

}
