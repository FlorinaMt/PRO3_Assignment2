import com.google.rpc.Code;
import com.google.rpc.Status;
import io.grpc.protobuf.StatusProto;
import model.Animal;
import model.Product;
import persistence.IPersistence;

import java.util.ArrayList;

public class SlaughterhouseBase
    extends SlaughterHouseInfoRetrieverGrpc.SlaughterHouseInfoRetrieverImplBase
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