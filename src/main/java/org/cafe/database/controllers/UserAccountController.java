package org.cafe.database.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InvalidPropertiesFormatException;
import org.cafe.database.DatabaseController;
import org.cafe.database.DatabaseService;
import org.cafe.models.user_accaount.UserAccountModel;

public class UserAccountController extends DatabaseController<UserAccountModel> {
  public UserAccountController(DatabaseService databaseService) {
    super(
            databaseService,
            "UserAccount",
            new String[]{"name", "telephone", "email", "cpf", "data_birth", "password"}
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
      userAccount.getPassword(),};

    super.insert(values);
  }

  public UserAccountModel get() {
    try {
      ArrayList<Object[]> results = super.findAll();
      if (results.isEmpty()) {
        throw new IllegalArgumentException("Usuario não encontrada.");
      }

      Object[] result = results.get(0);

      SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
      Date dataBirth = simpleDateFormat.parse(((String) result[5]));

      return new UserAccountModel(
              (String) result[0],
              (String) result[1],
              (double) result[2],
              (String) result[3],
              (double) result[4],
              dataBirth,
              (String) result[6]
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
      updatedAccount.getPassword(),};

    super.setById(id, values);
  }
}
