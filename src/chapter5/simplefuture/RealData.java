package chapter5.simplefuture;

/**
 * @author suruomo
 * @date 2020/7/22 14:56
 * @description: 真实数据
 */
public class RealData implements Data{
    public final String result;

    public RealData(String para) {
        //RealData的构造可能很慢，需要用户等待很久
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < 10; i++) {
            sb.append(para);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
        }
        result=sb.toString();
    }

    @Override
    public String getResult() {
        return result;
    }
}
