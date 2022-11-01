package service;

import model.Move;
import model.Player;

public class MoveStrategyOnTheBoard extends AbstractStrategy {

	
	
	public MoveStrategyOnTheBoard(Player player, Move move) {
		super(player, move);
	}

	@Override
	public void makeMove() {
		GameService.setOrMovePiece(player.getTheBoard().get(move.getInitial()).getContainedChips(), move.getEnd(), player.getTheBoard());
	}

}
