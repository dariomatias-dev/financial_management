package org.cafe.database;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseManager {
  public Connection connection;
  String dbPath;

  public DatabaseManager() {
    Dotenv dotEnv = Dotenv.load();

    this.dbPath = dotEnv.get("DB_PATH");

    connect();
  }

  private void connect() {
    try {
      connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);

      if (connection != null) {
        System.out.println("Connected Successfully.");

        createTasksTable();
      } else {
        System.out.println("Connection Failure.");
      }
    } catch (SQLException e) {
      System.err.println("Error connecting to SQLite: " + e.getMessage());
    }
  }

  void createTasksTable() {
    String schemaTablesSql = """
      CREATE TABLE IF NOT EXISTS UserAccount (
          id VARCHAR(36) PRIMARY KEY,
          name TEXT NOT NULL,
          telephone REAL NOT NULL,
          email TEXT NOT NULL UNIQUE,
          cpf REAL NOT NULL UNIQUE,
          dataBirth TEXT NOT NULL,
          password TEXT NOT NULL
      );
              
      CREATE TABLE IF NOT EXISTS Expenses (
        id VARCHAR(36) PRIMARY KEY,
        name TEXT NOT NULL,
        value REAL NOT NULL,
        period TEXT NOT NULL,
        description TEXT
      );
      
      CREATE TABLE IF NOT EXISTS Revenues (
          id VARCHAR(36) PRIMARY KEY,
          name TEXT NOT NULL,
          gross_value REAL NOT NULL,
          net_value REAL NOT NULL,
          period TEXT NOT NULL,
          description TEXT,
          revenue_type TEXT NOT NULL
      );
    """;

    try {
      Statement statement = connection.createStatement();
      statement.execute(schemaTablesSql);
    } catch (SQLException e) {
      throw new RuntimeException("Error creating tasks table: " + e.getMessage(), e);
    }
  }
}
