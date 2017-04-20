package ru.stqa.training.selenium.tests;

import org.testng.annotations.Test;

/**
 * Created by irinagavrilova on 4/20/17.
 */
public class CataLogsTests extends TestBase {

  @Test
  public void testCatalogLogs() {
    app.goTo().gotoCategoryPage();
    app.logsHelper().getBrowserLogs();
    app.logsHelper().getPerformanceLogs();
  }


}
