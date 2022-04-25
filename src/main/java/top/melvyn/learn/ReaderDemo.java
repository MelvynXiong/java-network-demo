package main.java.top.melvyn.learn;

import java.io.FileReader;
import java.io.Reader;
import java.io.IOException;

public class ReaderDemo {
  public static void main(String[] args) {
    ReaderDemo rDemo = new ReaderDemo();
    rDemo.readFile();
  }

  public void readFile() {
    // 创建一个FileReader对象:
    try (Reader reader = new FileReader("./Client.java")) { // 字符编码是???

      int n;
      while ((n = reader.read()) != -1) {
        System.out.println((char) n); // 打印char
      }
    } catch (IOException e) {
      System.out.println(e);
    }
  }
}
