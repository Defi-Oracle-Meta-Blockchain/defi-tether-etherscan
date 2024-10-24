package com.defi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.Log;
import io.reactivex.disposables.Disposable;

@Service
public class EventListenerService {

    private static final Logger logger = LoggerFactory.getLogger(EventListenerService.class);
    private final Web3j web3j;
    private Disposable subscription;

    public EventListenerService(Web3j web3j) {
        this.web3j = web3j;
    }

    // Listen for events emitted from smart contracts
    public void listenForEvents() {
        EthFilter filter = new EthFilter();
        subscription = web3j.ethLogFlowable(filter).subscribe(log -> {
            logger.info("Event received: {}", log);
            processEvent(log);
        }, error -> {
            logger.error("Error while listening for events: {}", error.getMessage());
        });
    }

    // Process the received event
    private void processEvent(Log log) {
        // Implement event processing logic here...
        logger.debug("Processing event: {}", log);
    }

    // Unsubscribe from event listening
    public void stopListening() {
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
            logger.info("Stopped listening for events.");
        }
    }
}
