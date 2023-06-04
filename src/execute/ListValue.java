package execute;

import java.util.List;

public class ListValue implements Value {
	private List<Value> vals;
	public ListValue(List<Value> list) {
		this.vals = list;
	}
	
	@Override
	public String toString() {
		String s = "[";
		for (Value v : this.vals) {
			s += v.toString() + " ";
		}
		s += "]";
		return s;
	}
}
