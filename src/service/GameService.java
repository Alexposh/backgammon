package service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import model.Chip;
import model.Die;
import model.Player;
import model.RollResult;
import model.Spot;

public class GameService {

	public static List<Spot> createBoard() {
		List<Spot> board = new ArrayList<>(); // 24 spots
//		Spot spot0 = new Spot();
//		Chip c1 = new Chip();
//		Chip c2 = new Chip();
//		List<Chip> chipsSpot0 = new ArrayList<>();
//		chipsSpot0.add(c1);
//		chipsSpot0.add(c2);
//		spot0.setContainedChips(chipsSpot0);
//		board.add(spot0);

		for (int i = 0; i < 24; i++) {
			Spot newSpot = new Spot();
			List<Chip> chipsNewSpot = new ArrayList<>();
			newSpot.setContainedChips(chipsNewSpot);
			board.add(newSpot);
		}
		System.out.println("created the board");
		return board;
	}

	public static List<Chip> generatePlayerHeaven(String color) {
		List<Chip> heaven = new ArrayList<>();
//		for (int i = 0; i < 15; i++) {
//
////			Chip chip = new Chip("RED");
////			stalinChips.add(chip);
//			playerChips.add(new Chip(color));
//		}
		return heaven;
	}

	public static List<Chip> generatePlayerPieces(String color) {
		List<Chip> playerChips = new ArrayList<>();
		for (int i = 0; i < 15; i++) {
			playerChips.add(new Chip(color));
		}
		return playerChips;
	}

	// List<Chip> destination
//	private static void setOrMovePiece(List<Chip> from, int to, List<Spot> board) {
//		Spot spotOnTheBoard = board.get(to);
//		Chip theFirstChip = from.get(0);
//		spotOnTheBoard.getContainedChips().add(theFirstChip);
//		from.remove(theFirstChip);
//	}

	// setOrMovePiece(list6, 8, board);
	// TODO: Create sendPieceToHeaven / sendPierceToEnemyHell

	
	/**
	 * Method for sending a piece to heaven
	 * @param board 
	 * @param spotIndex the spot from where we move the piece
	 * @param playerDestination the player containing the 'correct' heaven
	 * @return
	 */
	public static boolean sendPieceToPlayerHeaven(List<Spot> board, int spotIndex, Player playerDestination) {
		Spot clearedSpot = board.get(spotIndex);
		
		System.out.println("CLEARED SPOT INDEX: " + spotIndex);
		System.out.println("CLEARED SPOT: " + clearedSpot);
		Chip clearedChip = clearedSpot.getContainedChips().get(0);
		
		clearedSpot.getContainedChips().remove(clearedChip);
		playerDestination.getHeaven().add(clearedChip);
		return true;
	}

	public static void sendEnemyPieceToHell(List<Spot> board, int spotIndex, Player playerDestination) {
		Spot clearedSpot = board.get(spotIndex);
		Chip doomedChip = clearedSpot.getContainedChips().get(0);
		clearedSpot.getContainedChips().remove(doomedChip);
		playerDestination.getHell().add(doomedChip);
	}

	public static void bringPlayerPieceFromHell(List<Spot> board, Player PlayerHell, int spotDestination) {
		Chip resurectedChip = PlayerHell.getHell().get(0);
		Spot populatedSpot = board.get(spotDestination);
		PlayerHell.getHell().remove(resurectedChip);
		populatedSpot.getContainedChips().add(resurectedChip);
	}
	
	public static List<Die> checkValidMoves(Player player, RollResult rollResult) {
		 List<Die> allPossibleDice = rollResult.getDiceResult();
		 List<Die> allAvailableDice = new ArrayList<>();
		 List<Die> allMovableDice = new ArrayList<>();
		 for(Die die : allPossibleDice) {
			 if(die.isAvailable()) {
				 allAvailableDice.add(die);
			 }
		 }
		 
		 for(Die die : allAvailableDice) {
			 int checkedMove = die.getValue();
			 if(player.getOtherPlayer().getHouseInOrderHellRelated().get(checkedMove-1).getContainedChips().size() > 1) {
				 System.out.println("You have a piece in hell, you can NOT bring it  from Hell");
			 }
			 if(player.getOtherPlayer().getHouseInOrderHellRelated().get(checkedMove-1).getContainedChips().size() <= 1) {
				 System.out.println("You have a piece in hell, you CAN bring it  from Hell");
				 allMovableDice.add(die);
			 }
		 }
		 return allMovableDice;
			
	}

//	public static void sendPieceToHeaven(List<Chip> from, int to, List<Spot> board) {
//		Spot heaven = board.get(to);
//		Chip theFirstChip = from.get(0);
//		heaven.getContainedChips().add(theFirstChip);
//		from.remove(theFirstChip);
//	}
//
//	public static void sendEnemyPieceToHell(List<Chip> from, int to, List<Spot> board) {
//		Spot hell = board.get(to);
//		Chip theFirstChip = from.get(0);
//		hell.getContainedChips().add(theFirstChip);
//		from.remove(theFirstChip);
//	}

	// generate pieces and board
	// List<Piece> redHeaven = new ArrayList<Piece>

