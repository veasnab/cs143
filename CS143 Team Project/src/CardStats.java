import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;

public class CardStats {
	private int cardCount;
	private String[] deck;
	private Stack<String> card = new Stack<>();
	private Map<String, String> cardType = new HashMap<>();
	private Map<String, Integer> attackStat = new HashMap<>();
	private Map<String, Integer> defenceStat = new HashMap<>();
	private Map<String, Integer> healStat = new HashMap<>();

	public CardStats(String playerDeck) throws FileNotFoundException {
		this.cardCount = 0;
		Scanner file = new Scanner(new File(playerDeck));
		file.nextLine();

		while (file.hasNextLine()) {
			String line = file.nextLine();
			String[] row = line.split(",");
			String cardName = row[0];
			String type = row[1];

			if (!row[0].equals("") && row.length >= 1) {
				card.push(cardName);
				cardCount++;
			}
			if (!row[1].equals("") && row.length >= 2) {
				cardType.put(cardName, type);
			}
			if (!row[2].equals("") && row.length >= 3) {
				int attack = Integer.parseInt(row[2]);
				attackStat.put(cardName, attack);
			}
			if (!row[3].equals("") && row.length >= 4) {
				int defence = Integer.parseInt(row[3]);
				defenceStat.put(cardName, defence);
			}
			if (!row[4].equals("") && row.length >= 5) {
				int heal = Integer.parseInt(row[4]);
				healStat.put(cardName, heal);
			}
		}
		this.deck = new String[cardCount];
		for (int i = cardCount - 1; i >= 0; i--) {
			deck[i] = card.pop();
		}
	}

	public String[] getPlayerDeck() {
		return deck;
	}

	public String getCardType(String card) {
		return cardType.get(card);
	}

	public int getAttackStat(String card) {
		return attackStat.get(card);
	}

	public void setAttackStat(String card, int value) {
		attackStat.put(card, value);
	}

	public int getDefenseStat(String card) {
		return defenceStat.get(card);
	}

	public void setDefenseStat(String card, int value) {
		defenceStat.put(card, value);
	}

	public int getHealStat(String card) {
		return healStat.get(card);
	}

}
