package com.defi.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class TransactionServiceTest {

    @Test
    public void testCreateTransaction() throws Exception {
        BlockchainService blockchainService = Mockito.mock(BlockchainService.class);
        TransactionService transactionService = new TransactionService(blockchainService);

        // Mocking blockchainService behavior
        when(blockchainService.sendEther("0xRecipient", BigDecimal.TEN)).thenReturn(new TransactionReceipt());

        // Testing createTransaction
        TransactionReceipt receipt = transactionService.createTransaction("0xRecipient", BigDecimal.TEN);
        assertNotNull(receipt);
    }
}
