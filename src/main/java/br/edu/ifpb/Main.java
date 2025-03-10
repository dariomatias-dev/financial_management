package br.edu.ifpb;

import br.edu.ifpb.database.DatabaseService;
import br.edu.ifpb.database.controllers.BudgetDatabaseController;
import br.edu.ifpb.database.controllers.BudgetItemDatabaseController;
import br.edu.ifpb.database.controllers.ExpenseDatabaseController;
import br.edu.ifpb.database.controllers.RevenueDatabaseController;
import br.edu.ifpb.views.main.MainView;

public class Main {
  public static void main(String[] args) {
    DatabaseService databaseService = new DatabaseService();
    // Inicialização dos controladores de banco de dados para cada tipo de entidade.
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
