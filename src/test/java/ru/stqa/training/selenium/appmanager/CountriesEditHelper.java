package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.ArrayList;
import java.util.stream.Collectors;

import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfAllElementsLocatedBy;

/**
 * Created by irinagavrilova on 3/14/17.
 */
public class CountriesEditHelper extends HelperBase {
  public CountriesEditHelper(ChromeDriver driver) {
    super(driver);
  }

  WebDriverWait wait = new WebDriverWait(driver, 10);

  public void test(){
    ArrayList<String> list = links();
    list.stream().forEach(System.out::println);
  }

  public void addNewCountries() {
    driver.findElement(By.cssSelector("a.button")).click();
    wait.until(visibilityOfAllElementsLocatedBy(By.cssSelector("i.fa-external-link")));
  }

  public ArrayList<String> links(){
   return  driver.findElementsByCssSelector("form td a").stream()
           .map((d)->d.getAttribute("href")).collect(Collectors.toCollection(ArrayList<String>::new));

  }
  /*
  public void closeWindow(URL url) {
    String newWindow = wait.until(thereIsWindowOtherThan(oldWindows));
    driver.switchTo().window(newWindow);
    driver.close();
    driver.switchTo().window(mainWindow);
  }
  */

}
