package org.cafe;

import org.cafe.database.DatabaseService;
import org.cafe.database.controllers.BudgetDatabaseController;
import org.cafe.database.controllers.BudgetItemDatabaseController;
import org.cafe.database.controllers.ExpenseDatabaseController;
import org.cafe.database.controllers.RevenueDatabaseController;
import org.cafe.views.main.MainView;

public class Main {
  public static void main(String[] args) {
    DatabaseService databaseService = new DatabaseService();
    ExpenseDatabaseController expenseDatabaseController = new ExpenseDatabaseController(databaseService);
    RevenueDatabaseController revenueDatabaseController = new RevenueDatabaseController(databaseService);
    BudgetDatabaseController budgetDatabaseController = new BudgetDatabaseController(databaseService);
    BudgetItemDatabaseController budgetItemDatabaseController = new BudgetItemDatabaseController(databaseService);

    MainView mainView = new MainView(
            expenseDatabaseController,
            revenueDatabaseController,
            budgetDatabaseController,
            budgetItemDatabaseController
    );

    mainView.setVisible(true);
  }
}
