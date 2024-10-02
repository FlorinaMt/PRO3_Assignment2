import persistence.DatabaseHelper;
import persistence.DatabasePersistence;
import persistence.IPersistence;
import io.grpc.ServerBuilder;

import java.io.IOException;
import java.sql.SQLException;

public class Server
{
  public static void main(String[] args)
      throws SQLException, IOException, InterruptedException
  {
    DatabaseHelper helper = new DatabaseHelper(
        "jdbc:postgresql://localhost:5432/slaughterhouse?currentSchema=slaughterhouse_schema",
        "postgres", "344692StupidPass");

    IPersistence db = new DatabasePersistence(helper);

    SlaughterHouseInfoRetrieverGrpc.SlaughterHouseInfoRetrieverImplBase grpc = new SlaughterhouseInfoRetrieverImpl(db);

    io.grpc.Server server = ServerBuilder.forPort(9090).addService(grpc)
        .build();
    server.start();
    System.out.println("Server running");

    server.awaitTermination();
  }
}

