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
    //to get list of products we need to open all nested subfolders.
    //if we have unopened subfolder, we will click on it.
    //due to we have only nested subfolders, exactly the last one will be unopened.
    while (app.product().unOpenedFolder()) {
      ArrayList<String> folderSet = app.product().folderUrl();
      app.goTo().gotoPage(folderSet.get(folderSet.size() - 1), "td h1");
    }

    app.product().productsUrl().forEach((d) ->
    {
      app.goTo().gotoPage(d, "td form");
      app.logsHelper().getLogs();
    });
  }
}
