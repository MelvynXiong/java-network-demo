package example;

import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

public class FileDemo {

  public static void main(String[] args) throws IOException {
    // File f = new File("./Client.java");
    // System.out.println(f.length());
    System.out.println(Charset.defaultCharset());   
    FileDemo fd = new FileDemo();
    fd.copyFile("./Client.java", "./Target.java");
  }

  public void copyFile(String srcPath, String target) {
    try (InputStream in = new FileInputStream(srcPath);
        OutputStream out = new FileOutputStream(target)) {
        byte[] allBytes = in.readAllBytes();
        out.write(allBytes);
    } catch (FileNotFoundException e) {
      System.out.println("file not found");
    } catch (IOException e) {
      System.out.println("IO exception");
    }
  }

  public void writeFile() throws IOException {
    try (OutputStream output = new FileOutputStream("readme.txt")) {
      output.write("Hello".getBytes("UTF-8")); // Hello
    } // 编译器在此自动为我们写入finally并调用close()
  }

  public void readFile() throws IOException {
    String s;

    // 创建一个FileInputStream对象:
    try (InputStream input = new FileInputStream("./Client.java")) {

      int n; // 反复调用read()方法，直到返回-1
      StringBuilder sb = new StringBuilder();
      while ((n = input.read()) != -1) {
        sb.append((char) n);
      }
      s = sb.toString();
      System.out.println(s); // 打印byte的值
    } catch (FileNotFoundException e) {
      System.out.println(e);
    }
  }
}
