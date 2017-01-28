package ru.stqa.training.selenium;


import org.testng.annotations.Test;


public class AdminUsage extends TestBase {

  @Test
  public void testAdminUsage() {
    app.gotoCatalogPage();
    app.gotoManufacturesPage();
    app.gotoAcmeCorpPage();
    app.gotoCatalogPage();
    app.gotoProductGroupPage();
    app.openCatalogMenu();
    app.initAddNewCategory();
    app.fillCategoryForm(new CategoryData("Big Ducks"));
    app.choosingProductFromCategories();
    app.submitProductDuplicate();
    app.initEditProduct();
    app.fillProductForm(new ProductData("RD009", "RD009", "10.0000", "10", "Big Green Duck", "Best big green duck ever", "\\undefined"));
    app.choosingProductInCategories();
    app.submitDeleting();
    app.choosingCategoryToEdit();
    app.submitDeleting();
    app.logoutAdminSession();
  }

}
