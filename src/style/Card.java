package style;
public interface Card {
	public static enum Suit {SPADES, HEARTS, DIAMONDS, CLUBS};
	
	int getRank();
	Card.Suit getSuit();
	
	// test if the other Card is similar to that card.
	boolean equals(Card other);
	
	// get the string representation of the card's rank and suit
	String toString();
	
	// transform suit name to string form
	public static String suitToString(Card.Suit s) {
		switch (s) {
		case SPADES:
			return "Spades";
		case HEARTS:
			return "Hearts";
		case DIAMONDS:
			return "Diamonds";
		case CLUBS:
			return "Clubs";
			}
		return null;
		}
	}