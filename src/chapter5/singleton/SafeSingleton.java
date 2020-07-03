package chapter5.singleton;

/**
 * @author suruomo
 * @date 2020/7/3 13:43
 * @description:双重检验单例模式
 */
public class SafeSingleton {
    private volatile static SafeSingleton uniqueInstance;
    private SafeSingleton() {}
    public SafeSingleton getUniqueInstance(){
        if(uniqueInstance==null){
            synchronized (SafeSingleton.class){
                if (uniqueInstance==null){
                    uniqueInstance=new SafeSingleton();
                }
            }
        }
        return uniqueInstance;
    }
}
