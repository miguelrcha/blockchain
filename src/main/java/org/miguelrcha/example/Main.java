package org.miguelrcha.example;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // hash blockchain
        String statment1 = "Hello World!";
        int hashValue = statment1.hashCode();

        System.out.println("Statment = " + statment1 + " whose hash value = " + hashValue);

        // hash arrays
        String [] list1 = {"miguel", "aula", "blockchain"};
        String [] list2 = {"miguel", "aula", "blockChain"};

        int hash1 = Arrays.hashCode(list1);
        int hash2 = Arrays.hashCode(list2);

        System.out.println("hash1 = " + hash1 + " hash2 = " + hash2);

    }

}
