package model;

public class Move {
// hell, heaven, 
	private Integer initial;
	private Integer end;
	private Die die;
	private MoveType moveType;
	// private String typeOfMove; // 
	// private Player player;
	
	public Move() {
	}
	
	

	
	public Move(Integer initial, Integer end, 
			Die die, MoveType moveType) {
		this.initial = initial;
		this.end = end;
		this.die = die;
		this.moveType = moveType;
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


	

	public Die getDie() {
		return die;
	}



	public void setDie(Die die) {
		this.die = die;
	}


	

	public MoveType getMoveType() {
		return moveType;
	}



	public void setMoveType(MoveType moveType) {
		this.moveType = moveType;
	}



	@Override
	public String toString() {
		return "You can move from spot " + getInitial() + " to spot " + getEnd();
	}
	
	
	
}
