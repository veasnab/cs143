//Team Project Code
//Team HiChew
//12-06-2021

//You will run this CODE HERE!
//The Main method its on line 251 of TheGreatSacrifice Class
//You will need to have the CardStats Class, Deck Class, 
//HandOrField Class, Player Class, and TheGreatSacrifice Class inside the SRC folder

//The excel CardStats.csv file outside the SRC folder to fully run this project.
import java.io.FileNotFoundException;

public class TheGreatSacrifice {
	private int round = 0;
	private String winner = null;
	private Player one;
	private Player two;

	public TheGreatSacrifice() throws FileNotFoundException {
		this.one = new Player("CardStats.csv");
		this.two = new Player("CardStats.csv");
		one.startOfGame();
		two.startOfGame();

		while (0 < one.getPlayerHealth() || 0 < one.getPlayerDeckSize() + one.getPlayerHandSize()
				|| 0 <= two.getPlayerHealth() || 0 <= two.getPlayerDeckSize() + two.getPlayerHandSize()) {
			// fourth
			if (0 < two.getPlayerHealth() && 0 < one.getPlayerHealth()
					&& 0 < two.getPlayerDeckSize() + two.getPlayerHandSize()
					&& 0 < one.getPlayerDeckSize() + one.getPlayerHandSize()) {
				if (round >= 1) {
					System.out.println("Player One");
					one.increaseHealth();
					int count = 1;
					while (count <= 3 && 0 < two.getPlayerHealth()
							&& 0 < one.getPlayerDeckSize() + one.getPlayerHandSize()) {
						System.out.println("\nPlayer One Have Enter Battle Phase: " + count);
						System.out.println("\nSelect a card on your playing field to enter Battle.");
						String choose = one.chooseBattleCard();
						if (choose == null) {
							count = 5;
						}
						if (choose != null) {
							System.out.println("Select an opponent to Battle.");
							System.out.println("Hit the Enter key to Attack Opponent Health.");
							String opponent = two.chooseBattleCard();
							if (opponent == null && two.isDefenderOnBored()) {
								System.out.println(
										"You must first clear your opponent Defense Type card; before attacking "
												+ "their Health.");
							} else if (opponent != null && two.isDefenderOnBored()
									&& !two.getCardType(opponent).equals("Defense")) {
								System.out.println(
										"You must first clear your opponent Defense Type Card; before attacking "
												+ "any other card on the playing field.");
							} else if (opponent != null && two.isDefenderOnBored()
									&& two.getCardType(opponent).equals("Defense")) {
								int attack = one.getAttackStat(choose);
								int defense = one.getDefenseStat(choose);
								int opponentAttack = two.getAttackStat(opponent);
								int opponentDefense = two.getDefenseStat(opponent);
								int ResultingDefense = defense - opponentAttack;
								int opponentResultingDefense = opponentDefense - attack;
								two.setDefenseStat(opponent, opponentResultingDefense);
								one.setDefenseStat(choose, ResultingDefense);
								count++;
							} else if (opponent != null && !two.isDefenderOnBored()) {
								int attack = one.getAttackStat(choose);
								int defense = one.getDefenseStat(choose);
								int opponentAttack = two.getAttackStat(opponent);
								int opponentDefense = two.getAttackStat(opponent);
								int ResultingDefense = defense - opponentAttack;
								int opponentResultingDefense = opponentDefense - attack;
								two.setDefenseStat(opponent, opponentResultingDefense);
								one.setDefenseStat(choose, ResultingDefense);
								count++;
							} else if (opponent == null) {
								int attack = one.getAttackStat(choose);
								two.setPlayerHealth(attack);
								count++;
							}
							two.cardHealth();
							one.cardHealth();
							System.out.println();
							System.out.println("Battle Phase: " + (count - 1));
							System.out.println("Player Two Health: " + two.getPlayerHealth());
							two.playingField();
							System.out.println("\n\t\tPlayer Two Playing Field:");
							System.out.println("\tCurrentBored");
							System.out.println("\t\tPlayer One Playing Field:");
							one.playingField();
							System.out.println("\nPlayer One Health: " + one.getPlayerHealth());
						}
					}
					if (0 < one.getPlayerDeckSize()) {
						one.drawCard();
					}
					System.out.println("\nPlayer One Battle Phase Ending ... ");
					System.out.println();

				}
			}
			// start here
			if (0 < two.getPlayerHealth() && 0 < one.getPlayerHealth()
					&& 0 < two.getPlayerDeckSize() + two.getPlayerHandSize()
					&& 0 < one.getPlayerDeckSize() + one.getPlayerHandSize()) {
				System.out.println("Round: " + round);
				System.out.println("Player One Health: " + one.getPlayerHealth());
				System.out.println("Current Number Of Card In Player One Deck: " + one.getPlayerDeckSize());
				System.out.println("\nPlayer One Hand:");
				one.getHandStats();
				System.out.println("\n\n\tPlayer One Turn");
				one.playingCardPhase();
				// second
				System.out.println("\n\nRound: " + round);
				System.out.println("Player Two Health: " + two.getPlayerHealth());
				System.out.println();
				System.out.println();
				two.playingField();
				System.out.println("\n\t\tPlayer Two Playing Field:");
				System.out.println("\tCurrentBored");
				System.out.println("\t\tPlayer One Playing Field:");
				one.playingField();
				System.out.println("\nRound: " + round);
				System.out.println("Player One Health: " + one.getPlayerHealth());
			}
			// fifth
			if (0 < two.getPlayerHealth() && 0 < one.getPlayerHealth()
					&& 0 < two.getPlayerDeckSize() + two.getPlayerHandSize()
					&& 0 < one.getPlayerDeckSize() + one.getPlayerHandSize()) {
				if (round >= 1) {
					System.out.println("\nPlayer Two");
					two.increaseHealth();
					int count = 1;
					while (count <= 3 && 0 < one.getPlayerHealth()
							&& 0 < two.getPlayerDeckSize() + two.getPlayerHandSize()) {
						System.out.println("\nPlayer Two Have Enter Battle Phase: " + count);
						System.out.println("\nSelect a card on your playing field to enter Battle.");
						String choose = two.chooseBattleCard();
						if (choose == null) {
							count = 5;
						}
						if (choose != null) {
							System.out.println("Select an opponent to Battle.");
							System.out.println("Hit the Enter key to Attack Opponent Health.");
							String opponent = one.chooseBattleCard();
							if (opponent == null && one.isDefenderOnBored()) {
								System.out.println(
										"You must first clear your opponent Defense Type card; before attacking "
												+ "their Health.");
							} else if (opponent != null && one.isDefenderOnBored()
									&& !one.getCardType(opponent).equals("Defense")) {
								System.out.println(
										"You must first clear your opponent Defense Type Card; before attacking "
												+ "any other card on the playing field.");
							} else if (opponent != null && one.isDefenderOnBored()
									&& one.getCardType(opponent).equals("Defense")) {
								int attack = two.getAttackStat(choose);
								int defense = two.getDefenseStat(choose);
								int opponentAttack = one.getAttackStat(opponent);
								int opponentDefense = one.getDefenseStat(opponent);
								int ResultingDefense = defense - opponentAttack;
								int opponentResultingDefense = opponentDefense - attack;
								one.setDefenseStat(opponent, opponentResultingDefense);
								two.setDefenseStat(choose, ResultingDefense);
								count++;
							} else if (opponent != null && !one.isDefenderOnBored()) {
								int attack = two.getAttackStat(choose);
								int defense = two.getDefenseStat(choose);
								int opponentAttack = one.getAttackStat(opponent);
								int opponentDefense = one.getAttackStat(opponent);
								int ResultingDefense = defense - opponentAttack;
								int opponentResultingDefense = opponentDefense - attack;
								one.setDefenseStat(opponent, opponentResultingDefense);
								two.setDefenseStat(choose, ResultingDefense);
								count++;
							} else if (opponent == null) {
								int attack = two.getAttackStat(choose);
								one.setPlayerHealth(attack);
								count++;
							}
							one.cardHealth();
							two.cardHealth();
							System.out.println();
							System.out.println("Battle Phase: " + (count - 1));
							System.out.println("Player Two Health: " + two.getPlayerHealth());
							two.playingField();
							System.out.println("\n\t\tPlayer Two Playing Field:");
							System.out.println("\tCurrentBored");
							System.out.println("\t\tPlayer One Playing Field:");
							one.playingField();
							System.out.println("\nPlayer One Health: " + one.getPlayerHealth());
						}
					}
					System.out.println("\nPlayer Two Battle Phase Ending ... ");
					System.out.println();
					if (0 < two.getPlayerDeckSize()) {
						two.drawCard();
					}

				}
			}
			// third
			if (0 < two.getPlayerHealth() && 0 < one.getPlayerHealth()
					&& 0 < two.getPlayerDeckSize() + two.getPlayerHandSize()
					&& 0 < one.getPlayerDeckSize() + one.getPlayerHandSize()) {
				System.out.println("\n\nRound: " + round);
				System.out.println("Player Two Health: " + two.getPlayerHealth());
				System.out.println("Current Number Of Card In Player Two Deck: " + two.getPlayerDeckSize());
				System.out.println("\nPlayer Two Hand:");
				two.getHandStats();
				System.out.println("\n\n\tPlayer Two Turn");
				two.playingCardPhase();
				System.out.println();
				System.out.println("\nRound: " + round);
				System.out.println("\nPlayer Two Health: " + two.getPlayerHealth());
				two.playingField();
				System.out.println("\n\t\tPlayer Two Playing Field:");
				System.out.println("\tCurrentBored");
				System.out.println("\t\tPlayer One Playing Field:");
				one.playingField();
				System.out.println("\nPlayer One Health: " + one.getPlayerHealth());
				System.out.println("");
			}

			if (one.getPlayerHealth() <= 0) {
				winner = "Player Two";
				break;
			}
			if (two.getPlayerHealth() <= 0) {
				winner = "Player One";
				break;
			}
			int oneCount = 0;
			int twoCount = 0;
			if (one.getPlayerDeckSize() + one.getPlayerHandSize() <= 0) {
				oneCount = oneCount + 1;
				one.setPlayerHealth(oneCount);
			}

			if (two.getPlayerDeckSize() + two.getPlayerHandSize() <= 0) {
				twoCount = twoCount + 1;
				two.setPlayerHealth(twoCount);
			}
			round++;
		}
		// last
		System.out.println(winner + " Winner!");
	}

	public static void main(String[] args) throws FileNotFoundException {

		System.out.println("\n\n\t                     _ ________");
		System.out.println("\t_________ _           /  _ ____|                  __");
		System.out.println("\t\\__ T __/| |___  _ _ /  /  ____  ___  ___  ___ __/ |_");
		System.out.println("\t  |   |  |     \\/ e_\\  / |G>_^7 | r_\\/ e_\\/_a \\|_ t_|");
		System.out.println("\t  |___|  |__h__/\\___]\\ \\__ _/  /|_|  \\___]\\_/\\_\\|__|");
		System.out.println("\t- - - - - - - - - - - \\_______/ - - - - - - - - - -");
		System.out.println("\t                      SACRIFICE\n\n");

		new TheGreatSacrifice();
	}
}
