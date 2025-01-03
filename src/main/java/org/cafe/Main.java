package org.cafe;

import org.cafe.controllers.ExpenseController;
import org.cafe.database.DatabaseService;

public class Main {
  public static void main(String[] args) {
    new Init();
  }
}

class Init {
  Init() {
    run();
  }

  void run() {
    DatabaseService databaseService = new DatabaseService();

    new ExpenseController(databaseService);
  }
}
