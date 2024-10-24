package com.defi.services;

import org.springframework.stereotype.Service;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;

@Service
public class TransactionService {

    private static final Logger logger = LoggerFactory.getLogger(TransactionService.class);
    private final BlockchainService blockchainService;

    public TransactionService(BlockchainService blockchainService) {
        this.blockchainService = blockchainService;
    }

    /**
     * Creates and sends a transaction to the specified recipient address with the given amount.
     *
     * @param recipientAddress the address of the recipient
     * @param amount the amount to send
     * @return the transaction receipt
     * @throws Exception if an error occurs during the transaction
     */
    public TransactionReceipt createTransaction(String recipientAddress, BigDecimal amount) throws Exception {
        logger.info("Creating transaction to send {} Ether to {}", amount, recipientAddress);
        try {
            TransactionReceipt receipt = blockchainService.sendEther(recipientAddress, amount);
            logger.info("Transaction successful with hash: {}", receipt.getTransactionHash());
            return receipt;
        } catch (Exception e) {
            logger.error("Transaction failed: {}", e.getMessage());
            throw e;
        }
    }

    // Other transaction logic here...
}
