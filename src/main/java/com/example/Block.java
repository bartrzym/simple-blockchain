package com.example;

import com.example.transactions.Transaction;
import com.example.transactions.TransactionOutput;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;


public class Block {


    public String hash;
    public String previousHash;
    public ArrayList<Transaction> transactions = new ArrayList<>();
    private long timeStamp;
    private int nonce;

    public Block(String previousHash ) {
        this.previousHash = previousHash;
        this.timeStamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return StringUtil.applySha256(
                previousHash +
                        timeStamp +
                        transactions+
                        nonce
        );
    }

    public void mineBlock(int difficulty) {
        String target = StringUtil.getDificultyString(difficulty);
        while(!hash.substring( 0, difficulty).equals(target)) {
            nonce ++;
            hash = calculateHash();
        }
        System.out.println("Block Mined!!! : " + hash);
    }

    public void addTransaction(Transaction transaction, HashMap<String, TransactionOutput> map) {
        if(transaction == null) return;
        if((!previousHash.equals("0"))) {
            if((!transaction.processTransaction(map))) {
                System.out.println("Transaction failed to process. Discarded.");
                return;
            }
        }
        System.out.println("\n"+transaction.sender.toString().substring(40,194)+"\nis Attempting to send funds ("+transaction.value+") to "+"\n"+transaction.receiver.toString().substring(40,194)+"\n...");
        transactions.add(transaction);
        System.out.println("Transaction Successfully added to Block");
    }

}

