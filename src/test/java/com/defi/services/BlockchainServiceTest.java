package com.defi.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

public class BlockchainServiceTest {

    private Web3j web3j;
    private Credentials credentials;
    private BlockchainService blockchainService;

    @BeforeEach
    public void setUp() {
        web3j = Mockito.mock(Web3j.class);
        credentials = Mockito.mock(Credentials.class);
        blockchainService = new BlockchainService(web3j, credentials, new DefaultGasProvider());
    }

    @Test
    public void testSendEther() throws Exception {
        // Mocking the TransactionReceipt
        TransactionReceipt mockReceipt = new TransactionReceipt();
        mockReceipt.setTransactionHash("0xMockTransactionHash");

        // Mocking the sendEther method
        when(blockchainService.sendEther(eq("0xRecipient"), any(BigDecimal.class))).thenReturn(mockReceipt);

        // Simulating sending Ether
        TransactionReceipt receipt = blockchainService.sendEther("0xRecipient", BigDecimal.ONE);
        assertNotNull(receipt);
        assertNotNull(receipt.getTransactionHash());
    }
}
