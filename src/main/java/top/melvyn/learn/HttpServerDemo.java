package main.java.top.melvyn.learn;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class HttpServerDemo {
  public static void main(String[] args) throws IOException {
    // 注意到代码ss.accept()表示每当有新的客户端连接进来后，就返回一个Socket实例，这个Socket实例就是用来和刚连接的客户端进行通信的
    // 由于客户端很多，要实现并发处理，我们就必须为每个新的Socket创建一个新线程来处理，这样，主线程的作用就是接收新的连接，每当收到新连接后，就创建一个新线程进行处理。
    try (ServerSocket ss = new ServerSocket(8080)) { // 监听指定端口
      System.out.println("server is running...");
      for (;;) {
        Socket sock = ss.accept();
        System.out.println(
            "connected from " + sock.getRemoteSocketAddress() + "local is" + sock.getLocalSocketAddress());
        Thread t = new Handler(sock);
        t.start();
      }
    }
  }
}

class Handler extends Thread {
  Socket sock;

  public Handler(Socket sock) {
    this.sock = sock;
  }

  @Override
  public void run() {
    try (InputStream input = this.sock.getInputStream()) {
      try (OutputStream output = this.sock.getOutputStream()) {
        handle(input, output);
      }
    } catch (Exception e) {
      try {
        this.sock.close();
      } catch (IOException ioe) {
      }
      System.out.println("client disconnected.");
    }
  }

  private void handle(InputStream input, OutputStream output) throws IOException {
    System.out.println("Process new http request...");
    var writer = new BufferedWriter(new OutputStreamWriter(output, StandardCharsets.UTF_8));
    var reader = new BufferedReader(new InputStreamReader(input, StandardCharsets.UTF_8));
    // 读取HTTP请求:
    boolean requestOk = false;
    String first = reader.readLine();
    System.out.println(">>>" +first);
    if (first.startsWith("GET / HTTP/1.")) {
      requestOk = true;
    }
    for (;;) {
      String header = reader.readLine();
      if (header.isEmpty()) { // 读取到空行时, HTTP Header读取完毕
        break;
      }
      System.out.println(">>>>" + header);
    }
    System.out.println(requestOk ? "Response OK" : "Response Error");
    if (!requestOk) {
      // 发送错误响应:
      writer.write("HTTP/1.0 404 Not Found\r\n");
      writer.write("Content-Length: 0\r\n");
      writer.write("\r\n");
      writer.flush();
    } else {
      // 发送成功响应:
      String data = "<html><body><h1>Hello, world!</h1></body></html>";
      int length = data.getBytes(StandardCharsets.UTF_8).length;
      writer.write("HTTP/1.0 200 OK\r\n");
      writer.write("Connection: close\r\n");
      writer.write("Content-Type: text/html\r\n");
      writer.write("Content-Length: " + length + "\r\n");
      writer.write("\r\n"); // 空行标识Header和Body的分隔
      writer.write(data);
      writer.flush();
    }
  }
}