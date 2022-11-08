package service;

import model.Move;
import model.Player;

public class MoveStrategyToHeaven extends AbstractStrategy{
	public MoveStrategyToHeaven(Player player, Move move) {
		super(player, move);
		
	}

	@Override
	public void makeMove() {
		
		GameService.sendPieceToPlayerHeaven(player.getTheBoard(), move.getInitial(), player);
		
	}
}
