package org.casbin.casvisor.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.Credentials;
import org.casbin.casvisor.config.Config;
import org.casbin.casvisor.exception.Exception;
import org.casbin.casvisor.util.Map;
import org.casbin.casvisor.util.http.CasvisorResponse;
import org.casbin.casvisor.util.http.HttpClient;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class Service {
    protected final ObjectMapper objectMapper = new ObjectMapper(){{
        configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }};

    protected final Config config;
    protected final String credential;
    protected Service(Config config) {
        this.config = config;
        this.credential = Credentials.basic(config.clientId, config.clientSecret);
    }

    protected <T1, T2> CasvisorResponse<T1, T2> doGet(@NotNull String action, @Nullable java.util.Map<String, String> queryParams, TypeReference<CasvisorResponse<T1, T2>> typeReference) throws IOException {
        String url = String.format("%s/api/%s?%s", config.endpoint, action, Map.mapToUrlParams(queryParams));
        String response = HttpClient.syncGet(url, credential);
        CasvisorResponse<T1, T2> resp = objectMapper.readValue(response, typeReference);
        if (!Objects.equals(resp.getStatus(), "ok")) {
            throw new Exception(String.format("Failed fetching %s : %s", url, resp.getMsg()));
        }

        return resp;
    }

    protected <T1, T2> CasvisorResponse<T1, T2> doPost(@NotNull String action, @Nullable java.util.Map<String, String> queryParams, java.util.Map<String, String> postForm, TypeReference<CasvisorResponse<T1, T2>> typeReference) throws IOException {
        String url = String.format("%s/api/%s?%s", config.endpoint, action, Map.mapToUrlParams(queryParams));
        String response = HttpClient.postForm(url, postForm, credential);
        CasvisorResponse<T1, T2> resp = objectMapper.readValue(response, typeReference);
        if (!Objects.equals(resp.getStatus(), "ok")) {
            throw new Exception(String.format("Failed fetching %s : %s", url, resp.getMsg()));
        }

        return resp;
    }

    protected <T1, T2> CasvisorResponse<T1, T2> doPost(@NotNull String action, @Nullable java.util.Map<String, String> queryParams, String postString, TypeReference<CasvisorResponse<T1, T2>> typeReference) throws IOException {
        String url = String.format("%s/api/%s?%s", config.endpoint, action, Map.mapToUrlParams(queryParams));
        String response = HttpClient.postString(url, postString, credential);
        CasvisorResponse<T1, T2> resp = objectMapper.readValue(response, typeReference);
        if (!Objects.equals(resp.getStatus(), "ok")) {
            throw new Exception(String.format("Failed fetching %s : %s", url, resp.getMsg()));
        }

        return resp;
    }

    protected <T1, T2> CasvisorResponse<T1, T2> doPost(String action, @Nullable java.util.Map<String, String> queryParams, File postFile, TypeReference<CasvisorResponse<T1, T2>> typeReference) throws IOException {
        String url = String.format("%s/api/%s?%s", config.endpoint, action, Map.mapToUrlParams(queryParams));
        String response = HttpClient.postFile(url, postFile, credential);
        CasvisorResponse<T1, T2> resp = objectMapper.readValue(response, typeReference);
        if (!Objects.equals(resp.getStatus(), "ok")) {
            throw new Exception(String.format("Failed fetching %s : %s", url, resp.getMsg()));
        }

        return resp;
    }
}
