package top.melvyn.learn;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogDemo {
  public static void main(String[] args) {
    Logger logger = Logger.getGlobal();
    logger.info("start process...");
    logger.warning("memory is running out...");
    logger.fine("ignored.");
    logger.severe("process will be terminated...");
  }
}
