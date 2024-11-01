package model;

public class Product
{
  private final long productId;
  private final String productName;
  private final int weightKilo;
  private final int weightGrams;
  private final String packageDate, expirationDate;

  public Product(long productId, String productName, int weightKilo,
      int weightGrams, String packageDate, String expirationDate)
  {
    this.productId = productId;
    this.productName = productName;
    this.weightKilo = weightKilo;
    this.weightGrams = weightGrams;
    this.packageDate = packageDate;
    this.expirationDate = expirationDate;
  }

  public int getWeightKilo()
  {
    return weightKilo;
  }

  public int getWeightGrams()
  {
    return weightGrams;
  }

  public String getPackageDate()
  {
    return packageDate;
  }

  public String getExpirationDate()
  {
    return expirationDate;
  }

  public long getProductId()
  {
    return productId;
  }

  public String getProductName()
  {
    return productName;
  }

}
