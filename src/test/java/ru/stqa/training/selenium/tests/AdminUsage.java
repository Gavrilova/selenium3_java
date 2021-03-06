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
    app.product().submitProductDuplicate();
    app.product().initEditProduct();
    app.product().fillProductForm(
            new ProductData().withGeneralCode("RD009").withSKU("RD009")
            .withPriceUSD("10.0000").withCampaingnsPercentage("10").withNameEng("Big Green Duck")
            .withShortDescriptionEng("Best big green duck ever").withQuantity("\\undefined"));
    app.goTo().choosingProductInCategories();
    app.product().submitProductDeleting();
    app.goTo().choosingCategoryToEdit();
    app.categoryPage().submitCategoryDeleting();
    app.goTo().logoutAdminSession();
  }

}
