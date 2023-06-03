package execute;

public class StringValue implements Value {
	private String val;
	public StringValue(String val) {
		this.val = val;
	}
	
	public String getVal() {
		return this.val;
	}
	
	@Override
	public String toString() {
		return this.val;
	}
}
