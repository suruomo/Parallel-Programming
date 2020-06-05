package chapter5.produce;

/**
 * 消费产品
 * @author 苏若墨
 */
public final class PCData {
	private  final int intData;
	public PCData(int d){
		intData=d;
	}
	public PCData(String d){
		intData=Integer.valueOf(d);
	}
	public int getData(){
		return intData;
	}
	@Override
	public String toString(){
		return "data:"+intData;
	}
}
