import java.io.FileNotFoundException;
import java.util.Scanner;

public class Player {
	private Scanner s;
	private int playerHealth;
	private CardStats addedDeck;
	private Deck playerDeck;
	private HandOrField playerHand;
	private HandOrField playerField;

	public Player(String customDeck) throws FileNotFoundException {
		this.s = new Scanner(System.in);
		this.playerHealth = 20;
		this.addedDeck = new CardStats(customDeck);
		this.playerDeck = new Deck(addedDeck.getPlayerDeck());
		this.playerHand = new HandOrField();
		this.playerField = new HandOrField();
	}

	public int getPlayerHealth() {
		return playerHealth;
	}

	public int getPlayerDeckSize() {
		return playerDeck.numberOfCardInDeck();
	}

	public int getPlayerHandSize() {
		return playerHand.size();
	}

	public int getFieldSize() {
		return playerField.size();
	}

	public void drawCard() {
		if(playerHand.size() < 5) {
		playerHand.addCard(playerDeck.drawACard());
		}
		else {
			System.out.println("Your hand is too full. Discard draw card." );
			playerDeck.drawACard();
		}
			
	}

	public String getCardType(String card) {
		return addedDeck.getCardType(card);
	}

	public int getAttackStat(String card) {
		return addedDeck.getAttackStat(card);
	}

	public int getDefenseStat(String card) {
		return addedDeck.getDefenseStat(card);
	}

	public void setDefenseStat(String card, int value) {
		addedDeck.setDefenseStat(card, value);
	}

	public void setPlayerHealth(int value) {
		this.playerHealth = this.playerHealth - value;
	}

	public void cardHealth() {
		for (int i = 0; i < playerField.size(); i++) {
			String currentCard = playerField.getCardStats(i);
			int health = addedDeck.getDefenseStat(currentCard);
			if (health <= 0) {
				playerField.chooseCard(i);
			}
		}
	}

	public void increaseHealth() {
		for (int i = 0; i < playerField.size(); i++) {
			String currentCard = playerField.getCardStats(i);
			int health = addedDeck.getHealStat(currentCard);
			if (health > 0) {
				this.playerHealth = this.playerHealth + 1;
				System.out.println("For every Health Type Card on the player own field: \n"
						+ "Increase player Health by One at the start of every round it remain on the field."
						+ "\nThis Player Health Increase to " + this.playerHealth);
			}
		}
	}

	public boolean isDefenderOnBored() {
		for (int i = 0; i < playerField.size(); i++) {
			String currentCard = playerField.getCardStats(i);
			String name = addedDeck.getCardType(currentCard);
			if (name.equals("Defense")) {
				return true;
			}
		}
		return false;
	}

	// Phases
	public void startOfGame() {

		playerDeck.shuffle();
		while (playerHand.size() < 5) {
			playerHand.addCard(playerDeck.drawACard());
		}
	}

	public void getHandStats() {
		if (playerHand.size() > 0) {
			for (int i = 0; i < playerHand.size(); i++) {
				System.out.print(" - - - - - - - -");
			}
			System.out.println("");
			for (int i = 0; i < playerHand.size(); i++) {
				String currentCard = playerHand.getCardStats(i);
				System.out.print("||");
				System.out.print(currentCard);
				System.out.print("\t\t");
			}
			System.out.println("||");
			for (int i = 0; i < playerHand.size(); i++) {
				String currentCard = playerHand.getCardStats(i);
				System.out.print("||Type:" + addedDeck.getCardType(currentCard));
				System.out.print("\t");
			}
			System.out.println("||");
			for (int i = 0; i < playerHand.size(); i++) {
				String currentCard = playerHand.getCardStats(i);
				System.out.print("||");
				if (addedDeck.getHealStat(currentCard) > 0) {
					System.out.print("Healing:" + addedDeck.getHealStat(currentCard));

				} else {
					System.out.print("\t");
				}

				System.out.print("\t");
			}

			System.out.println("||");
			for (int i = 0; i < playerHand.size(); i++) {
				String currentCard = playerHand.getCardStats(i);
				System.out.print("||Attack:" + addedDeck.getAttackStat(currentCard));
				System.out.print("\t");
			}
			System.out.println("||");
			for (int i = 0; i < playerHand.size(); i++) {
				String currentCard = playerHand.getCardStats(i);
				System.out.print("||Defense:" + addedDeck.getDefenseStat(currentCard));
				System.out.print("\t");
			}
			System.out.println("||");
			for (int i = 0; i < playerHand.size(); i++) {
				System.out.print(" - - - - - - - -");
			}
		}
	}

