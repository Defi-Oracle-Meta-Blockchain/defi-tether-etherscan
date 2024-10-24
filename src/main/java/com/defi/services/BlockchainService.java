package com.defi.services;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.crypto.Credentials;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;
import org.web3j.tx.Transfer;

import java.math.BigDecimal;

public class BlockchainService {

    private final Web3j web3j;
    private final Credentials credentials;
    private final ContractGasProvider gasProvider;
    private final RawTransactionManager transactionManager;

    public BlockchainService(Web3j web3j, Credentials credentials, ContractGasProvider gasProvider) {
        this.web3j = web3j;
        this.credentials = credentials;
        this.gasProvider = gasProvider;
        this.transactionManager = new RawTransactionManager(web3j, credentials,
                new PollingTransactionReceiptProcessor(web3j, 1000, 30));
    }

    // Send Ether between accounts
    public TransactionReceipt sendEther(String recipientAddress, BigDecimal amount) throws Exception {
        return Transfer.sendFunds(web3j, credentials, recipientAddress, amount, Transfer.GAS_PRICE).send();
    }

    // Other blockchain transaction logic here...
}
