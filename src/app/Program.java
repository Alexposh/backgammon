package app;

import java.util.List;
import java.util.Scanner;

import model.Move;
import model.MoveType;
import model.Player;
import model.RollResult;
import model.Spot;
import service.AbstractStrategy;
import service.GameService;
import service.MoveStrategyFactory;
import service.MoveStrategyOnBoardAndSendEnemyPieceToHell;
import service.MoveStrategyOnTheBoard;

public class Program {

	private static void debugOptions(Player player, RollResult rollResult) {
		List<Move> optionsAvailable = player.getMyOptions(rollResult);
		for (Move element : optionsAvailable) {
			System.out.println(element); 
		}
	}
	
	/**
	 * 
	 * @param player
	 * @return the player that won the game. If either player is still "on the board", return null
	 */
	private static Player playerTurn(Player player) {
		int die1 = player.rollDice();
		int die2 = player.rollDice();

		RollResult rollResult = new RollResult();// List<Die>;

		rollResult.addDie(die1);
		rollResult.addDie(die2);
		if (die1 == die2) {
			rollResult.addDie(die1);
			rollResult.addDie(die2);
		}
		debugOptions(player, rollResult);
		
		
//		OPTION 0 You can move from spot 0 to spot 2
//		OPTION 1 You can move from spot 11 to spot 13
//		You can move from spot 16 to spot 18
//		You can move from spot 18 to spot 20
//		You can move from spot 0 to spot 3
//		You can move from spot 11 to spot 14
//		You can move from spot 16 to spot 19
//		You can move from spot 18 to spot 21

		Scanner scan = new Scanner(System.in);
		while(player.getMyOptions(rollResult).size() > 0) {
			List<Move> optionsAvailable = player.getMyOptions(rollResult);
			for(int i=0; i<optionsAvailable.size(); i++) {
				System.out.println("OPTION: " + i + " " + optionsAvailable.get(i));
			}
			System.out.println("YOUR OPTION: ");
			int optionIndex = scan.nextInt();
			Move selectedMove = optionsAvailable.get(optionIndex);
		
			AbstractStrategy moveStrategy = MoveStrategyFactory.createMoveStrategy(player, selectedMove);
			moveStrategy.makeMove();
			
			
			selectedMove.getDie().setAvailable(false);
			if(player.getHeaven().size() == 15) {
				// game over, player won
				return player;
			}
		}
		return null;
	}
	
	public static void main(String[] args) {

//		List<Spot> board = createBoard();
//		List<Chip> redPieces = generateStalinHeaven();
//		List<Chip> whitePieces = generatePlayerPieces("white");

//		for ( Spot spotOnBoard : board ) {
//			System.out.println("this is a spot"+ spotOnBoard.getContainedChips() );
//		}

		List<Spot> board = GameService.populateBoardFromFile();// GameService.populateBoard();
		GameService.displayBoard(board);
//		List<Chip> heavenRed = generatePlayerHeaven("RED"); // []
//		List<Chip> heavenWhite = generatePlayerHeaven("WHITE"); // []
//		
//		sendPieceToPlayerHeaven(board, 0, heavenRed);
		// white - casa 18
		// red - casa 5
		Player playerRed = new Player(board, "RED", 5);
		Player playerWhite = new Player(board, "WHITE", 18);

		playerRed.setOtherPlayer(playerWhite);
		playerWhite.setOtherPlayer(playerRed);

		Player currentPlayer = null;
		boolean redPlayerTurn = true;
		boolean isGameOver = false;
		Player winner = null;
		while (!isGameOver) {
			// take turns
			if (redPlayerTurn) {
				currentPlayer = playerRed;
			} else {
				currentPlayer = playerWhite;
			}
			winner = playerTurn(currentPlayer);
			if(winner != null) {
				isGameOver = true;
			}
			System.out.println("CURRENTLY MOVING: " + currentPlayer.getColor());
			redPlayerTurn = !redPlayerTurn;
			GameService.displayBoard(board);
		}
		
		GameService.displayBoard(board);
		System.out.println("IN THE END: " + winner.getColor() + " IS THE WINNER");

	}
}
