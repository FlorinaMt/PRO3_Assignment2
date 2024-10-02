import com.google.rpc.Code;
import com.google.rpc.Status;
import io.grpc.protobuf.StatusProto;
import persistence.IPersistence;
import model.Animal;
import model.Product;

import java.util.ArrayList;

public class SlaughterhouseInfoRetrieverImpl
    extends SlaughterHouseInfoRetrieverGrpc.SlaughterHouseInfoRetrieverImplBase
{
  private IPersistence persistence;

  public SlaughterhouseInfoRetrieverImpl(IPersistence persistence)
  {
    this.persistence = persistence;
  }

  public void getAnimalsFromProductA(RequestA request,
      io.grpc.stub.StreamObserver<ResponseA> responseObserver)
  {
    try
    {
      ArrayList<Animal> animals = persistence.getAnimalsFromProduct(request.getProductId());

      ResponseA.Builder responseBuilder = ResponseA.newBuilder();

      for (Animal animal : animals)
        responseBuilder.addAnimal(AnimalModelGrpcConverter.ToProto(animal));

      ResponseA response = responseBuilder.build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
    catch(IllegalArgumentException e)
    {
      Status error = Status.newBuilder().setCode(Code.INVALID_ARGUMENT_VALUE).setMessage(e.getMessage()).build();
      responseObserver.onError(StatusProto.toStatusRuntimeException(error));
    }
  }

  @Override public void getProductsFromAnimalB(RequestB request,
      io.grpc.stub.StreamObserver<ResponseB> responseObserver)
  {
    ArrayList<Product> products = persistence.getProductsFromAnimal(
        request.getRegNo());

    ResponseB.Builder responseBuilder = ResponseB.newBuilder();

    for (Product product : products)
      responseBuilder.addProduct(ProductModelGrpcConverter.ToProto(product));

    ResponseB response = responseBuilder.build();

    responseObserver.onNext(response);
    responseObserver.onCompleted();
  }

}

