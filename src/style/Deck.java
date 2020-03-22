package style;

public interface Deck {

	// test if has hand in the deck
	boolean hasHand();
	
	// 
	Card dealNextCard();
	PokerHand dealHand();
	void findAndRemove(Card c);

}