package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.training.selenium.model.CategoryData;

import java.util.List;

/**
 * Created by irinagavrilova on 1/28/17.
 */
public class CategoryHelper extends HelperBase {

  public CategoryHelper(ChromeDriver driver) {
    super(driver);
  }

  public void fillCategoryForm(CategoryData categoryData) {
    if (!driver.findElement(By.name("status")).isSelected()) {
      click(By.name("status"));
    }
    type(By.name("name[en]"), categoryData.getCategoryNameEng());
    click(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[4]/td/select/option[4]"));
    click(By.name("save"));
  }

  public void initAddNewCategory() {

    click(By.linkText("Add New Category"));
  }

  public void submitCategoryDeleting() {
    click(By.name("delete"));
    driver.switchTo().alert().accept();
  }


  public boolean areElementsPresent(By locator) {
    try {
      return driver.findElements(locator).size() > 0;
    } catch (InvalidSelectorException ex) {
      return false;
    }
  }


  public List<WebElement> listElementsPresent(By locator) {
    if (isElementPresent(locator)==true) {
      return driver.findElements(locator);
    } else {
      return null;
    }
  }

  public String urlCurrent(By locator) {
    driver.findElement(locator).click();
    return driver.getCurrentUrl();
  }

  public int quantityElementsPresent(By locator) {
    if (isElementPresent(locator)) {
      return driver.findElements(locator).size();
    } else {
      return 0;
    }
  }


  public String[] list(By locator, int size) {
    List<WebElement> commandMenu = listElementsPresent(locator);
    String[] commandText = new String[size];
    int i = 0;
    for (WebElement e : commandMenu) {
      String str = e.getText();
      commandText[i] = str;
      //     System.out.println(str);
      i++;
    }
    return commandText;
  }

}
