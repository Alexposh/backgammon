package service;

import model.Move;
import model.Player;

public class MoveStrategyOnBoardAndSendEnemyPieceToHell extends AbstractStrategy{

	public MoveStrategyOnBoardAndSendEnemyPieceToHell(Player player, Move move) {
		super(player, move);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void makeMove() {

		// TODO: add the logic for sending the piece to hell
		GameService.sendEnemyPieceToHell(player.getTheBoard(), move.getEnd(), player.getOtherPlayer());
		GameService.setOrMovePiece(player.getTheBoard().get(move.getInitial()).getContainedChips(), move.getEnd(), player.getTheBoard());
	}

}
