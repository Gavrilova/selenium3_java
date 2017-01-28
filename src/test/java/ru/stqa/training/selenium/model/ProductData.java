package ru.stqa.training.selenium.model;

public class ProductData {
  private final String generalCode;
  private final String SKU;
  private final String priceUSD;
  private final String compaingsPercentage;
  private final String nameEng;
  private final String shortDescriptionEng;
  private final String quantity;

  public ProductData(String generalCode, String SKU, String priceUSD, String compaingsPercentage, String nameEng, String shortDescriptionEng, String quantity) {
    this.generalCode = generalCode;
    this.SKU = SKU;
    this.priceUSD = priceUSD;
    this.compaingsPercentage = compaingsPercentage;
    this.nameEng = nameEng;
    this.shortDescriptionEng = shortDescriptionEng;
    this.quantity = quantity;
  }

  public String getGeneralCode() {
    return generalCode;
  }

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
}
