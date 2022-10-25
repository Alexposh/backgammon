package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import app.Program;
import service.GameService;

public class Player {

	private List<Chip> heaven = new ArrayList<>();
	private List<Chip> hell = new ArrayList<>();
	private List<Spot> theBoard = null;
	private String color;
	private Player otherPlayer;
	private int houseFrom;

	public Player(List<Spot> theBoard, String color, int houseFrom) {
		this.theBoard = theBoard;
		this.color = color;
		this.houseFrom = houseFrom;
	}

	public List<Spot> getTheBoard() {
		return theBoard;
	}

	public int getHouseFrom() {
		return houseFrom;
	}

	// [11, 22, 33, 44, 55, 66]
	public List<Spot> getHouseInOrderHellRelated() {
		List<Spot> result = new ArrayList<>();

		if (color.equals("RED")) { // 0 1 2 3 4 5
			for (int i = 0; i <= 5; i++) {
				result.add(theBoard.get(i));
			}
		} else { // 23 22 21 20 19 18
			for (int i = 23; i >= 18; i--) {
				result.add(theBoard.get(i));
			}
		}
		return result;
	}

	public List<Spot> getSpotsOutOfBaseWithMyChips() {
		List<Spot> result = new ArrayList<>();
		List<Spot> filteredResult = new ArrayList<>();

		if (color.equals("RED")) { // 6 ---> 23
			for (int i = 6; i <= 23; i++) {
				result.add(theBoard.get(i));
			}
		} else { // 17 --> 0
			for (int i = 17; i >= 0; i--) {
				result.add(theBoard.get(i));
			}
		}
		for (Spot spot : result) {
			if (spot.getContainedChips().size() > 0 && spot.getContainedChips().get(0).getColor() == color) {
				filteredResult.add(spot);
			}

		}

		return filteredResult;
	}

	public List<Spot> higherSpotsWithChips(Spot spotToStop) {
		List<Spot> chipsOnHigherSpots = new ArrayList<>();
		List<Spot> spotsWithChips = new ArrayList<>();

		for (Spot spot : chipsOnHigherSpots) {

			if (spot.getContainedChips().size() != 0 && getColor() == spot.getContainedChips().get(0).getColor()) {

				spotsWithChips.add(spot);
			}

		}
		return spotsWithChips;
	}

	public List<Spot> countChipsOnHigherSpots(int spotSelected) {
		List<Spot> result = new ArrayList<>();

		if (color.equals("RED")) { // 0 1 2 3 4 5
			for (int i = 5; i > spotSelected; i--) {
				result.add(theBoard.get(i));
			}
		} else { // 23 22 21 20 19 18
			for (int i = 18; i < spotSelected; i++) {
				result.add(theBoard.get(i));
			}
		}
		return result;
	}

	public Player getOtherPlayer() {
		return otherPlayer;
	}

	public void setOtherPlayer(Player otherPlayer) {
		this.otherPlayer = otherPlayer;
	}

	public List<Chip> getHeaven() {
		return heaven;
	}

	public String getColor() {
		return color;
	}

	public void setHeaven(List<Chip> heaven) {
		this.heaven = heaven;
	}

	public List<Chip> getHell() {
		return hell;
	}

	public void setHell(List<Chip> hell) {
		this.hell = hell;
	}

	public int rollDice() {
		Random random = new Random();
		int numberFrom1To6 = random.nextInt(6) + 1;

		return numberFrom1To6;
	}

