package grpc;

import model.Animal;
import model.Product;
import persistence.IPersistence;
import proto.SlaughterhouseInfoRetrieverGrpc;

import java.util.ArrayList;

public class SlaughterhouseBase
    extends SlaughterhouseInfoRetrieverGrpc.SlaughterhouseInfoRetrieverImplBase
{
  private final IPersistence persistence;

  public SlaughterhouseBase(IPersistence persistence)
  {
    this.persistence = persistence;
  }

  public ArrayList<Animal> getAnimalsFromProductA(long productId)
  {
    return persistence.getAnimalsFromProduct(productId);
  }

  public ArrayList<Product> getProductsFromAnimalB(long animalRegNo)
  {
    return persistence.getProductsFromAnimal(animalRegNo);
  }
}