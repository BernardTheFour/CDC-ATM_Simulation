package dao;

import java.io.File;
import java.io.FileNotFoundException;

import domains.Account;
import util.DataAccessObject;

public class AccountDao extends DataAccessObject {

    public AccountDao(File file) throws FileNotFoundException {
        super(file);
    }

    public Account getAccount(int row) {
        Object[] result = super.readRow(row);
        Account domain = new Account();

        domain.setAccountNumber(String.valueOf(result[0]));
        domain.setPin(String.valueOf(result[1]));
        domain.setName(String.valueOf(result[2]));
        domain.setBalance(Integer.valueOf(String.valueOf(result[3])));

        return domain;
    }
}
