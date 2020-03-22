package style;

public interface PokerHand {
	
	Card[] getCards();
	// test if the Poker hand array has Card c
	boolean contains(Card c);
	
	// check if the Poker hand array has only one pair of same cards
	boolean isOnePair();
	// check if the Poker hand array has two pairs of same cards
	boolean isTwoPair();
	// check if the Poker hand array has three cards of same kind
	boolean isThreeOfAKind();
	// check if the Poker hand is straight 
	boolean isStraight();
	// check if the Poker hand is Flush
	boolean isFlush();
	// check if the Poker hand is Full house
	boolean isFullHouse();
	// check if the poker hand is Four Of A Kind
	boolean isFourOfAKind();
	// check if the Poker Hand is Straight Flush
	boolean isStraightFlush();

	int getHandTypeValue();
	int getHandRank();
	
	// compare the HandTypeValue or HandRank to the other PokerHand
	int compareTo(PokerHand other);
	
}
