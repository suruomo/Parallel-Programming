package chapter5.simplefuture;

/**
 * @author suruomo
 * @date 2020/7/22 15:06
 * @description: 模拟客户端发起请求
 */
public class Client {
    public Data request(final String queryStr) {
        final FutureData future = new FutureData();
        // RealData的构建很慢
        new Thread() {
            @Override
            public void run() {
                RealData realdata = new RealData(queryStr);
                future.setRealData(realdata);
            }
        }.start();
        return future;
    }
}
