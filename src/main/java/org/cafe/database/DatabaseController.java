package org.cafe.database;

import java.util.ArrayList;

public abstract class DatabaseController<T> {
  protected final DatabaseService databaseService;
  protected final String tableName;
  protected final String[] columns;

  public DatabaseController(
    DatabaseService databaseService,
    String tableName,
    String[] columns
  ) {
    this.databaseService = databaseService;
    this.tableName = tableName;
    this.columns = columns;
  }

  // CREATE
  public void insert(Object... values) {
    databaseService.create(buildInsertQuery(), values);
  }

  // READ
  public ArrayList<Object[]> findById(String id) {
    String query = "SELECT * FROM " + tableName + " WHERE id = ?";

    return databaseService.read(query, id);
  }

  public ArrayList<Object[]> findAll() {
    String query = "SELECT * FROM " + tableName;

    return databaseService.read(query);
  }

  // UPDATE
  public void setById(String id, Object... values) {
    Object[] newParams = new Object[values.length + 1];
    System.arraycopy(values, 0, newParams, 0, values.length);
    newParams[values.length] = id;

    databaseService.update(buildUpdateQuery(), newParams);
  }

  // DELETE
  public void removeById(String id) {
    String query = "DELETE FROM " + tableName + " WHERE id = ?";

    databaseService.delete(query, id);
  }

  public void removeAll() {
    String query = "DELETE FROM " + tableName;

    databaseService.delete(query);
  }

  // METHODS
  private String buildInsertQuery() {
    StringBuilder query = new StringBuilder("INSERT INTO " + tableName + " (id, ");
    for (String column : columns) {
      query.append(column).append(", ");
    }

    query.setLength(query.length() - 2);
    query.append(") VALUES (?, ");
    for (int i = 0; i < columns.length; i++) {
      query.append("?, ");
    }

    query.setLength(query.length() - 2);
    query.append(")");

    return query.toString();
  }

  private String buildUpdateQuery() {
    StringBuilder query = new StringBuilder("UPDATE " + tableName + " SET ");

    for (String column : columns) {
      query.append(column).append(" = ?, ");
    }

    query.setLength(query.length() - 2);
    query.append(" WHERE id = ?");

    return query.toString();
  }
}
