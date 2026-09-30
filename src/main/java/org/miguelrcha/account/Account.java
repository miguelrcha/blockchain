package org.miguelrcha.account;

public class Account {

    public static void printAccounts() {
        for (Account account : AccountManager.getAccounts()) {
            System.out.println(account);
        }
    }

    private String name;

    // transient: the balance changes over time, so it must not be part of the block hash
    private transient double lastValue;

    public Account(String name, double initialValue) {
        this.name = name;
        this.lastValue = initialValue;
    }

    public Account() {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLastValue() {
        return lastValue;
    }

    public void setLastValue(double lastValue) {
        this.lastValue = lastValue;
    }

    @Override
    public String toString() {
        return name + ": $" + lastValue;
    }
}
