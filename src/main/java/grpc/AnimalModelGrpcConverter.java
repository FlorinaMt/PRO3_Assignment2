package grpc;

import proto.*;

public class AnimalModelGrpcConverter
{
  public static Animal ToProto(model.Animal animal)
  {
    return Animal.newBuilder().setAnimalRegNo(animal.getAnimalRegNo())
        .setAnimalType(animal.getAnimalType())
        .setWeightKilos(animal.getWeightKilos())
        .setWeightGrams(animal.getWeightGrams()).build();
  }

  public static model.Animal fromProto(Animal protoAnimal)
  {
    return new model.Animal(protoAnimal.getAnimalRegNo(),
        protoAnimal.getAnimalType(), protoAnimal.getWeightKilos(), protoAnimal.getWeightGrams());
  }
}
