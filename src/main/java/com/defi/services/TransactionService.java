package com.defi.services;

import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigDecimal;

public class TransactionService {

    private final BlockchainService blockchainService;

    public TransactionService(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }

    // Method to create and send transactions
    public TransactionReceipt createTransaction(String recipientAddress, BigDecimal amount) throws Exception {
        return blockchainService.sendEther(recipientAddress, amount);
    }

    // Other transaction logic here...
}
