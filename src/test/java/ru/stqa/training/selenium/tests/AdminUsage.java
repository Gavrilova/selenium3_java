package ru.stqa.training.selenium.tests;


import org.testng.annotations.Test;
import ru.stqa.training.selenium.model.CategoryData;
import ru.stqa.training.selenium.model.ProductData;


public class AdminUsage extends TestBase {

  @Test
  public void testAdminUsage() {
    app.getNavigationHelper().gotoCatalogPage();
    app.getNavigationHelper().gotoManufacturesPage();
    app.getNavigationHelper().gotoAcmeCorpPage();
    app.getNavigationHelper().gotoCatalogPage();
    app.getNavigationHelper().gotoProductGroupPage();
    app.getNavigationHelper().openCatalogMenu();
    app.getNavigationHelper().gotoCatalogSubMenuPage();
    app.getCategoryHelper().initAddNewCategory();
    app.getCategoryHelper().fillCategoryForm(new CategoryData("Big Ducks"));
    app.getNavigationHelper().choosingProductFromCategories();
    app.getProductHelper().submitProductDuplicate();
    app.getProductHelper().initEditProduct();
    app.getProductHelper().fillProductForm(new ProductData("RD009", "RD009", "10.0000", "10", "Big Green Duck", "Best big green duck ever", "\\undefined"));
    app.getNavigationHelper().choosingProductInCategories();
    app.getProductHelper().submitProductDeleting();
    app.getNavigationHelper().choosingCategoryToEdit();
    app.getCategoryHelper().submitCategoryDeleting();
    app.getNavigationHelper().logoutAdminSession();
  }

}
