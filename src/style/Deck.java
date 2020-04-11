package style;

public interface Deck {
	
	// test if the deck could form a PokerHand
	boolean hasHand();
	// find the next card to deal in the deck
	Card dealNextCard();
	// form a PokerHand from the deck
	PokerHand dealHand();
	// find and remove a specific card in the deck
	void findAndRemove(Card c);

}