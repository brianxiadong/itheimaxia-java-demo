package singleton;

public class StaticInnerClass {

    private StaticInnerClass() {
    }

    private static class SingletonHolder {
        private static final StaticInnerClass instance = new StaticInnerClass();
    }

    public static StaticInnerClass getInstance() {
        return SingletonHolder.instance;
    }
}