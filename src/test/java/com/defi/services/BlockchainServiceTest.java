package com.defi.services;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class BlockchainServiceTest {

    @Test
    public void testSendEther() throws Exception {
        Web3j web3j = Mockito.mock(Web3j.class);
        Credentials credentials = Mockito.mock(Credentials.class);

        BlockchainService blockchainService = new BlockchainService(web3j, credentials, new DefaultGasProvider());

        // Simulating sending Ether
        assertNotNull(blockchainService.sendEther("0xRecipient", BigDecimal.ONE));
    }
}
