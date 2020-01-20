package com.example;

import com.example.validation.Validation;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.Base64;

class SigningTest {


    private Wallet wallet1 = new Wallet();
    private Wallet wallet2 = new Wallet();


    @Test
    void confirmingSignedData(){

        String data1 = Base64.getEncoder().encodeToString(wallet1.publicKey.getEncoded()) + "Test";
        String data2 = Base64.getEncoder().encodeToString(wallet1.publicKey.getEncoded()) + "New Test";

        Assertions.assertTrue(Validation.verifySignature(wallet1.publicKey, data1, Validation.confirm(wallet1.privateKey, data1)));
        Assertions.assertFalse(Validation.verifySignature(wallet1.publicKey, data1, Validation.confirm(wallet1.privateKey, data2)));
        Assertions.assertFalse(Validation.verifySignature(wallet1.publicKey, data2, Validation.confirm(wallet1.privateKey, data1)));
        Assertions.assertTrue(Validation.verifySignature(wallet1.publicKey, data2, Validation.confirm(wallet1.privateKey, data2)));

        Assertions.assertFalse(Validation.verifySignature(wallet1.publicKey, data2, Validation.confirm(wallet2.privateKey, data2)));
        Assertions.assertFalse(Validation.verifySignature(wallet2.publicKey, data2, Validation.confirm(wallet1.privateKey, data2)));


    }
}