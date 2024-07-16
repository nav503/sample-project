package com.asos.codetest.sselse.app;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CustomerDataAccessImpl implements CustomerDataAccess{

    private String baseUrl;

    public CustomerDataAccessImpl() {
        AppSettings appSettings = new ApplicationSettings();
        baseUrl = appSettings.getThirdPartyCustomerServiceBaseUrl();
    }

    @Override
    public CustomerResponse loadCustomer(int customerId) throws IOException {
        URL url = new URL(baseUrl + String.format("/api/customers/%d", customerId));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(connection.getInputStream())
        );
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = reader.readLine()) != null) {
            content.append(inputLine);
        }

        reader.close();
        connection.disconnect();
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(content.toString(), CustomerResponse.class);
    }
}
