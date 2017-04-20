package ru.stqa.training.selenium.tests;

import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created by irinagavrilova on 4/20/17.
 */
public class CataLogsTests extends TestBase {

  @Test
  public void testCatalogLogs() {
    app.goTo().gotoCategoryPage();
    ArrayList<String> folderSet = app.productPage().folderUrl();
    app.productPage().printList(folderSet);
    app.goTo().gotoPage(folderSet.get(folderSet.size() - 1)); //if we have subfolder, we will click on most recent subfolder.
    app.logsHelper().getLogs();


    ArrayList<String> productSet = app.productPage().productsUrl();
    app.productPage().printList(productSet);
    productSet.forEach((d) ->
    {
      app.goTo().gotoPage(d);
      app.logsHelper().getLogs();
    });
  }


}
