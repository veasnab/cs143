
import java.util.Stack;

public class Deck {
	private int size;
	private String[] cardName;
	private Stack<String> deckOfCard = new Stack<>();

	public Deck(String[] card) {
		size = card.length;
		for (int i = card.length - 1; i >= 0; i--) {
			deckOfCard.push(card[i]);
		}
		this.cardName = new String[size];
		for (int i = 0; i < cardName.length; i++) {
			cardName[i] = deckOfCard.pop();
		}
		for (int i = cardName.length - 1; i >= 0; i--) {
			deckOfCard.push(cardName[i]);
		}
	}

	public void shuffle() {
		this.cardName = new String[size];
		for (int i = 0; i < cardName.length; i++) {
			cardName[i] = deckOfCard.pop();
		}
		for (int i = 0; i < cardName.length; i++) {
			int j = (int) (Math.random() * cardName.length);
			String temp = cardName[i];
			cardName[i] = cardName[j];
			cardName[j] = temp;
		}
		for (int i = cardName.length - 1; i >= 0; i--) {
			deckOfCard.push(cardName[i]);
		}
	}

	public int numberOfCardInDeck() {
		return size;
	}

	public String addACard(String card) {
		size++;
		return deckOfCard.push(card);
	}

	public String drawACard() {
		size--;
		return deckOfCard.pop();
	}

}
