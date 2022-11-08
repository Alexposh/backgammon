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
	
	private static void playerTurn(Player player) {
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
			//
//			int fromSpot = selectedMove.getInitial();
			
			// move = on the board
			
			
			// move = move on the board and send piece to hell
			// move = send piece to heaven
			// bring from hell to board
			
//			AbstractStrategy moveStrategy = null;// = new MoveStrategyOnTheBoard(player, selectedMove);
//			
//			if(selectedMove.getMoveType() == MoveType.ON_THE_BOARD) {
//				moveStrategy = new MoveStrategyOnTheBoard(player, selectedMove);
//			}else if(selectedMove.getMoveType() == MoveType.ON_THE_BOARD_AND_SEND_ENEMY_PIECE_TO_HELL) {
//				moveStrategy = new MoveStrategyOnBoardAndSendEnemyPieceToHell(player, selectedMove);
//			}
			
			
			// Vehicle vehicle = null;
			// if(type == "car"){
				// vehicle = new Car();
//			}
			// Vehicle vehicle = VehicleFactory.createVehicle("car");
			
			AbstractStrategy moveStrategy = MoveStrategyFactory.createMoveStrategy(player, selectedMove);
			moveStrategy.makeMove();
			
//			if(selectedMove.getMoveType() == MoveType.ON_THE_BOARD) {
//				GameService.setOrMovePiece(player.getTheBoard().get(fromSpot).getContainedChips(), selectedMove.getEnd(), player.getTheBoard());
//			}
			//takeAction();
			
			
			
			selectedMove.getDie().setAvailable(false);
			// optionsAvailable.remove(selectedMove);
		}
		
	}
	
	// player could be either red or white
