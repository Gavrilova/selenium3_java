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
    app.goTo().gotoPage(folderSet.get(folderSet.size() - 1), "td h1"); //if we have subfolder, we will click on most recent subfolder.
    app.logsHelper().getLogs();
    app.productPage().productsUrl().forEach((d) ->
    {
      app.goTo().gotoPage(d, "td form");
      app.logsHelper().getLogs();
    });
  }
}
