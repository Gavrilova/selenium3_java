package ru.stqa.training.selenium.tests;


import org.testng.annotations.Test;
import ru.stqa.training.selenium.model.CategoryData;
import ru.stqa.training.selenium.model.ProductData;


public class AdminUsage extends TestBase {

  @Test
  public void testAdminUsage() {
    app.goTo().gotoCatalogPage();
    app.goTo().gotoManufacturesPage();
    app.goTo().gotoAcmeCorpPage();
    app.goTo().gotoCatalogPage();
    app.goTo().gotoProductGroupPage();
    app.goTo().openCatalogMenu();
    app.goTo().gotoCatalogSubMenuPage();
    app.categoryPage().initAddNewCategory();
    app.categoryPage().fillCategoryForm(new CategoryData("Big Ducks"));
    app.goTo().choosingProductFromCategories();
    app.productPage().submitProductDuplicate();
    app.productPage().initEditProduct();
    app.productPage().fillProductForm(new ProductData("RD009", "RD009", "10.0000", "10", "Big Green Duck", "Best big green duck ever", "\\undefined"));
    app.goTo().choosingProductInCategories();
    app.productPage().submitProductDeleting();
    app.goTo().choosingCategoryToEdit();
    app.categoryPage().submitCategoryDeleting();
    app.goTo().logoutAdminSession();
  }

}
