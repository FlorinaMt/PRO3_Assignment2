import model.Animal;
import model.Product;

import java.util.ArrayList;

public interface IPersistence
{
    ArrayList<Animal> getAnimalsFromProduct(long productId) throws IllegalArgumentException;
    ArrayList<Product> getProductsFromAnimal(long animalId) throws IllegalArgumentException;
}
