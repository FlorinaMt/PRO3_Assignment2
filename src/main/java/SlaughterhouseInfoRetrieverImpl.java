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
  private final SlaughterhouseBase base;

  public SlaughterhouseInfoRetrieverImpl(SlaughterhouseBase base)
  {
    this.base=base;
  }

  public void getAnimalsFromProductA(RequestA request,
      io.grpc.stub.StreamObserver<ResponseA> responseObserver)
  {
    try
    {
      ArrayList<Animal> animals = base.getAnimalsFromProductA(request.getProductId());

      ResponseA.Builder responseBuilder = ResponseA.newBuilder();

      for (Animal animal : animals)
        responseBuilder.addAnimal(AnimalModelGrpcConverter.ToProto(animal));

      ResponseA response = responseBuilder.build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
    catch(Exception e)
    {
      Status error = Status.newBuilder().setCode(Code.INVALID_ARGUMENT_VALUE).setMessage(e.getMessage()).build();
      responseObserver.onError(StatusProto.toStatusRuntimeException(error));
    }
  }

  @Override public void getProductsFromAnimalB(RequestB request,
      io.grpc.stub.StreamObserver<ResponseB> responseObserver)
  {
    try
    {
      ArrayList<Product> products = base.getProductsFromAnimalB(request.getRegNo());

      ResponseB.Builder responseBuilder = ResponseB.newBuilder();

      for (Product product : products)
        responseBuilder.addProduct(ProductModelGrpcConverter.ToProto(product));

      ResponseB response = responseBuilder.build();

      responseObserver.onNext(response);
      responseObserver.onCompleted();
    }
    catch(Exception e)
    {
      Status error = Status.newBuilder().setCode(Code.INVALID_ARGUMENT_VALUE).setMessage(e.getMessage()).build();
      responseObserver.onError(StatusProto.toStatusRuntimeException(error));
    }

  }

}


