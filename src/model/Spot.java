package model;

import java.util.ArrayList;
import java.util.List;

public class Spot {

	// private int spotPosition;
	private List<Chip> containedChips = new ArrayList<>();

	public List<Chip> getContainedChips() {
		return containedChips;
	}

	public void setContainedChips(List<Chip> containedChips) {
		this.containedChips = containedChips;
	}

	@Override
	public String toString() {
		return "Spot [containedChips=" + containedChips + "]";
	}
	
	
	
}