	public static void displayBoard(List<Spot> board) {
		for (Spot spot : board) {
			int chipsOnSpot = spot.getContainedChips().size();
			int spotPosition = board.indexOf(spot);

			String color = "";
			if (chipsOnSpot > 0) {
				if (spot.getContainedChips().get(0).getColor().equals("RED")) {
					color = "R";
				} else {
					color = "W";
				}
			}
//			j++;
			System.out.print("_ "+ spotPosition);
			for (int i = 0; i < chipsOnSpot; i++) {

//				System.out.println("chip number " +(i+1)+ " on spot: "+(spotPosition+1));
				System.out.print("_" + i + color);
			}
			System.out.println();

//			System.out.println("added "+chipsOnSpot+" on position "+(spotPosition+1));

		}
		System.out.println("entire board populated");
	}
	
	public static List<Spot> populateBoardFromFile(){
		List<Spot> board = createBoard();
		List<String> linesFromFile = new ArrayList<>();
		try {
			
			 linesFromFile = Files.readAllLines(Paths.get("C:\\Users\\alexp\\eclipse-workspace\\Table\\src\\service\\board_config_test_heaven.txt"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("COULD NOT FIND board_config.txt");
		}
//		String line1 = linesFromFile.get(0); // "0-W-2"
		// setPiece
		for(String line : linesFromFile) {
			String elements[] = line.split("-"); // elements[0] = "0" elements[1] = "W" elements[2] = "2"
//			if(elements[1].equals("W")) {
//				Chip chip = new Chip("WHITE");
//			}
			Chip chip = elements[1].equals("W") ? new Chip("WHITE") : new Chip("RED");
			
			int nrPieces = Integer.valueOf(elements[2]);
			for(int i=0; i<nrPieces; i++) {
				setPiece(chip, Integer.valueOf(elements[0]), board);
			}
		}
		return board;
	}

	public static List<Spot> populateBoard() {
		List<Spot> board = createBoard();
		List<Chip> redPieces = generatePlayerPieces("RED");
		List<Chip> whitePieces = generatePlayerPieces("WHITE");

		for (int i = 0; i < 2; i++) {

			GameService.setOrMovePiece(whitePieces, 0, board);

		}
//	System.out.println("added 2 white pieces on Spot0");
		for (int i = 0; i < 5; i++) {

			GameService.setOrMovePiece(redPieces, 5, board);

		}
//	System.out.println("added 5 red pieces on Spot5");
		for (int i = 0; i < 3; i++) {

			GameService.setOrMovePiece(redPieces, 7, board);

		}
//	System.out.println("added 3 red pieces on Spot7");
		for (int i = 0; i < 5; i++) {

			GameService.setOrMovePiece(whitePieces, 11, board);

		}
//	System.out.println("added 5 white pieces on Spot11");
//	System.out.println("half the board populated");
		// -----------half the board

		for (int i = 0; i < 5; i++) {

			GameService.setOrMovePiece(redPieces, 12, board);

		}
//	System.out.println("added 5 red pieces on Spot12");
		for (int i = 0; i < 3; i++) {

			GameService.setOrMovePiece(whitePieces, 16, board);

		}
//	System.out.println("added 3 white pieces on Spot16");
		for (int i = 0; i < 5; i++) {

			GameService.setOrMovePiece(whitePieces, 18, board);

		}
//	System.out.println("added 5 white pieces on Spot18");
		for (int i = 0; i < 2; i++) {

			GameService.setOrMovePiece(redPieces, 23, board);

		}
//	 System.out.println("added 2 red pieces on Spot23");
		System.out.println("entire board populated");

		return board;

	}

	
	public static void setPiece(Chip chip, int to, List<Spot> board) {
		Spot spotOnTheBoard = board.get(to);
		spotOnTheBoard.getContainedChips().add(chip);
	}
	
	public static void setOrMovePiece(List<Chip> from, int to, List<Spot> board) {
		Spot spotOnTheBoard = board.get(to);
		Chip theFirstChip = from.get(0);
		spotOnTheBoard.getContainedChips().add(theFirstChip);
		from.remove(theFirstChip);
	}
	
	public static int outOfBaseChips(Player player) {
		int travellingChips = 0;
		
		
//		List<Spot> spotsOutOfBase = player.getSpotsOutOfBase(); 
				
//		for(Spot spot : spotsOutOfBase) {
//			
//			if( spot.getContainedChips().size() != 0 && player.getColor() == spot.getContainedChips().get(0).getColor()) {
//				int chipsOnSpot = spot.getContainedChips().size();
//				travellingChips += chipsOnSpot;
//			}
//			
//		
//		}
		return travellingChips;
	}
	
	public static int chipsOnHigherSpots(Player player, Spot spotToStop) {
		int chipsOnHigherSpots = 0;
		
		
		List<Spot> spotsInBase = player.countChipsOnHigherSpots(player.getHouseInOrderHellRelated().indexOf(spotToStop)); 
//		List<Spot> spotsInBase = player.getOtherPlayer().getHouseInOrderHellRelated(); ?????
				
		for(Spot spot : spotsInBase) {
			
			if( spot.getContainedChips().size() != 0 && player.getColor() == spot.getContainedChips().get(0).getColor()) {
				int chipsOnSpot = spot.getContainedChips().size();
				chipsOnHigherSpots += chipsOnSpot;
			}
			
		
		}
		return chipsOnHigherSpots;
	}
	
}
