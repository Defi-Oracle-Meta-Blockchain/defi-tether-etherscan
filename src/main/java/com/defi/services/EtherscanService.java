package com.defi.services;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

public class EtherscanService {

    private final String apiKey;
    private final OkHttpClient httpClient;

    public EtherscanService(String apiKey) {
        this.apiKey = apiKey;
        this.httpClient = new OkHttpClient();
    }

    // Method to check transaction status via Etherscan
    public String getTransactionStatus(String txHash) throws Exception {
        String url = "https://api.etherscan.io/api?module=transaction&action=getstatus&txhash=" + txHash + "&apikey=" + apiKey;
        Request request = new Request.Builder().url(url).build();
        Response response = httpClient.newCall(request).execute();

        if (!response.isSuccessful()) {
            throw new Exception("Failed to fetch transaction status from Etherscan");
        }

        JSONObject json = new JSONObject(response.body().string());
        return json.getJSONObject("result").getString("status");
    }
}