	// public List<Move> getMyOptions(...
	public List<Move> getMyOptions(RollResult rollResult) {
		RollResult rollResultOK = new RollResult();
		List<Die> diceResultOK = new ArrayList<>();
		for (Die die : rollResult.getDiceResult()) {
			if (die.isAvailable()) {
				diceResultOK.add(die);
			}
		}
		rollResultOK.setDiceResult(diceResultOK);

		List<Spot> spotsWithMyChips = new ArrayList<>();
		List<Spot> spotsWithEnemyChips = new ArrayList<>();
		List<Chip> chipsInHell = new ArrayList<Chip>();

		chipsInHell = hell;
		List<String> optionsFound = new ArrayList<>();
		List<Move> movesAvailable = new ArrayList<>();

		for (Spot spotChecked : theBoard) {
			if (spotChecked.getContainedChips().size() > 0) {
				if (spotChecked.getContainedChips().get(0).getColor() == color) {
					spotsWithMyChips.add(spotChecked);
				} else {
					spotsWithEnemyChips.add(spotChecked);
				}

			}
		}

		// hell logic starts here
		if (hell.size() > 0) {
			for (Die die : rollResultOK.getDiceResult()) {

				Spot theSpot = getOtherPlayer().getHouseInOrderHellRelated().get(die.getValue() - 1);
				String optionFound = ("You can move from hell " + " to spot " + (theBoard.indexOf(theSpot)));
				optionsFound.add(optionFound);
				if (spotsWithEnemyChips.contains(theSpot) && theSpot.getContainedChips().size() > 1) {
					// do nothing
					System.out.println("[Debug] Enemy lives there");
				} else {
					movesAvailable.add(new Move(null, theBoard.indexOf(theSpot)));
				}

			}
			return movesAvailable;
		}

		// hell logic ends here

		// heaven logic starts here
		List<Spot> spotsWithChipsOutOfBase = getSpotsOutOfBaseWithMyChips();// new ArrayList<>();
		List<Spot> spotsWithChipsInMyBase = getHouseInOrderHellRelated();// new ArrayList<>();
		if (spotsWithChipsOutOfBase.size() == 0) {
			for (Die die : rollResultOK.getDiceResult()) {

				for (Spot spot : spotsWithChipsInMyBase) {
					Spot endSpot = null;
					try {
						endSpot = theBoard.get((theBoard.indexOf(spot))
								+ (this.color.equals("RED") ? -die.getValue() : die.getValue()));
					} catch (Exception e) {
						// if we try to get the spot from out of bounds (theBoard)
						System.out.println("[Debug] endpoint is off the board");
					}
					// getOtherPlayer().getHouseInOrderHellRelated().get(die.getValue() - 1);
					if (die.getValue() == getHouseInOrderHellRelated().indexOf(spot) + 1) { // getHouseInOrderHellRelated()
						String optionFound = ("You can move with die " + die.getValue() + " to heaven from spot "
								+ (theBoard.indexOf(spot)));
						optionsFound.add(optionFound);
						movesAvailable.add(new Move(theBoard.indexOf(spot), theBoard.indexOf(spot)
								+ (this.color.equals("RED") ? -die.getValue() : die.getValue())));
					}

					if (die.getValue() < getHouseInOrderHellRelated().indexOf(spot) + 1) {
						// theBoard
						String optionFound = ("You can move with die " + die.getValue() + "  from spot "
								+ (theBoard.indexOf(spot)) + " to spot " + ((theBoard.indexOf(spot))
										+ (this.color.equals("RED") ? -die.getValue() : die.getValue())));

						if (endSpot != null && spotsWithEnemyChips.contains(endSpot)
								&& endSpot.getContainedChips().size() > 1) {
							// do nothing
							System.out.println("[Debug] Enemy lives there");
						} else {
							optionsFound.add(optionFound);
							movesAvailable.add(new Move(theBoard.indexOf(spot), theBoard.indexOf(spot)
									+ (this.color.equals("RED") ? -die.getValue() : die.getValue())));
						}

					}

					if (die.getValue() > getHouseInOrderHellRelated().indexOf(spot) + 1) {

						// intricate stuff
						if (higherSpotsWithChips(spot).size() > 0) {
							String optionFound = ("You cannot move from this spot " + theBoard.indexOf(spot)
									+ " until you clear higher spots");
							optionsFound.add(optionFound);
							movesAvailable.add(new Move(theBoard.indexOf(spot), theBoard.indexOf(spot)
									+ (this.color.equals("RED") ? -die.getValue() : die.getValue())));
						} else {

							String optionFound = ("You can move with die " + die.getValue() + " to heaven from spot "
									+ (theBoard.indexOf(spot)));
							optionsFound.add(optionFound);
							movesAvailable.add(new Move(theBoard.indexOf(spot), theBoard.indexOf(spot)
									+ (this.color.equals("RED") ? -die.getValue() : die.getValue())));
						}
					}
				}
				return movesAvailable;
			}

		}

		// heaven logic ends here

		// board moves logic starts here

		for (Die die : rollResultOK.getDiceResult()) {

			for (Spot spot : spotsWithMyChips) {
				Spot endSpot = null;
				try {
					endSpot = theBoard.get(
							(theBoard.indexOf(spot)) + (this.color.equals("RED") ? -die.getValue() : die.getValue()));
				} catch (Exception e) {
					// if we try to get the spot from out of bounds (theBoard)
					System.out.println("[Debug] endpoint is off the board");
				}

				if (endSpot != null && spotsWithEnemyChips.contains(endSpot)
						&& endSpot.getContainedChips().size() > 1) {
					// do nothing
					System.out.println("[Debug] Enemy lives there");
				} else {
					String optionFound = ("You can move from spot " + (theBoard.indexOf(spot)) + " to spot "
							+ ((theBoard.indexOf(spot))
									+ (this.color.equals("RED") ? -die.getValue() : die.getValue())));
					if (endSpot != null) {
						optionsFound.add(optionFound);
						movesAvailable.add(new Move(theBoard.indexOf(spot), theBoard.indexOf(spot)
								+ (this.color.equals("RED") ? -die.getValue() : die.getValue())));
					}

				}

			}

			// board moves logic ends here
		}
		return movesAvailable;

	}

