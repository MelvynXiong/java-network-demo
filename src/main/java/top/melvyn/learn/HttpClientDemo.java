package main.java.top.melvyn.learn;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.time.Duration;
import java.util.List;
import java.util.Map;

public class HttpClientDemo { // 全局HttpClient:
  static HttpClient httpClient = HttpClient.newBuilder().build();

  public static void main(String[] args) throws Exception {
    String url = "https://www.sina.com.cn/";
    HttpRequest request = HttpRequest.newBuilder(new URI(url))
        // 设置Header:
        .header("User-Agent", "Java HttpClient").header("Accept", "*/*")
        // 设置超时:
        .timeout(Duration.ofSeconds(5))
        // 设置版本:
        .version(Version.HTTP_2).build();
    HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
    // HTTP允许重复的Header，因此一个Header可对应多个Value:
    Map<String, List<String>> headers = response.headers().map();        
    for (String header : headers.keySet()) {
      System.out.println(header + ": " + headers.get(header).get(0));
    }
    System.out.println(response.body().substring(0, 1024) + "...");
  }
}