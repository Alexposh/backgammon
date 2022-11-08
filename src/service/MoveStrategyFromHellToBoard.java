package service;

import model.Move;
import model.Player;

public class MoveStrategyFromHellToBoard extends AbstractStrategy {
	public MoveStrategyFromHellToBoard(Player player, Move move) {
		super(player, move);
	}

	@Override
	public void makeMove() {
		GameService.bringPlayerPieceFromHell(player.getTheBoard(), player, move.getEnd());
	}
}
