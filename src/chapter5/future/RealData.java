package chapter5.future;

import java.util.concurrent.Callable;

/**
 * @author suruomo
 * @date 2020/7/22 14:49
 * @description: 真实数据
 */
public class RealData implements Callable<String> {
    private String para;
    public RealData(String para){
        this.para=para;
    }
    @Override
    public String call() throws Exception {
        //模拟构造数据过程
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        return sb.toString();
    }
}
