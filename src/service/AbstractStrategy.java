package service;

import model.Move;
import model.Player;

public abstract class AbstractStrategy {

	
	protected Player player;
	protected Move move;
	
	public AbstractStrategy(Player player, Move move) {
		this.player = player;
		this.move = move;
	}
	
	public abstract void makeMove();
	
}
