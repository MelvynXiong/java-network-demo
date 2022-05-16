package top.melvyn.learn;

public class ErrorDemo {
  public static void main(String[] args) {
    System.out.print(getNumber(0));
    System.out.print(getNumber(1));
    System.out.print(getNumber(2));
    System.out.print(getNumber(4));
  }

  public static int getNumber(int num) {
    try {
      int result = 2 / num;
      return result;
    } catch (Exception exception) {
      return 0;
    } finally {
      if (num == 0) {
        return -1;
      }
      if (num == 1) {
        return 1;
      }
    }
  }
}