	public void playingCardPhase() {
		int counter = 0;
		while (counter < 2) {
			if (playerHand.size() > 0) {
				System.out.println("\nThe start of the index is 1, where card: " + playerHand.getCardStats(0)
						+ " is located. " + "\nThe end of the index is at " + playerHand.size() + " where card: "
						+ playerHand.getCardStats((playerHand.size() - 1)) + " is located."
						+ "\nEnter a corresponding index at or between 1 to " + playerHand.size()
						+ " to play on the field.");
				System.out.println("Or Hit the Enter key to End Turn: ");
				String cardIndex = s.nextLine();
				if (cardIndex.equals("")) {
					counter = 2;
				}
				if (playerField.size() < 3 && !cardIndex.equals("")) {
					try {
						int handIndex = Integer.parseInt(cardIndex) - 1;
						playerField.addCard(playerHand.chooseCard(handIndex));
						counter++;
					} catch (IndexOutOfBoundsException e) {
						System.out.println("Index enter was invalid");
					} catch (NumberFormatException e) {
						System.out.println("Index enter was invalid");
					} finally {
						getHandStats();
					}
				}
				if(playerField.size() >= 2) {
					counter = 2;
					System.out.println("\nPlaying Field is now Full with 3 card on the field.");
				}

			} else {
				System.out.println("Your hand is empty.");
			}
		}
		System.out.println();
		System.out.println("Ending Player Card Phase...");
	}

	public void playingField() {
		if (playerField.size() > 0) {
			for (int i = 0; i < playerField.size(); i++) {
				System.out.print(" + + + + + + + +");
			}
			System.out.println("");
			for (int i = 0; i < playerField.size(); i++) {
				String currentCard = playerField.getCardStats(i);
				System.out.print("||");
				System.out.print(currentCard);
				System.out.print("\t\t");
			}
			System.out.println("||");
			for (int i = 0; i < playerField.size(); i++) {
				String currentCard = playerField.getCardStats(i);
				System.out.print("||Type:" + addedDeck.getCardType(currentCard));
				System.out.print("\t");
			}
			System.out.println("||");
			for (int i = 0; i < playerField.size(); i++) {
				String currentCard = playerField.getCardStats(i);
				System.out.print("||");
				if (addedDeck.getHealStat(currentCard) > 0) {
					System.out.print("Healing:" + addedDeck.getHealStat(currentCard));

				} else {
					System.out.print("\t");
				}

				System.out.print("\t");

			}
			System.out.println("||");
			for (int i = 0; i < playerField.size(); i++) {
				String currentCard = playerField.getCardStats(i);
				System.out.print("||Attack:" + addedDeck.getAttackStat(currentCard));
				System.out.print("\t");
			}
			System.out.println("||");
			for (int i = 0; i < playerField.size(); i++) {
				String currentCard = playerField.getCardStats(i);
				System.out.print("||Defense:" + addedDeck.getDefenseStat(currentCard));
				System.out.print("\t");
			}
			System.out.println("||");
			for (int i = 0; i < playerField.size(); i++) {
				System.out.print(" + + + + + + + + ");
			}
		}

	}

	public String chooseBattleCard() {

		if (playerField.size() == 0) {
			System.out
					.println("You have no card on the your playing field. Hit the Enter Key to End the Battle Phase.");
		}
		if (playerField.size() == 1) {
			System.out.println("\nYou only have one card on the playing field, enter 1 to go into battle");
			System.out.println("Or Hit the Enter Key to End the Battle Phase");
		}
		if (playerField.size() > 1) {
			System.out.println("Enter a corresponding index at or between 1 to " + playerField.size()
					+ " to enter the Battling Phase.");
			System.out.println("Or Hit the Enter to End Turn the Battle Phase: ");
		}
		String Index = s.nextLine();
		if (!Index.equals("")) {
			try {
				int index = Integer.parseInt(Index) - 1;
				return playerField.getCardStats(index);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Index enter was invalid");
				chooseBattleCard();
			} catch (NumberFormatException e) {
				System.out.println("Index enter was invalid");
				chooseBattleCard();
			}
		}
		return null;
	}

}
