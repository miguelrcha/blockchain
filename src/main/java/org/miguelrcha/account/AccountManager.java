package org.miguelrcha.account;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AccountManager {
    private static final ArrayList<Account> ACCOUNTS = new ArrayList<Account>();

    public static Account createAccount(String name, double initialValue) {
        if (findAccount(name) != null) {
            throw new IllegalArgumentException("A conta já existe: " + name);
        }
        Account account = new Account(name, initialValue);
        ACCOUNTS.add(account);
        return account;
    }

    public static Account findAccount(String name) {
        for (Account account : ACCOUNTS) {
            if (account.getName().equals(name)) {
                return account;
            }
        }
        return null;
    }

    public static List<Account> getAccounts() {
        return Collections.unmodifiableList(ACCOUNTS);
    }
}
