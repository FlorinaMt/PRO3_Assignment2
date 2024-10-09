package grpc;

import proto.*;
public class ProductModelGrpcConverter
{
  public static Product ToProto(model.Product product)
  {
    return Product.newBuilder().setProductId(product.getProductId())
        .setProductName(product.getProductName())
        .setWeightKilo(product.getWeightKilo())
        .setWeightGrams(product.getWeightGrams())
        .setPackageDate(product.getPackageDate())
        .setExpirationDate(product.getExpirationDate()).build();
  }

  public static model.Product fromProto(Product protoProduct)
  {
    return new model.Product(protoProduct.getProductId(),
        protoProduct.getProductName(), protoProduct.getWeightKilo(),
        protoProduct.getWeightGrams(), protoProduct.getPackageDate(),
        protoProduct.getExpirationDate());
  }

}
