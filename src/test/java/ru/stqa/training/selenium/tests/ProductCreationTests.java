package ru.stqa.training.selenium.tests;

import org.testng.annotations.Test;
import ru.stqa.training.selenium.model.ProductData;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import static org.junit.Assert.assertEquals;

/**
 * Created by irinagavrilova on 2/28/17.
 */
public class ProductCreationTests extends TestBase {

  @Test
  public void testProductCreation() {
    app.goTo().openCatalogMenu();
    int before = app.product().id().size();
    HashSet<String> hrefBefore = app.product().links();

    app.product().initAddNewProduct();
    String name = "Big Zello duck";
    String generalCode = "rd" + (new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date())).replace(".", "");  //should be unique to each product!
    File photo = new File("src/test/resources/Zello.png");
    app.product().fillThreeTabs(new ProductData().withNameEng(name)
            .withGeneralCode(generalCode).withQuantity("23").withPhoto(photo)
            .withDataValidFrom("02012017").withDataValidTo("02042018")
            .withKeywords("keywordsZello").withShortDescriptionEng("short description")
            .withDescriptionEng("You'll never have better duck!")
            .withHeadTitle("HeadTitle Zello duck").withMetaDescriptionEng("Meta Description Zello duck")
            .withPurchasePrice("17")
            .withPriceUSD("19").withGrossPricesUSD("19.82").withPriceEUR("18").withGrossPricesEUR("18.15")
            .withCampaingnsPercentage("15"));

    app.goTo().openCatalogMenu();
    int after = app.product().id().size();
    assertEquals(after, (before + 1));
    HashSet<String> hrefAfter = app.product().links();
    hrefAfter.removeAll(hrefBefore);
    app.product().accordance(hrefAfter.iterator().next(), name, generalCode);
  }
}
