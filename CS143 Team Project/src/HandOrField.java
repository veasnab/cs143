
import java.util.LinkedList;

public class HandOrField {
	private LinkedList<String> handOrField;

	public HandOrField() {
		this.handOrField = new LinkedList<String>();
	}

	public String getCardStats(int index) {
		return handOrField.get(index);
	}

	public int size() {
		return handOrField.size();
	}

	public String chooseCard(int index) {
		return handOrField.remove(index);
	}

	public void addCard(String card) {
		handOrField.add(card);
	}

}
