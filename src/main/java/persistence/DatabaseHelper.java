package persistence;

import org.postgresql.Driver;

import java.sql.*;

public class DatabaseHelper
{
  private final String jdbcURL;
  private final String username;
  private final String password;

  public DatabaseHelper(String jdbcURL, String username, String password)
      throws SQLException
  {
    this.jdbcURL = jdbcURL;
    this.username = username;
    this.password = password;
    DriverManager.registerDriver(new Driver());
  }

  public DatabaseHelper(String jdbcURL) throws SQLException
  {
    this(jdbcURL, null, null);
  }

  protected Connection getConnection() throws SQLException
  {
    if (username == null)
    {
      return DriverManager.getConnection(jdbcURL);
    }
    else
    {
      return DriverManager.getConnection(jdbcURL, username, password);
    }
  }

  private PreparedStatement prepare(Connection connection, String sql,
      Object[] parameters) throws SQLException
  {
    PreparedStatement stat = connection.prepareStatement(sql);
    for (int i = 0; i < parameters.length; i++)
    {
      stat.setObject(i + 1, parameters[i]);
    }
    return stat;
  }

  public int executeUpdate(String sql, Object... parameters) throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stat = prepare(connection, sql, parameters);
      return stat.executeUpdate();
    }
  }

  public ResultSet executeQuery(String sql, Object... parameters)
      throws SQLException
  {
    try (Connection connection = getConnection())
    {
      PreparedStatement stat = prepare(connection, sql, parameters);
      return stat.executeQuery(sql);
    }
  }
}
