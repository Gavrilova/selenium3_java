package ru.stqa.training.selenium.tests;

import org.testng.annotations.Test;
import ru.stqa.training.selenium.model.ProductData;

import java.io.File;

/**
 * Created by irinagavrilova on 2/28/17.
 */
public class ProductCreationTests extends TestBase {

  @Test
  public void testProductCreation() {
    app.goTo().gotoCatalogPage();
    app.productPage().initAddNewProduct();
    String generalCode = "rd0100"; //should be unique to each product!
    File photo = new File("src/test/resources/Zello.png") ;
    app.productPage().fillThreeTabs(new ProductData().withNameEng("Big Zello duck")
            .withGeneralCode(generalCode).withQuantity("23").withPhoto(photo)
            .withDataValidFrom("12022017").withDataValidTo("12042018")
            .withKeywords("keywordsZello").withShortDescriptionEng("short description")
            .withDescriptionEng("You never get better duck!")
            .withHeadTitle("HeadTitle Zello duck").withMetaDescriptionEng("Meta Description Zello duck")

    );

  }
}
