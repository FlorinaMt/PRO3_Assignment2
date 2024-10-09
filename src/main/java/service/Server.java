package service;

import grpc.SlaughterhouseBase;
import grpc.SlaughterhouseInfoRetrieverImpl;
import persistence.DatabaseHelper;
import persistence.IPersistence;
import io.grpc.ServerBuilder;
import persistence.SlaughterhouseDao;
import proto.SlaughterhouseInfoRetrieverGrpc;

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

    IPersistence dao = new SlaughterhouseDao(helper);

    SlaughterhouseBase base = new SlaughterhouseBase(dao);

    SlaughterhouseInfoRetrieverGrpc.SlaughterhouseInfoRetrieverImplBase grpc = new SlaughterhouseInfoRetrieverImpl(base);

    io.grpc.Server server = ServerBuilder.forPort(9090).addService(grpc).build();
    server.start();
    System.out.println("Server running");

    server.awaitTermination();
  }
}

