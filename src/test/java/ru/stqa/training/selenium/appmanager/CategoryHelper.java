package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.training.selenium.model.CategoryData;

/**
 * Created by irinagavrilova on 1/28/17.
 */
public class CategoryHelper {
  private ChromeDriver driver;

  public CategoryHelper(ChromeDriver driver) {
    this.driver=driver;
  }

  public void fillCategoryForm(CategoryData categoryData) {
    if (!driver.findElement(By.name("status")).isSelected()) {
      driver.findElement(By.name("status")).click();
    }
    driver.findElement(By.name("name[en]")).click();
    driver.findElement(By.name("name[en]")).clear();
    driver.findElement(By.name("name[en]")).sendKeys(categoryData.getCategoryNameEng());
    driver.findElement(By.xpath("//*[@id=\"tab-general\"]/table/tbody/tr[4]/td/select/option[4]")).click();
    driver.findElement(By.name("save")).click();
  }
}
