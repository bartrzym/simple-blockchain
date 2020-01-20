package com.example.transactions;

import com.example.Wallet;
import java.util.HashMap;

public class AncestorTransaction extends Transaction {
    private Transaction ancestorTransaction;
    private Wallet ancestorWallet = new Wallet();


    public AncestorTransaction() {
        super();
    }

    public Transaction init(Wallet receiver, float value, HashMap map) {
        ancestorTransaction = new Transaction(ancestorWallet.publicKey, receiver.publicKey, value, null);
        ancestorTransaction.generateSignature(ancestorWallet.privateKey);
        ancestorTransaction.transactionId = "0";
        ancestorTransaction.outputs.add(new TransactionOutput(ancestorTransaction.receiver, ancestorTransaction.value, ancestorTransaction.transactionId));
        map.put(ancestorTransaction.outputs.get(0).id, ancestorTransaction.outputs.get(0));

        return ancestorTransaction;
    }

}
