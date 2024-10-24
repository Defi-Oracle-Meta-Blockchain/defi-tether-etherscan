// Integration tests for the application
package com.defi.integration;

import com.defi.services.BlockchainService;
import com.defi.services.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class IntegrationTest {

    private static final Logger logger = LoggerFactory.getLogger(IntegrationTest.class);

    private Web3j web3j;
    private Credentials credentials;
    private BlockchainService blockchainService;
    private TransactionService transactionService;

    @BeforeEach
    public void setUp() {
        logger.info("Setting up mocks and services for integration tests");
        web3j = Mockito.mock(Web3j.class);
        credentials = Mockito.mock(Credentials.class);
        blockchainService = new BlockchainService(web3j, credentials, new DefaultGasProvider());
        transactionService = new TransactionService(blockchainService);
    }

    @Test
    public void testSendEtherIntegration() throws Exception {
        logger.info("Starting testSendEtherIntegration");
        when(blockchainService.sendEther("0xRecipient", BigDecimal.ONE)).thenReturn(new TransactionReceipt());

        TransactionReceipt receipt = blockchainService.sendEther("0xRecipient", BigDecimal.ONE);
        assertNotNull(receipt);
        logger.info("testSendEtherIntegration completed successfully with receipt: {}", receipt);
    }

    @Test
    public void testCreateTransactionIntegration() throws Exception {
        logger.info("Starting testCreateTransactionIntegration");
        when(blockchainService.sendEther("0xRecipient", BigDecimal.TEN)).thenReturn(new TransactionReceipt());

        TransactionReceipt receipt = transactionService.createTransaction("0xRecipient", BigDecimal.TEN);
        assertNotNull(receipt);
        logger.info("testCreateTransactionIntegration completed successfully with receipt: {}", receipt);
    }
}
