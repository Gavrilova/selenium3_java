package ru.stqa.training.selenium.model;

import java.io.File;

public class ProductData {
  private String generalCode;
  private String SKU;
  private String priceUSD;
  private String compaingsPercentage;
  private String nameEng;
  private String shortDescriptionEng;
  private String quantity;
  private File photo;




  public ProductData withPhoto(File photo) {
    this.photo = photo;
    return this;
  }

  public ProductData withGeneralCode(String generalCode) {
    this.generalCode = generalCode;
    return this;
  }

  public ProductData withSKU(String SKU) {
    this.SKU = SKU;
    return this;
  }

  public ProductData withPriceUSD(String priceUSD) {
    this.priceUSD = priceUSD;
    return this;
  }

  public ProductData withCompaingsPercentage(String compaingsPercentage) {
    this.compaingsPercentage = compaingsPercentage;
    return this;
  }

  public ProductData withNameEng(String nameEng) {
    this.nameEng = nameEng;
    return this;
  }

  public ProductData withShortDescriptionEng(String shortDescriptionEng) {
    this.shortDescriptionEng = shortDescriptionEng;
    return this;
  }

  public ProductData withQuantity(String quantity) {
    this.quantity = quantity;
    return this;
  }



  public String getGeneralCode() {return generalCode;}

  public String getSKU() {
    return SKU;
  }

  public String getPriceUSD() {
    return priceUSD;
  }

  public String getCompaingsPercentage() {
    return compaingsPercentage;
  }

  public String getNameEng() {
    return nameEng;
  }

  public String getShortDescriptionEng() {
    return shortDescriptionEng;
  }

  public String getQuantity() {
    return quantity;
  }

  public File getPhoto() {return photo;}
}
