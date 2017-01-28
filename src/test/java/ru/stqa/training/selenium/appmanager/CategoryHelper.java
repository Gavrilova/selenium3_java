package ru.stqa.training.selenium.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.training.selenium.model.CategoryData;

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

  public void submitCategoryDeleting() {
    click(By.name("delete"));
    driver.switchTo().alert().accept();
  }
}
