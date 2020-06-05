package chapter5.singleton;

/**
 * 使用内部类，在getInstance()内部对SingletonHolder进行初始化
 * @author 苏若墨
 */
public class StaticSingleton {
    public static int STATUS;
    private StaticSingleton(){
        System.out.println("StaticSingleton is create");
    }
    private static class SingletonHolder {
        private static StaticSingleton instance = new StaticSingleton();
    }
    public static StaticSingleton getInstance() {
        return SingletonHolder.instance;
    }
}
