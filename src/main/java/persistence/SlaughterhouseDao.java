package persistence;

import model.Animal;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SlaughterhouseDao implements IPersistence
{
  private final DatabaseHelper helper;

  public SlaughterhouseDao(DatabaseHelper helper)
  {
    this.helper = helper;
  }

  @Override public ArrayList<Animal> getAnimalsFromProduct(long productId)
      throws IllegalArgumentException
  {
    String sql =
        "SELECT animalRegNo, animalType, animalWeight\n" + "FROM animal\n"
            + "         JOIN animal_part on animal.animalRegNo = animal_part.originAnimalRegNo\n"
            + "         JOIN tray on animal_part.trayId = tray.trayId\n"
            + "         JOIN mixed_product_tray on tray.trayId = mixed_product_tray.trayId\n"
            + "         JOIN mixed_product on mixed_product_tray.productId = mixed_product.productId\n"
            + "WHERE mixed_product.productId = ?\n" + "UNION\n"
            + "SELECT animalRegNo, animalType, animalWeight\n" + "FROM animal\n"
            + "         JOIN animal_part on animal.animalRegNo = animal_part.originAnimalRegNo\n"
            + "         JOIN tray on animal_part.trayId = tray.trayId\n"
            + "         JOIN same_part_product_tray on tray.trayId = same_part_product_tray.trayId\n"
            + "         JOIN same_part_product on same_part_product_tray.productId = same_part_product.productId\n"
            + "WHERE same_part_product.productId = ?;";

    try (Connection connection = helper.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql))
    {
      ps.setLong(1, productId);
      ps.setLong(2, productId);

      try (ResultSet rs = ps.executeQuery())
      {
        ArrayList<Animal> animals = new ArrayList<>();

        while (rs.next())
        {
          long regNo = rs.getLong("animalRegNo");
          String type = rs.getString("animalType");
          float weight = rs.getFloat("animalWeight");
          int weightKilos = (int) weight;
          int weightGrams = Math.round((weight - weightKilos) * 1000);

          Animal animal = new Animal(regNo, type, weightKilos, weightGrams);
          animals.add(animal);
        }
        return animals;
      }

    }
    catch (SQLException e)
    {
      throw new IllegalArgumentException(e);
    }
  }

  @Override public ArrayList<Product> getProductsFromAnimal(long animalRegNo)
      throws IllegalArgumentException
  {
    String sql =
        "SELECT mixed_product.productId, mixed_product.productName, mixed_product.weight, mixed_product.packageDate, mixed_product.expirationDate\n"
            + "FROM mixed_product\n"
            + "         JOIN mixed_product_tray on mixed_product.productId = mixed_product_tray.productId\n"
            + "         JOIN tray on mixed_product_tray.trayId = tray.trayId\n"
            + "         JOIN animal_part on tray.trayId = animal_part.trayId\n"
            + "         JOIN animal on animal_part.originAnimalRegNo = animal.animalRegNo\n"
            + "WHERE animalRegNo = ?\n" + "UNION\n"
            + "SELECT same_part_product.productId, same_part_product.partType, same_part_product.weight, same_part_product.packageDate, same_part_product.expirationDate\n"
            + "FROM same_part_product\n"
            + "         JOIN same_part_product_tray on same_part_product.productId = same_part_product_tray.productId\n"
            + "         JOIN tray on same_part_product_tray.trayId = tray.trayId\n"
            + "         JOIN animal_part on tray.trayId = animal_part.trayId\n"
            + "         JOIN animal on animal_part.originAnimalRegNo = animal.animalRegNo\n"
            + "WHERE animalRegNo = ?;";
    try (Connection connection = helper.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql))
    {
      ps.setLong(1, animalRegNo);
      ps.setLong(2, animalRegNo);

      try (ResultSet rs = ps.executeQuery())
      {
        ArrayList<Product> products = new ArrayList<>();

        while (rs.next())
        {

          long id = rs.getLong("productId");
          String name = rs.getString("productName");
          float weight = rs.getFloat("weight");
          int weightKilos = (int) weight;
          int weightGrams = Math.round((weight - weightKilos) * 1000);
          String packageDate = rs.getString("packageDate");
          String expirationDate = rs.getString("expirationDate");

          Product product = new Product(id, name, weightKilos, weightGrams,
              packageDate, expirationDate);
          products.add(product);
        }
        return products;
      }
      catch (SQLException e)
      {
        throw new IllegalArgumentException(e);
      }
    }
    catch (SQLException e)
    {
      throw new IllegalArgumentException(e);
    }

  }
}

