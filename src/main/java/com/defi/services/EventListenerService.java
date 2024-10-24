package com.defi.services;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthLog;

import java.util.concurrent.CompletableFuture;

public class EventListenerService {

    private final Web3j web3j;

    public EventListenerService(Web3j web3j) {
        this.web3j = web3j;
    }

    // Listen for events emitted from smart contracts
    public void listenForEvents() {
        web3j.ethLogFlowable(new EthLog()).subscribe(log -> {
            System.out.println("Event received: " + log);
            // Process event here...
        });
    }
}
