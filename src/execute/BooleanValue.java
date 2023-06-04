package execute;

public class BooleanValue implements Value{
	private boolean val;
	public BooleanValue(boolean val) {
		this.val = val;
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.val);
	}
}
