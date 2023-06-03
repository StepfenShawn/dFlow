package execute;

public class NumberValue implements Value {
	private long val;
	public NumberValue(long val) {
		this.val = val;
	}
	
	public long getVal() {
		return this.val;
	}
	
	public static long getVal(Value ValueObj) {
		return ((NumberValue)ValueObj).getVal();
	}
	
	@Override
	public String toString() {
		return String.valueOf(val);
	}
}
