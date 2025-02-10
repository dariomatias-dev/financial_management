package org.cafe.models.user_accaount;

import java.util.Date;

public class UserAccountModel extends CreateUserAccountModel {
    private String id;

    public UserAccountModel(
            String id,
            String name,
            double telephone,
            String email,
            double cpf,
            Date dataBirth,
            String password
    ) {
        super(name, telephone, email, cpf, dataBirth, password);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
