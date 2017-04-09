package ru.stqa.training.selenium.tests;

import org.testng.annotations.Test;

import java.util.ArrayList;

/**
 * Created by irinagavrilova on 3/14/17.
 */
public class AdminEditCountriesTests extends TestBase {

  @Test
  public void testAdminEditCountries() {
    app.goTo().gotoCountriesPages();
    app.edit().addNewCountries();
    ArrayList<String> list = app.edit().test();
    for (int i=0; i<list.size(); i++) {
      app.edit().handleWindow(i);
      System.out.println(list.get(i));
    }
   /*
    app.edit().handleWindow(1);
    System.out.println(list.get(1));
    app.edit().handleWindow(2);
    System.out.println(list.get(2));
    app.edit().handleWindow(4);
    System.out.println(list.get(4));
    app.edit().handleWindow(5);
    System.out.println(list.get(5));
    app.edit().handleWindow(6);
    System.out.println(list.get(6));
    app.edit().handleWindow(7);
    System.out.println(list.get(7));
    */
  }
}