// Utility class for Etherscan API calls
package com.defi.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class EtherscanApiClient {

    private static final Logger logger = LoggerFactory.getLogger(EtherscanApiClient.class);

    @Value("${etherscan.api.key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public EtherscanApiClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getTransactionStatus(String txHash) {
        String url = buildUrl("transaction", "gettxreceiptstatus", "txhash", txHash);
        logger.debug("Fetching transaction status for txHash: {}", txHash);
        return fetchFromEtherscan(url);
    }

    public String getAccountBalance(String address) {
        String url = buildUrl("account", "balance", "address", address, "tag", "latest");
        logger.debug("Fetching account balance for address: {}", address);
        return fetchFromEtherscan(url);
    }

    private String buildUrl(String module, String action, String... params) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("https://api.etherscan.io/api")
                .queryParam("module", module)
                .queryParam("action", action)
                .queryParam("apikey", apiKey);

        for (int i = 0; i < params.length; i += 2) {
            builder.queryParam(params[i], params[i + 1]);
        }

        return builder.toUriString();
    }

    private String fetchFromEtherscan(String url) {
        try {
            return restTemplate.getForObject(url, String.class);
        } catch (Exception e) {
            logger.error("Error fetching data from Etherscan API: {}", e.getMessage());
            return null;
        }
    }
}
