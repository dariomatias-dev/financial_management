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

        Statement stmt = connection.createStatement();
        stmt.execute("PRAGMA foreign_keys = ON;");

        createTasksTable();
      } else {
        System.out.println("Connection Failure.");
      }
    } catch (SQLException e) {
      System.err.println("Error connecting to SQLite: " + e.getMessage());
    }
  }

  void createTasksTable() {
    String createUserAccountTable = """
      CREATE TABLE IF NOT EXISTS UserAccount (
          id VARCHAR(36) PRIMARY KEY,
          name TEXT NOT NULL,
          telephone REAL NOT NULL,
          email TEXT NOT NULL UNIQUE,
          cpf REAL NOT NULL UNIQUE,
          dataBirth TEXT NOT NULL,
          password TEXT NOT NULL
      );
    """;

    String createExpensesTable = """
      CREATE TABLE IF NOT EXISTS Expenses (
        id VARCHAR(36) PRIMARY KEY,
        name TEXT NOT NULL,
        value REAL NOT NULL,
        period TEXT NOT NULL,
        description TEXT
      );
    """;

    String createRevenuesTable = """
      CREATE TABLE IF NOT EXISTS Revenues (
          id VARCHAR(36) PRIMARY KEY,
          name TEXT NOT NULL,
          description TEXT NOT NULL,
          value REAL NOT NULL,
          period TEXT NOT NULL
      );
    """;

    String createBudgetsTable = """
      CREATE TABLE IF NOT EXISTS Budgets (
          id VARCHAR(36) PRIMARY KEY,
          name TEXT NOT NULL,
          description TEXT NOT NULL,
          category TEXT NOT NULL,
          status TEXT NOT NULL,
          total_budget_value REAL NOT NULL,
          total_spent REAL NOT NULL,
          initial_date TEXT NOT NULL,
          end_date TEXT NOT NULL
      );
    """;

    String createBudgetItemsTable = """
      CREATE TABLE IF NOT EXISTS BudgetItems (
          id VARCHAR(36) PRIMARY KEY,
          budget_id VARCHAR(36) NOT NULL,
          name TEXT NOT NULL,
          description TEXT,
          value REAL NOT NULL,
          period TEXT NOT NULL,
          created_at TEXT NOT NULL,
          FOREIGN KEY (budget_id) REFERENCES Budgets(id) ON DELETE CASCADE
      );
    """;

    try {
      Statement statement = connection.createStatement();
      statement.execute(createUserAccountTable);
      statement.execute(createExpensesTable);
      statement.execute(createRevenuesTable);
      statement.execute(createBudgetsTable);
      statement.execute(createBudgetItemsTable);
    } catch (SQLException e) {
      throw new RuntimeException("Error creating tables: " + e.getMessage(), e);
    }
  }
}
