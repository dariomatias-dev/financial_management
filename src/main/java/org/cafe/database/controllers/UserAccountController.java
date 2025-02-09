package org.cafe.database.controllers;

import org.cafe.database.DatabaseController;
import org.cafe.database.DatabaseService;
import org.cafe.models.UserAccountModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;

public class UserAccountController extends DatabaseController<UserAccountModel> {
    public UserAccountController(DatabaseService databaseService) {
        super(
            databaseService,
            "UserAccount",
            new String[]{ "name", "telephone", "email", "cpf", "data_birth", "password" }
        );
    }

    public void create(
            UserAccountModel userAccount
    ) throws InvalidPropertiesFormatException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        String dateFormatted = simpleDateFormat.format(userAccount.getDataBirth());

        Object[] values = {
                userAccount.getName(),
                userAccount.getTelephone(),
                userAccount.getEmail(),
                userAccount.getCpf(),
                dateFormatted,
                userAccount.getPassword(),
        };

        super.insert(values);
    }

    public UserAccountModel get() {
        try {
            ArrayList<Object[]> results = super.findAll();
            if (results.isEmpty()) {
                throw new IllegalArgumentException("Usuario não encontrada.");
            }

            Object[] row = results.getFirst();

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
            Date dataBirth = simpleDateFormat.parse(((String) row[5]));

            return new UserAccountModel(
                    (String) row[0],
                    (String) row[1],
                    (double) row[2],
                    (String) row[3],
                    (double) row[4],
                    dataBirth,
                    (String) row[6]
            );
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
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