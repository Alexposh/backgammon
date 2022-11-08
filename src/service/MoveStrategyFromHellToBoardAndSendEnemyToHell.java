package service;

import model.Move;
import model.Player;

public class MoveStrategyFromHellToBoardAndSendEnemyToHell extends AbstractStrategy{
	public MoveStrategyFromHellToBoardAndSendEnemyToHell(Player player, Move move) {
		super(player, move);
	}

	@Override
	public void makeMove() {
		GameService.bringPlayerPieceFromHell(player.getTheBoard(), player, move.getEnd());
		GameService.sendEnemyPieceToHell(player.getTheBoard(), move.getEnd(), player.getOtherPlayer());
	}
}
