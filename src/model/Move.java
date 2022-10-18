package model;

public class Move {
// hell, heaven, 
	private Integer initial;
	private Integer end;
	// private Player player;
	
	public Move() {
	}
	
	
	
	public Move(Integer initial, Integer end) {
		this.initial = initial;
		this.end = end;
	}



	public Integer getInitial() {
		return initial;
	}
	public void setInitial(Integer initial) {
		this.initial = initial;
	}
	public Integer getEnd() {
		return end;
	}
	public void setEnd(Integer end) {
		this.end = end;
	}



	@Override
	public String toString() {
		return "You can move from spot " + getInitial() + " to spot " + getEnd();
	}
	
	
	
}
