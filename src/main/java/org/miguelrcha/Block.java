package org.miguelrcha;

import com.google.gson.Gson;
import org.miguelrcha.hashing.SHA256;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.List;

public class Block {

    private static ArrayList<Block> BLOCKS = new ArrayList<>();

    public static final Gson blockAdaptedGson = new Gson();

    private static int nextId = 1;

    private int id;
    private List<Transactions> transitions;
    private long timestamp;
    private transient String hash;
    private String previousHash;
    private boolean isValid = false;

    // Data FMT America/Sao_Paulo
    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss.SSS")
                    .withZone(ZoneId.of("America/Sao_Paulo"));

    public static void printAllBlocks() {
        for(Block b : BLOCKS) {
            b.print();
            Report.log("");
            Report.log("--------------");

        }
    }

    // Checks every block from the last to the first: its own hash and the link to the previous block
    public static boolean validate() {

        Report.log("");
        Report.log("--------------");
        Report.log("INICIANDO VALIDAÇÃO (" + BLOCKS.size() + " blocos)");
        Report.log("--------------");
        int lastIndex = BLOCKS.size();
        int currentIndex = lastIndex;

        boolean success = true;
        Block b = null;

        while(currentIndex > 0) {
            --currentIndex;
            b = BLOCKS.get(currentIndex);
            System.out.print("\nValidando bloco " + b.getId() + ": ");
            boolean valid = b.hash.equals(SHA256.encode(b));
            if(valid && currentIndex > 0) {
                valid = b.previousHash.equals(BLOCKS.get(currentIndex - 1).hash);
            }
            System.out.print(valid);

            if(!valid) {
                success = false;
                break;
            }
        }

        Report.log("");

        if(success) {
            Report.log("------------------");
            Report.log("VALIDAÇÃO CONCLUÍDA COM SUCESSO!");
            Report.log("------------------");
            Report.log("");
        }else {
            Report.log("------------------");
            Report.log("VALIDAÇÃO FALHOU NO BLOCO " + b.getId());
            Report.log("------------------");
        }
        return success;
    }

    private void print() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return
                "transitions = " + transitions +
                "| hash = " + hash +
                "| previousHash = " + previousHash + "| data = " + FMT.format(Instant.ofEpochMilli(timestamp)) +
                "| isValid = " + isValid();
    }

    public Block(List<Transactions> transitions, String previousHash) {
        super();
        this.id = nextId++;
        this.transitions = transitions;
        this.previousHash = previousHash;
        this.timestamp = System.currentTimeMillis();
        this.hash = SHA256.encode(this);
        BLOCKS.add(this);
        //this.hash = Arrays.hashCode(new int[] {Arrays.hashCode(transitions), this.previousHash});
    }

    public int getId() {
        return id;
    }

    public List<Transactions> getTransitions() {
        return transitions;
    }

    public void setTransitions(List<Transactions> transitions) {
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
