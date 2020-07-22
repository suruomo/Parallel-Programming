package chapter5.simplefuture;

/**
 * @author suruomo
 * @date 2020/7/22 14:59
 * @description: FutureData实现快速返回的RealData包装，封装了获取RealData的等待过程
 */
public class FutureData implements Data{
    public RealData realData=null;
    public boolean isReady=false;

    public synchronized void setRealData(RealData data){
        if(isReady){
            return;
        }
        this.realData=data;
        isReady=true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!isReady){
            try{
                wait();
            }catch (InterruptedException e){
            }
        }
        return realData.result;
    }
}
