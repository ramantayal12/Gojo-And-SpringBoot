package org.gojo.learn.service.trino.clients;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OkhttpRestClient extends BaseHttpClient<String> {


  private final OkHttpClient client;

  @Autowired
  public OkhttpRestClient(
      OkHttpClient client) {
    this.client = client;
  }

  /**
   * Performs a POST request to the specified URL with a JSON payload.
   *
   * @param url         the URL to send the POST request to
   * @param requestBody the request body
   * @return the response body as a string
   */
  public String postCall(String url, RequestBody requestBody) {

    Request request = new Request.Builder()
        .url(url)
        .post(requestBody)
        .build();

    return Optional.of(request)
        .map(req -> {
          try (Response response = client.newCall(req).execute()) {
            if (!response.isSuccessful()) {
              throw new RuntimeException(
                  "Response code: " + response.code() + " - " + response.message());
            }
            return response.body() != null ? response.body().string() : null;
          } catch (IOException e) {
            throw new RuntimeException(e);
          }
        })
        .orElseThrow(() -> new RuntimeException("Request could not be processed"));
  }

  @Override
  public String postCall(String url, Map<String, String> data) {
    return postCall(url, getRequestBody(data));
  }

  @Override
  public ClientType getType() {
    return ClientType.OKHTTP;
  }
}
