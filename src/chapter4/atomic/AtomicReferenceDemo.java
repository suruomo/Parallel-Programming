package chapter4.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author suruomo
 * @date 2020/7/15 18:57
 * @description: 无锁的线程安全对象
 * 为余额低于20的用户充值20，但是多线程操作，如果在充值同时用户又消费导致余额低于20，则会出现重复充值现象
 */
public class AtomicReferenceDemo {
    static AtomicReference<Integer> money=new AtomicReference<Integer>();
    public static void main(String[] args) {
        money.set(19);
        //模拟多个线程同时更新后台数据库，为用户充值
        for(int i = 0 ; i < 3 ; i++) {
            new Thread() {
                @Override
                public void run() {
                    while(true){
                        while(true){
                            Integer m=money.get();
                            if(m<20){
                                if(money.compareAndSet(m, m+20)){
                                    System.out.println("余额小于20元，充值成功，余额:"+money.get()+"元");
                                    break;
                                }
                            }else{
                                //System.out.println("余额大于20元，无需充值");
                                break ;
                            }
                        }
                    }
                }
            }.start();
        }

        //用户消费线程，模拟消费行为
        new Thread() {
            @Override
            public void run() {
                for(int i=0;i<100;i++){
                    while(true){
                        Integer m=money.get();
                        if(m>10){
                            System.out.println("大于10元");
                            if(money.compareAndSet(m, m-10)){
                                System.out.println("成功消费10元，余额:"+money.get());
                                break;
                            }
                        }else{
                            System.out.println("没有足够的金额");
                            break;
                        }
                    }
                    try {Thread.sleep(100);} catch (InterruptedException e) {}
                }
            }
        }.start();
    }
}
