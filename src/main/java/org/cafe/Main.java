package org.cafe;

import org.cafe.database.DatabaseService;
import org.cafe.database.controllers.BudgetController;
import org.cafe.database.controllers.BudgetItemController;
import org.cafe.database.controllers.ExpenseController;
import org.cafe.views.main.MainView;

public class Main {
  public static void main(String[] args) {
    DatabaseService databaseService = new DatabaseService();
    ExpenseController expenseController = new ExpenseController(databaseService);
    BudgetController budgetController = new BudgetController(databaseService);
    BudgetItemController budgetItemController = new BudgetItemController(databaseService);

    MainView mainView = new MainView(expenseController, budgetController, budgetItemController);

    mainView.setVisible(true);
  }
}
