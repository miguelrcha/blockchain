package org.miguelrcha;

import org.miguelrcha.account.Account;
import org.miguelrcha.account.AccountManager;

import java.util.ArrayList;
import java.util.List;

public class BlockchainDemo {
    public static void main(String[] args) {
        //Initial balances
        Account pedro = AccountManager.createAccount("Pedro", 700);
        Account miguel = AccountManager.createAccount("Miguel", 550);
        Account lucas = AccountManager.createAccount("Lucas", 0);
        Account marcos = AccountManager.createAccount("Marcos", 0);
        Account terry = AccountManager.createAccount("Terry", 0);

        //If someone tries to fraud a transaction, the hash code will be changed, consequently
        //showing that the code was tampered with

        //Genesis block: no transactions, previous hash "0"
        Block firstBlock = new Block(new ArrayList<>(), "0");
        System.out.println("First block: " + firstBlock);

        Block secondBlock = new Block(execute(
                new Transactions(pedro, lucas, 60),
                new Transactions(miguel, marcos, 100),
                new Transactions(pedro, terry, 20)), firstBlock.getHash());
        System.out.println("Second block: " + secondBlock);

        Block thirdBlock = new Block(execute(
                new Transactions(terry, lucas, 60),
                new Transactions(marcos, pedro, 90)), secondBlock.getHash());
        System.out.println("Third block: " + thirdBlock);

        //Chain bool isValid
        Block.validate();   // true

        //Fraud attempt situation: change the value of an already recorded transaction
        secondBlock.getTransitions().get(0).setTransition(6000);
        Block.validate();   // false
    }

    //Executes every transaction (moving the balances) and returns the accepted ones as a block's list;
    //transactions without enough balance are rejected and left out of the block
    static List<Transactions> execute(Transactions... transactions) {
        List<Transactions> list = new ArrayList<>();
        for (Transactions transaction : transactions) {
            try {
                transaction.execute();
                list.add(transaction);
            } catch (IllegalStateException e) {
                System.out.println("Invalid: " + e.getMessage());
            }
        }
        return list;
    }
}
