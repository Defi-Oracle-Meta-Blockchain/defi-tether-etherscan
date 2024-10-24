package com.defi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.crypto.Credentials;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.response.PollingTransactionReceiptProcessor;
import org.web3j.tx.Transfer;

import java.math.BigDecimal;

public class BlockchainService {

    private static final Logger logger = LoggerFactory.getLogger(BlockchainService.class);
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

    /**
     * Send Ether between accounts.
     *
     * @param recipientAddress the recipient's address
     * @param amount the amount of Ether to send
     * @return the transaction receipt
     * @throws Exception if the transaction fails
     */
    public TransactionReceipt sendEther(String recipientAddress, BigDecimal amount) throws Exception {
        logger.info("Sending {} Ether to {}", amount, recipientAddress);
        TransactionReceipt receipt = Transfer.sendFunds(web3j, credentials, recipientAddress, amount, Transfer.GAS_PRICE).send();
        logger.info("Transaction successful with hash: {}", receipt.getTransactionHash());
        return receipt;
    }

    // Other blockchain transaction logic here...

    /**
     * Get the balance of the given address.
     *
     * @param address the address to check the balance of
     * @return the balance in Wei
     * @throws Exception if the request fails
     */
    public BigDecimal getBalance(String address) throws Exception {
        logger.info("Fetching balance for address: {}", address);
        BigDecimal balance = web3j.ethGetBalance(address, DefaultBlockParameterName.LATEST).send().getBalance();
        logger.info("Balance for address {}: {}", address, balance);
        return balance;
    }
}
