package top.melvyn.learn;

import java.lang.reflect.Field;

public class ReflectDemo2 {

  // public static void main(String[] args) throws Exception {
  // Class stdClass = Student.class;
  // // 获取public字段"score":
  // System.out.println(stdClass.getField("score"));
  // // 获取继承的public字段"name":
  // System.out.println(stdClass.getField("name"));
  // // 获取private字段"grade":
  // System.out.println(stdClass.getDeclaredField("grade"));
  // }
  public static void main(String[] args) throws Exception {
    Person p = new Person("Xiao Ming");
    System.out.println(p.getName()); // "Xiao Ming"
    Class c = p.getClass();
    Field f = c.getDeclaredField("name");
    f.setAccessible(true);
    f.set(p, "Xiao Hong");
    System.out.println(p.getName()); // "Xiao Hong"
  }

}

// class Student extends Person {
// public int score;
// private int grade;
// }

class Person {
  private String name;

  public Person(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }
}