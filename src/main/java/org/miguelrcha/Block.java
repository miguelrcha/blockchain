package org.miguelrcha;

import com.google.gson.Gson;
import org.miguelrcha.hashing.SHA256;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import java.util.Arrays;

public class Block {

    public static final Gson blockAdaptedGson = new Gson();
    private String[] transitions;
    private long timestamp;
    private transient String hash;
    private String previousHash;
    private boolean isValid = false;

    // Data FMT America/Sao_Paulo
    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS")
                    .withZone(ZoneId.of("America/Sao_Paulo"));

    @Override
    public String toString() {
        return
                "transitions = " + Arrays.toString(transitions) +
                ", hash = " + hash +
                ", previousHash = " + previousHash +
                        ", timestamp = " + FMT.format(Instant.ofEpochMilli(timestamp)) +
                ", isValid = " + isValid;
    }

    public Block(String[] transitions, String previousHash) {
        super();
        this.transitions = transitions;
        this.previousHash = previousHash;
        this.timestamp = System.currentTimeMillis();
        this.hash = SHA256.encode(this);
        //this.hash = Arrays.hashCode(new int[] {Arrays.hashCode(transitions), this.previousHash});
    }

    public String[] getTransitions() {
        return transitions;
    }

    public void setTransitions(String[] transitions) {
        this.transitions = transitions;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getHash() {
        return hash;
    }

    public String calculateHash() {
        return SHA256.encode(this);
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

    public boolean isValid() {
        return hash.equals(SHA256.encode(this));
    }

    public void setisValid(boolean valid) {
        isValid = valid;
    }
}
