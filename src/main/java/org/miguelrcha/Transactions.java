package org.miguelrcha;

import org.miguelrcha.account.Account;

public class Transactions {

    private static int nextId = 1;

    private int id;

    // who sends the money
    private Account who;

    // who receives the money
    private Account to;

    private double transition;

    private long timestamp;

    // transient: Gson would loop forever serializing Block -> Transactions -> Block
    private transient Block block;

    public Transactions(Account who, Account to, double transition) {
        if (transition <= 0) {
            throw new IllegalArgumentException("O valor da transação deve ser positivo: " + transition);
        }
        this.id = nextId++;
        this.who = who;
        this.to = to;
        this.transition = transition;
        this.timestamp = System.currentTimeMillis();
    }

    public Transactions() {

    }

    // Moves the money between the accounts; fails if the sender does not have enough balance
    public void execute() {
        if (who.getLastValue() < transition) {
            throw new IllegalStateException(who.getName() + " não tem saldo suficiente para $" + transition);
        }
        who.setLastValue(who.getLastValue() - transition);
        to.setLastValue(to.getLastValue() + transition);
    }

    public int getId() {
        return id;
    }

    public Block getBlock() {
        return block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public Account getWho() {
        return who;
    }

    public void setWho(Account who) {
        this.who = who;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    public double getTransition() {
        return transition;
    }

    public void setTransition(double transition) {
        this.transition = transition;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "#" + id + " " + who.getName() + " envia para " + to.getName() + " $" + transition;
    }
}
