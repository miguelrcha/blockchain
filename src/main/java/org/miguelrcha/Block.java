package org.miguelrcha;

import com.google.gson.Gson;
import org.miguelrcha.hashing.SHA256;

import java.util.Arrays;

public class Block {

    public static final Gson blockAdaptedGson = new Gson();
    private String[] transitions;
    private transient String hash;
    private String previousHash;
    private boolean isGenesis = false;

    @Override
    public String toString() {
        return
                "transitions = " + Arrays.toString(transitions) +
                ", hash = " + hash +
                ", previousHash = " + previousHash +
                ", isGenesis = " + isGenesis;
    }

    public Block(String[] transitions, String previousHash) {
        super();
        this.transitions = transitions;
        this.previousHash = previousHash;
        this.hash = SHA256.encode(this);
        //this.hash = Arrays.hashCode(new int[] {Arrays.hashCode(transitions), this.previousHash});
    }

    public String[] getTransitions() {
        return transitions;
    }

    public void setTransitions(String[] transitions) {
        this.transitions = transitions;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public boolean isGenesis() {
        return isGenesis;
    }

    public void setGenesis(boolean genesis) {
        isGenesis = genesis;
    }
}
