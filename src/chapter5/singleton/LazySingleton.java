package chapter5.singleton;

/**
 * 延迟加载策略：在instance第一次使用时创建对象
 * @author 苏若墨
 */
public class LazySingleton {
    private LazySingleton() {
        System.out.println("LazySingleton is create");
    }
    private static LazySingleton instance = null;
    public static synchronized LazySingleton getInstance() {
        if (instance == null)
            instance = new LazySingleton();
        return instance;
    }
}
