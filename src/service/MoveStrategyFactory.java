package service;

import model.Move;
import model.MoveType;
import model.Player;

public class MoveStrategyFactory {

	
	public static AbstractStrategy createMoveStrategy(Player player, Move selectedMove) {
		AbstractStrategy moveStrategy = null;// = new MoveStrategyOnTheBoard(player, selectedMove);
		
		if(selectedMove.getMoveType() == MoveType.ON_THE_BOARD) {
			moveStrategy = new MoveStrategyOnTheBoard(player, selectedMove);
		}else if(selectedMove.getMoveType() == MoveType.ON_THE_BOARD_AND_SEND_ENEMY_PIECE_TO_HELL) {
			moveStrategy = new MoveStrategyOnBoardAndSendEnemyPieceToHell(player, selectedMove);
		} else if (selectedMove.getMoveType() == MoveType.SEND_PIECE_TO_HEAVEN) {
			moveStrategy = new MoveStrategyToHeaven(player, selectedMove);
		}else if(selectedMove.getMoveType() == MoveType.BRING_FROM_HELL) {
			moveStrategy  = new MoveStrategyFromHellToBoard(player, selectedMove);
		}else if(selectedMove.getMoveType() == MoveType.BRING_FROM_HELL_AND_SEND_ENEMY_PIECE_TO_HELL) {
			moveStrategy  = new MoveStrategyFromHellToBoardAndSendEnemyToHell(player, selectedMove);
		}
		//
		//
		//
		return moveStrategy;
	}
	
}
