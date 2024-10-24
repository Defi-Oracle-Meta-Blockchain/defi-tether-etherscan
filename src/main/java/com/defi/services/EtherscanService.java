package com.defi.services;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class EtherscanService {

    private static final Logger logger = LoggerFactory.getLogger(EtherscanService.class);
    private static final String ETHERSCAN_API_URL = "https://api.etherscan.io/api";
    private final String apiKey;
    private final OkHttpClient httpClient;

    public EtherscanService(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = createHttpClient();
    }

    private OkHttpClient createHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder()
                .addInterceptor(logging)
                .build();
    }

    // Method to check transaction status via Etherscan
    public String getTransactionStatus(String txHash) throws IOException {
        String url = String.format("%s?module=transaction&action=getstatus&txhash=%s&apikey=%s", ETHERSCAN_API_URL, txHash, apiKey);
        Request request = new Request.Builder().url(url).build();
        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                logger.error("Failed to fetch transaction status from Etherscan: HTTP {}", response.code());
                throw new IOException("Failed to fetch transaction status from Etherscan");
            }

            JSONObject json = new JSONObject(response.body().string());
            return json.getJSONObject("result").getString("status");
        } catch (IOException e) {
            logger.error("Error while fetching transaction status: {}", e.getMessage());
            throw e;
        }
    }
}
