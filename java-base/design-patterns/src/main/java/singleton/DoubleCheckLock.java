package singleton;


public class DoubleCheckLock {

  private static DoubleCheckLock instance;
  private DoubleCheckLock() {}
  public static DoubleCheckLock getInstance() {
    if (instance == null) {
      synchronized(DoubleCheckLock.class) { // 此处为类级别的锁
        if (instance == null) {
          instance = new DoubleCheckLock();
        }
      }
    }
    return instance;
  }
}