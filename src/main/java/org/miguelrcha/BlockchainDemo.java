package org.miguelrcha;

import java.util.ArrayList;
import java.util.List;

public class BlockchainDemo {
    public static void main(String[] args) {
        //Demonstrate a series of blocks in a chain
        ArrayList<Block> blockChain = new ArrayList<Block>();

        //If someone tries to fraud a transaction, the hash code will be changed, consequently
        //showing that the code was tampered with

        //b1 hash=b72634bc3b5c7ade11ac68b8c9a6504488afe254c8fa4d12b91681315c5775af
        String[] initialValues = {"Pedro has $700", "Miguel has $550 for Claude Max Plan"};
        Block firstBlock = new Block(initialValues, "0");
        blockChain.add(firstBlock);
        System.out.println("First block is " + firstBlock.toString());
        System.out.println("The block chain is " + blockChain.toString());

        //b2 hash=a6ac98b1d75e4a58482e1646f1e7a4fc931716b61d28a2e846d7b2b483d9f7e5
        String[] secondValues = {"Pedro gives Lucas $60", "Miguel gives Marcos $100", "Pedro gives Terry $20"};
        Block secondBlock = new Block(secondValues, firstBlock.getHash());
        blockChain.add(secondBlock);
        System.out.println("Second block is " + secondBlock.toString());
        System.out.println("The block chain is " + blockChain.toString());

        //b3 hash=4f4e7a8855451622135211ce68d896754f66d7a8a2913272b77b410428ca6d13
        String[] thirdValues = {"Terry gives Lucas $60", "Marcos gives Pedro $90"};
        Block thirdBlock = new Block(thirdValues, secondBlock.getHash());
        blockChain.add(thirdBlock);
        System.out.println("Third block is " + thirdBlock.toString());
        System.out.println("The block chain is " + thirdBlock.toString());

        //Simulate valided block chain?
        //Chain bool isValid
        System.out.println("Valid? " + isChainValid(blockChain));   // true

        //Fraud attempt situation bool isValid
        secondBlock.setTransitions(new String[]{"Pedro gives Lucas $60"});
        System.out.println("Valid? " + isChainValid(blockChain));   // false
    }

    static boolean isChainValid(List<Block> chain) {
        for (int i = 0; i < chain.size(); i++) {
            Block current = chain.get(i);
            if (!current.isValid()) return false;
            if (i > 0 && !current.getPreviousHash().equals(chain.get(i - 1).getHash())) return false;
        }
        return true;
    }
}