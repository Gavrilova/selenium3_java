package ru.stqa.training.selenium.tests;

import org.junit.Assert;
import org.testng.annotations.Test;
import ru.stqa.training.selenium.model.ProductData;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by irinagavrilova on 2/28/17.
 */
public class ProductCreationTests extends TestBase {

  @Test
  public void testProductCreation() {
    app.goTo().gotoCatalogPage();
    int before = app.productPage().productCount();

    app.productPage().initAddNewProduct();

    String generalCode = "rd" + (new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())).replace(".","");  //should be unique to each product!
    File photo = new File("src/test/resources/Zello.png") ;
    app.productPage().fillThreeTabs(new ProductData().withNameEng("Big Zello duck")
            .withGeneralCode(generalCode).withQuantity("23").withPhoto(photo)
            .withDataValidFrom("12012017").withDataValidTo("12042018")
            .withKeywords("keywordsZello").withShortDescriptionEng("short description")
            .withDescriptionEng("You never get better duck!")
            .withHeadTitle("HeadTitle Zello duck").withMetaDescriptionEng("Meta Description Zello duck")
            .withPurchasePrice("17")
            .withPriceUSD("19").withGrossPricesUSD("19.82").withPriceEUR("18").withGrossPricesEUR("18.15")
            .withCampaingnsPercentage("15"));

    app.goTo().gotoCatalogPage();
    int after = app.productPage().productCount();
    //after = before + 2; because product has two links: with name and edit button
    Assert.assertEquals(after,(before+2));
  }

}