//	private static void turn(Player player) {
//		int die1 = player.rollDice();
//		int die2 = player.rollDice();
//
//		RollResult rollResult = new RollResult();// List<Die>;
//
//		rollResult.addDie(die1);
//		rollResult.addDie(die2);
//		if (die1 == die2) {
//			rollResult.addDie(die1);
//			rollResult.addDie(die2);
//		}
//		// ---------------------- debug ---------------------
//		List<Move> optiosAvailable = player.getMyOptions(rollResult);
//		for (Move element : optiosAvailable) {
//			System.out.println(element); // model.Move@32819
//		}
//		// ---------------------- /debug ---------------------
//		int chipsOutOfBase = GameService.outOfBaseChips(player);
//		System.out.println(player.getColor() + " ChipsOutOfBase: " + chipsOutOfBase);
////		GameService.setOrMovePiece(board.get(23).getContainedChips(), 21, board);
////		GameService.displayBoard(board);
//
//		// THIS NEEDS TO BE REFACTORED!!!!!
//		Scanner scan = new Scanner(System.in);
//
//		while (player.getHell().size() > 0 && GameService.checkValidMoves(player, rollResult).size() > 0) {
////			GameService.bringPlayerPieceFromHell(player.getTheBoard(), player, player.getHouseInOrderHellRelated()+die -1);
////			die 1 can be placed on the board?
////			die 2 can be placed on the board?
//			GameService.displayBoard(player.getTheBoard());
//			System.out.println("DIE 1: " + die1 + "  DIE 2: " + die2);
//			List<Die> possibleDies = GameService.checkValidMoves(player, rollResult);
//
//			System.out.println("AVAILABLE DIES: ");
//			for (Die die : possibleDies) {
//				System.out.println("DIE INDEX: " + possibleDies.indexOf(die) + " VALUE: " + die.getValue());
//			}
//			int option = -1;
//			System.out.print("Please enter the index of the die (from available dies)! ");
//			option = scan.nextInt();
//
//			Die applicableDie = null;
//			while (applicableDie == null) {
//				try {
//					applicableDie = possibleDies.get(option);
//				} catch (IndexOutOfBoundsException e) {
//					System.out.println("CANNOT SELECT THAT DIE");
//				}
//			}
////			for (Die applicableDie : possibleDies) {
//			applicableDie.setAvailable(false);
////						
//			// List<Spot> hellRelated = player.getHouseInOrderHellRelated(); // 0 - 5
//			// System.out.println("HELL RELATED SIZE: " + hellRelated.size()); // 6
//			Spot theSpot = player.getOtherPlayer().getHouseInOrderHellRelated().get(applicableDie.getValue() - 1);
//			// p1, p2, 18-23
//			// System.out.println("INDEX OF ELEMENT RELATED TO DIE: " +
//			// hellRelated.indexOf(theSpot));
//			//// listaDe6.get(3).getValue(); //
//
//			GameService.bringPlayerPieceFromHell(player.getTheBoard(), player, player.getTheBoard().indexOf(theSpot));
//			GameService.displayBoard(player.getTheBoard());
//			// ---------------------- debug ---------------------
//
//			for (Move element : player.getMyOptions(rollResult)) {
//				System.out.println(element);
//			}
//			// ---------------------- /debug ---------------------
//
////			}
//
//		}
//		if (player.getHell().size() > 0) {
//			// should stop turn
//			System.out.println("END TURN: " + player.getColor());
//			return;
//		}
//
//		// ./------------------------------ hell -----------------------------------
//
//		// we still have moves and pieces on the board to move
//		// while(){...
//
////			
//
////			check if the number of chips on higher spots is 0.
////						only if there are no more chips on larger spots - > move to heaven
////						if there are chips on larger Spots, do not invalidate die and do not move, ask for another spot option 
////						   to be entered
////						
//
////				Spot theSpot = player.getHouseInOrderHellRelated().get(5); // initially the player has 5 pieces here
////				GameService.sendPieceToPlayerHeaven(player.getTheBoard(), player.getTheBoard().indexOf(theSpot), player);
//
////		}
////			
//
//		// ./------------------------------ all pieces in house
//		// -----------------------------------
//
//		// above is done if player has no pieces out of home
//
//		if (die1 != die2) {
//
//			for (int i = 0; i < rollResult.getDiceResult().size(); i++) {
//				GameService.displayBoard(player.getTheBoard());
//				// ---------------------- debug ---------------------
//
//				for (Move element : player.getMyOptions(rollResult)) {
//					System.out.println(element);
//				}
//				// ---------------------- /debug ---------------------
//				System.out.println("You rolled different dies. Which die you wanna use? ");
//				for (Die die : rollResult.getDiceResult()) {
//					System.out.println(
//							"DIE INDEX: " + rollResult.getDiceResult().indexOf(die) + " DIE VALUE: " + die.getValue());
//				}
//
//				int option = 0;
////				scan.close();
//				Die theDieSelected = null;
//				while (theDieSelected == null) {
//					option = scan.nextInt();
//					try {
//						theDieSelected = rollResult.getDiceResult().get(option);
//					} catch (IndexOutOfBoundsException e) {
//						System.out.println("PLEASE SELECT A VALID DIE");
//					}
//				}
//				System.out.println("you selected to move " + theDieSelected.getValue());
//				System.out.println("dice is available? : " + theDieSelected.isAvailable());
//
//				while (!theDieSelected.isAvailable()) {
//					GameService.displayBoard(player.getTheBoard());
//					// ---------------------- debug ---------------------
//
//					for (Move element : player.getMyOptions(rollResult)) {
//						System.out.println(element);
//					}
//					// ---------------------- /debug ---------------------
//					System.out.println("Sorry, die already used");
////					Scanner scan2 = new Scanner(System.in);
//					option = scan.nextInt();
////					scan2.close();
//					theDieSelected = rollResult.getDiceResult().get(option);
//				}
//				int dieValue = theDieSelected.getValue();
//				boolean moveUsed = false;
//				do {
//					// boolean moved =
////					if(chipsOutOfBase == 0) {
////						Spot theSpot = player.getHouseInOrderHellRelated().get(dieValue - 1);
////						moveUsed = GameService.sendPieceToPlayerHeaven(player.getTheBoard(), player.getTheBoard().indexOf(theSpot), player);
////						
////					}else {
//					moveUsed = player.selectChipAndMoveChip(dieValue);
////					}
//
//					GameService.displayBoard(player.getTheBoard());
//					// ---------------------- debug ---------------------
//
//					for (Move element : player.getMyOptions(rollResult)) {
//						System.out.println(element);
//					}
//					// ---------------------- /debug ---------------------
//
//				} while (!moveUsed);
//				theDieSelected.setAvailable(false);
//			}
//		} else {
//
//			for (Die die : rollResult.getDiceResult()) { // 0 1
//				int dieValue = die.getValue();
//				boolean moveUsed = false;
//				do {
//					GameService.displayBoard(player.getTheBoard());
//					System.out.println("You rolled a double: " + dieValue);
//					moveUsed = player.selectChipAndMoveChip(dieValue);
//					GameService.displayBoard(player.getTheBoard());
//					// ---------------------- debug ---------------------
//
//					for (Move element : player.getMyOptions(rollResult)) {
//						System.out.println(element);
//					}
//					// ---------------------- /debug ---------------------
//
//				} while (!moveUsed);
//				die.setAvailable(false);
//
//			}
//		}
//		// ./------------------------------ move on the board
//		// -----------------------------------
//
////		Die result has 2 values: dieValue
////		ask the user the dieIndex
////		ask the user for theSpot
////		
////		Die dieOption
////		Spot theSpot
////		
////		while there are available dies do this:
////			if hell is >0 -> hell(player, dieValue,theSpot) - makes dieOption unavailable
////			else if outOfBaseChips == 0 -> heaven(player, dieValue, theSpot)  - makes dieOption unavailable
////			else if regularMove -> board (player, dieValue,theSpot) - makes dieOption unavailable
////			
////			if hell is >0 -> hell(player, dieValue,theSpotDest) - makes dieOption unavailable
////			else if outOfBaseChips == 0 -> heaven(player, dieValue, theSpotSrc)  - makes dieOption unavailable
////			else if regularMove -> board (player, dieValue,theSpotSrc) - makes dieOption unavailable
////				
//		// ---------------------- rollResultOKdebug ---------------------
//
//		for (Move element : player.getMyOptions(rollResult)) {
//			System.out.println("You can move from spot " + element.getInitial() + " to spot " + element.getEnd());
//		}
//		// ---------------------- /debug ---------------------
//
//		// hell()
//		// house()
//		// board()
//	}

	public static void main(String[] args) {

//		List<Spot> board = createBoard();
//		List<Chip> redPieces = generateStalinHeaven();
//		List<Chip> whitePieces = generatePlayerPieces("white");

//		for ( Spot spotOnBoard : board ) {
//			System.out.println("this is a spot"+ spotOnBoard.getContainedChips() );
//		}

		List<Spot> board = GameService.populateBoard();
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
		while (!isGameOver) {
			// take turns
			if (redPlayerTurn) {
				currentPlayer = playerRed;
			} else {
				currentPlayer = playerWhite;
			}
			playerTurn(currentPlayer);
			System.out.println("CURRENTLY MOVING: " + currentPlayer.getColor());
			redPlayerTurn = !redPlayerTurn;
			GameService.displayBoard(board);
		}

		GameService.displayBoard(board);

//		!!!!!! this works out of the while loop
//		playerRed.selectChipAndMoveChip(rollResult.getDiceResult().get(0).getValue());
//		GameService.displayBoard(board);

//		for (Die die : rollResult.getDiceResult()) {

		// ceva...
//		}

//		if(rollResult.getDiceResult().size())

//		 int j = 0;
//		GameService.setOrMovePiece(board.get(0).getContainedChips(), 2, board);
//		displayBoard(board);

		// System.out.println(playerRed.rollDice());
//		List<Integer> rollResult = new ArrayList<>();
//		int die1 = playerRed.rollDice();
//		rollResult.add(die1);
//		int die2 = playerRed.rollDice();
//		rollResult.add(die2);
//		if(die1 == die2) {
//			rollResult.add(die1);
//			rollResult.add(die2);
//		}
//		System.out.println("DIE 1: " + die1 + "  DIE 2: " + die2);
//		System.out.println("Is this order okay?");
//		boolean answer = false;
//		Scanner scan = new Scanner(System.in);
//		answer = scan.nextBoolean(); // false | true
//		if(!answer) {
//			// reverse the list
//		}
		// [3,3,3,3]
		// [2,3]

//		for (int dieValue : rollResult) {
//			
//			// give the option
//			
//			if(playerRed.getHell().size() > 0) {
//				System.out.println("You have pieces off the board!!");
//				playerRed.moveChipfromHellToBoard(dieValue);
//			} else {
//				playerRed.selectChipAndMoveChip(dieValue);
//			}
//		}
//		if(playerRed.getHell().size() > 0) {
//			System.out.println("You have pieces off the board!!");
//			playerRed.moveChipfromHellToBoard(die1);
//		}
//		playerRed.selectChipAndMoveChip(die1);

//		System.out.println("aceasta aste tabla: "+board);
//		for(int i=0;i<spot0.size();i++){
//		    System.out.println(spot0.get(i));
//		} 
//		List<Chip> chipsSpot0 = new ArrayList<>();
//		chipsSpot0.add(c1);
//		chipsSpot0.add(c2);
//		spot0.setContainedChips(chipsSpot0);

//		for( Chip redChip : redPieces) {
//			System.out.println("this is a  red chip");
//		}
//		
//		for( Chip whiteChip : whitePieces) {
//			System.out.println("this is a  white chip");
//		}

	}
}
