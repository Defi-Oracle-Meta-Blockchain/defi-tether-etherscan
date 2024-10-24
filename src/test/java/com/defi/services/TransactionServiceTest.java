package com.defi.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(TransactionServiceTest.class);

    private BlockchainService blockchainService;
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        logger.info("Setting up mocks and services for TransactionServiceTest");
        blockchainService = Mockito.mock(BlockchainService.class);
        transactionService = new TransactionService(blockchainService);
    }

    @Test
    public void testCreateTransaction() throws Exception {
        logger.info("Starting testCreateTransaction");

        // Mocking blockchainService behavior
        TransactionReceipt mockReceipt = new TransactionReceipt();
        when(blockchainService.sendEther("0xRecipient", BigDecimal.TEN)).thenReturn(mockReceipt);

        // Testing createTransaction
        logger.info("Calling createTransaction with recipient: 0xRecipient and amount: 10");
        TransactionReceipt receipt = transactionService.createTransaction("0xRecipient", BigDecimal.TEN);
        assertNotNull(receipt, "Transaction receipt should not be null");
        logger.info("Transaction successful with receipt: {}", receipt);
    }

    @Test
    public void testCreateTransactionWithInvalidAddress() {
        logger.info("Starting testCreateTransactionWithInvalidAddress");

        // Mocking blockchainService behavior to throw an exception for invalid address
        when(blockchainService.sendEther("invalidAddress", BigDecimal.TEN)).thenThrow(new IllegalArgumentException("Invalid address"));

        // Testing createTransaction with invalid address
        logger.info("Calling createTransaction with invalid recipient address");
        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.createTransaction("invalidAddress", BigDecimal.TEN);
        }, "Expected createTransaction to throw, but it didn't");
        logger.info("Transaction failed as expected due to invalid address");
    }

    @Test
    public void testCreateTransactionWithZeroAmount() {
        logger.info("Starting testCreateTransactionWithZeroAmount");

        // Mocking blockchainService behavior to throw an exception for zero amount
        when(blockchainService.sendEther("0xRecipient", BigDecimal.ZERO)).thenThrow(new IllegalArgumentException("Amount must be greater than zero"));

        // Testing createTransaction with zero amount
        logger.info("Calling createTransaction with recipient: 0xRecipient and amount: 0");
        assertThrows(IllegalArgumentException.class, () -> {
            transactionService.createTransaction("0xRecipient", BigDecimal.ZERO);
        }, "Expected createTransaction to throw, but it didn't");
        logger.info("Transaction failed as expected due to zero amount");
    }
}