	// }

	public boolean selectChipAndMoveChip(int dieValue) {
		Scanner scan = new Scanner(System.in);
		int spotFrom;
		int spotTo;
		System.out.print("Move From spot: ");
		spotFrom = scan.nextInt();
//		System.out.print("move to To spot: ");
//		scan.close();

		// spotTo = spotFrom + dieValue; // scan.nextInt();

		if (houseFrom == 5) {
			spotTo = spotFrom - dieValue;
			System.out.println("Try to move red piece from " + spotFrom + " to " + spotTo);
		} else {
			spotTo = spotFrom + dieValue;
			System.out.println("Try to move white piece from " + spotFrom + " to " + spotTo);
		}

		if (spotTo > 23 || spotTo < 0) {
			System.out.println("that is out of the board");
			return false;
		}

		Spot spotFromObject = theBoard.get(spotFrom);
		Spot spotToObject = theBoard.get(spotTo);
		System.out.println("mutam una din cele " + spotFromObject.getContainedChips().size() + " piese");

		if (hell.size() > 0) {
			System.out.println("You have pieces in Hell, move those first");

			// dieValue
			// spotTo
			// if my pieces or enemy pieces are on SpotTo
			//
			return false;
		}

		if (spotFromObject.getContainedChips().size() == 0) {
			// there is no piece i can move
			System.out.println("there is no piece i can move");
			return false;
		}

		if (spotFromObject.getContainedChips().size() > 0) {
			if (!spotFromObject.getContainedChips().get(0).getColor().equals(color)) {
				System.out.println("Not your pieces!");
				return false;
			}
		}

		if (spotToObject.getContainedChips().size() > 0) {
			if (!spotToObject.getContainedChips().get(0).getColor().equals(color)) {
				if (spotToObject.getContainedChips().size() == 1) {
					GameService.sendEnemyPieceToHell(theBoard, spotTo, otherPlayer);

				}
				if (spotToObject.getContainedChips().size() > 1) {
					System.out.println("Not a valid move, enemy lives here!");
					return false;
				}
			}
		}

//		if(spotFromObject.getContainedChips().get(0).getColor().equals(color)) {

//		}

		// TODO: check if the piece i want to move belongs to me
		// TODO: check if i can actually move to 'spotTo'

		GameService.setOrMovePiece(spotFromObject.getContainedChips(), spotTo, theBoard);
		return true;
//		spotTo = scan.nextInt();
//		System.out.println("MOVING ONE PIECE FROM: " + spotFrom + " TO: " + spotTo);

	}

	public boolean moveChipfromHellToBoard(int dieValue) {
		// (theBoard, this, spotTo)
		int spotTo;
		int enterPieceStartingFrom;
		if (houseFrom == 5) {
			enterPieceStartingFrom = 23;
			spotTo = enterPieceStartingFrom - dieValue + 1;
//			 // die value is subtracted to the starting position
		} else {
			enterPieceStartingFrom = 0;
			spotTo = enterPieceStartingFrom + dieValue - 1;
		}

		Spot spotToObject = theBoard.get(spotTo);

//		TODO: 
		if (spotToObject.getContainedChips().size() > 0) {

			if (spotToObject.getContainedChips().get(0).getColor() != color) {

				if (spotToObject.getContainedChips().size() == 1) {
					System.out.println("You killed the enemy piece");
					GameService.sendEnemyPieceToHell(theBoard, spotTo, otherPlayer);
					GameService.bringPlayerPieceFromHell(theBoard, this, spotTo);
					return true;
				}

				if (spotToObject.getContainedChips().size() > 1) {
					System.out.println("Spot is defended, you cannot set piece there");
					return false;
				}
			}

		}

//					

//		
//		if(spotToObject has 1 enemy piece) {
//			sendEnemyPieceToHell
//		}
		GameService.bringPlayerPieceFromHell(theBoard, this, spotTo);
		return true;
	}
}
