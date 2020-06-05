package chapter5.singleton;

/**
 * 简单单例模式：由于类初始化时创建静态成员instance，所以在任何地方引用STATUS时（任何Singleton方法或者字段引用都会导致类初始化）创建instance实例
 * @author 苏若墨
 */
public class Singleton {
    public static int STATUS=1;
    private Singleton(){
        System.out.println("Singleton is create");
    }
    private static Singleton instance = new Singleton();
    public static Singleton getInstance() {
        return instance;
    }
}
