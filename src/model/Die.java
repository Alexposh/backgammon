package model;

public class Die {

	private int value;
	private boolean available;
	
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	public boolean isAvailable() {
		return available;
	}
	public void setAvailable(boolean available) {
		this.available = available;
	}
	@Override
	public String toString() {
		return "Die [value=" + value + ", available=" + available + "]";
	}
	
	
	
	
}
