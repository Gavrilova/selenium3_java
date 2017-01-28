package ru.stqa.training.selenium;


import org.testng.annotations.Test;


public class AdminUsage extends TestBase {

  @Test
  public void testAdminUsage() {
    gotoCatalogPage();
    gotoManufacturesPage();
    gotoAcmeCorpPage();
    gotoCatalogPage();
    gotoProductGroupPage();
    openCatalogMenu();
    initAddNewCategory();
    fillCategoryForm(new CategoryData("Big Ducks"));
    choosingProductFromCategories();
    submitProductDuplicate();
    initEditProduct();
    fillProductForm(new ProductData("RD009", "RD009", "10.0000", "10", "Big Green Duck", "Best big green duck ever", "\\undefined"));
    choosingProductInCategories();
    submitDeleting();
    choosingCategoryToEdit();
    submitDeleting();
    logoutAdminSession();
  }

}
