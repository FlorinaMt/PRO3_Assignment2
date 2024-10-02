package model;

public class Animal
{

  private long animalRegNo;
  private String animalType;
  private int weightKilos, weightGrams;

  public Animal(long animalId, String animalType, int weightKilos,
      int weightGrams)
  {
    this.animalRegNo = animalId;
    this.animalType = animalType;
    this.weightKilos = weightKilos;
    this.weightGrams = weightGrams;
  }

  public int getWeightKilos()
  {
    return weightKilos;
  }

  public int getWeightGrams()
  {
    return weightGrams;
  }

  public long getAnimalRegNo()
  {
    return animalRegNo;
  }

  public String getAnimalType()
  {
    return animalType;
  }
}
