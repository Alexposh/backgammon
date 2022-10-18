package model;

import java.util.ArrayList;
import java.util.List;

public class RollResult {

	private List<Die> diceResult = new ArrayList<>();
	
	public void addDie(int value) {
		Die die = new Die();
		die.setValue(value);
		die.setAvailable(true);
		diceResult.add(die);
	}

	public List<Die> getDiceResult() {
		return diceResult;
	}

	public void setDiceResult(List<Die> diceResult) {
		this.diceResult = diceResult;
	}
	
	
	
	
}
