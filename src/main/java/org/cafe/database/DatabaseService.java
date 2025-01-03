package org.cafe.database;

import org.cafe.DatabaseManager;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.UUID;

public class DatabaseService {
  private final DatabaseManager databaseManager;

  public DatabaseService() {
    this.databaseManager = new DatabaseManager();
  }

  // CREATE
  public void create(String query, Object... params) {
    Object[] newParams = new Object[params.length + 1];
    newParams[0] = UUID.randomUUID();
    System.arraycopy(params, 0, newParams, 1, params.length);

    try (PreparedStatement statement = databaseManager.connection.prepareStatement(query)) {
      setParameters(statement, newParams);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao executar INSERT.", e);
    }
  }

  // READ
  public ArrayList<Object[]> read(String query, Object... params) {
    ArrayList<Object[]> results = new ArrayList<>();
    try (PreparedStatement statement = databaseManager.connection.prepareStatement(query)) {
      setParameters(statement, params);

      ResultSet rs = statement.executeQuery();
      while (rs.next()) {
        int columnCount = rs.getMetaData().getColumnCount();
        Object[] rows = new Object[columnCount];
        for (int i = 0; i < columnCount; i++) {
          rows[i] = rs.getObject(i + 1);
        }
        results.add(rows);
      }
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao executar SELECT.", e);
    }

    return results;
  }

  // UPDATE
  public void update(String query, Object... params) {
    try (PreparedStatement statement = databaseManager.connection.prepareStatement(query)) {
      setParameters(statement, params);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao executar UPDATE.", e);
    }
  }

  // DELETE
  public void delete(String query, Object... params) {
    try (PreparedStatement statement = databaseManager.connection.prepareStatement(query)) {
      setParameters(statement, params);
      statement.executeUpdate();
    } catch (SQLException e) {
      throw new RuntimeException("Erro ao executar DELETE.", e);
    }
  }

  // METHODS
  private void setParameters(PreparedStatement statement, Object[] params) throws SQLException {
    for (int i = 0; i < params.length; i++) {
      statement.setObject(i + 1, params[i]);
    }
  }
}
