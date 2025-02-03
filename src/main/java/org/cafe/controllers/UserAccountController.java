package org.cafe.controllers;

import org.cafe.database.DatabaseController;
import org.cafe.database.DatabaseService;
import org.cafe.models.userAccount.UserAccountModel;

import java.util.ArrayList;

public class UserAccountController extends DatabaseController<UserAccountModel> {
    public UserAccountController(DatabaseService databaseService) {
        super(
            databaseService,
            "user_account",
            new String[]{ "name", "telephone", "email", "cpf", "data_birth", "password" }
        );
    }

    public void create(
            UserAccountModel userAccount
    ) {
        Object[] values = {
            userAccount.getName(),
            userAccount.getTelephone(),
            userAccount.getEmail(),
            userAccount.getCpf(),
            userAccount.getDataBirth(),
            userAccount.getPassword(),
        };

        super.insert(values);
    }

    public UserAccountModel get() {
        ArrayList<Object[]> results = super.findAll();
        if (results.isEmpty()) {
            throw new IllegalArgumentException("Usuario não encontrada.");
        }

        Object[] row = results.getFirst();

        return new UserAccountModel(
            (String) row[0],
            (double) row[1],
            (String) row[2],
            (double) row[3],
            (double) row[4],
            (String) row[5]
        );
    }

    public void update(
            String id,
            UserAccountModel updatedAccount
    ) {
        Object[] values = {
            updatedAccount.getName(),
            updatedAccount.getTelephone(),
            updatedAccount.getEmail(),
            updatedAccount.getCpf(),
            updatedAccount.getDataBirth(),
            updatedAccount.getPassword(),
        };

        super.setById(id, values);
    }
}