package style;

public class CardImpl implements Card {
	private static final String[] strings = {null, null, "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King", "Ace"};
	
	public static final int JACK = 11;
	public static final int QUEEN = 12;
	public static final int KING = 13;
	public static final int ACE = 14;


	private int rank;
	private Card.Suit suit;

	public CardImpl(int rank, Card.Suit suit) {
		if (rank < 2 || rank > 14) {
			throw new IllegalArgumentException();
		}
		
		this.rank = rank;
		this.suit = suit;
	}
	
	public int getRank() {
		return rank;
	}

	public Card.Suit getSuit() {
		return suit; 
	}

	/* test if the other Card is similar to that card.
	 * input: other Card
	 * output: boolean
	 */
	public boolean equals(Card other) {
		if (other == null) {
			throw new IllegalArgumentException();
		}
		
		if (rank == other.getRank() && suit == other.getSuit()){
			return true;
		} else {
			return false;
		}
	}
	
	/* convert the rank and suit information of the card to a string version
	 * input: null
	 * output: strings of rank + "of" + strings of suit
	 */
	public String toString() {
		return strings[rank] + " of " + Card.suitToString(suit);
	}

}
