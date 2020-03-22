package style;

/* implementation of PokerHand.
 * Get the information about poker hand type 
 * and its corresponding rank and hand value
 */
public class PokerHandImpl implements PokerHand {

	private Card[] cardArr;

	public PokerHandImpl(Card[] cards) {
		if (cards.equals(null)) {
			throw new RuntimeException("Null pointer");
		}
		if (cards.length != 5) {
			throw new RuntimeException("Out of bounds");
		}
		for (int i=0; i<5; i++) {
			if (cards[i] == null) {
				throw new RuntimeException("Null pointer");
			}
		}

		cardArr = cards.clone();
		
		// reorder the collection of five card 
		// by smallest rank to highest rank
		for (int i=0; i<5; i++) {			
			for (int j=i+1; j<5; j++) {
				if (cardArr[j].getRank() < cardArr[i].getRank()) {
					Card tmp = cardArr[i];
					cardArr[i] = cardArr[j];
					cardArr[j] = tmp;
				}
			}
		}
	}

	public Card[] getCards() {
		return cardArr;
	}
	
	/* test if the Poker hand array has a specific kind of card
	 * input: Card c
	 * output: boolean true or false
	 */
	public boolean contains(Card c) {
		if (c == null) {
			throw new IllegalArgumentException();
		}
		
		for (int i=0; i<5; i++) {
			if (cardArr[i].equals(c)) {
				return true;
			}
		}
		return false;
	}

	/* check if the poker hand is flush which means five cards have same suit
	 * input: null
	 * output: boolean 
	 */
	public boolean isFlush() {
		for (int i=1; i<5; i++) {
			if (cardArr[i].getSuit() != cardArr[0].getSuit()) {
				return false;
			}
		}
		return true;
	}

	/* check if the poker hand is straight
	 * the rank of first card + 1 = the rank of the second card
	 * Return true if the rank is consecutive or it is The Wheel
	 */
	public boolean isStraight() {
		boolean checkConsecutive = true;
		for (int i=0; i<4; i++) {
			if (cardArr[i].getRank()+1 != cardArr[i+1].getRank()) { 
				checkConsecutive = false;
				break;
			}
		}
		return checkConsecutive || isTheWheel();
	}
	
	/* check if the poker hand is The Wheel
	 * which consists exactly Ace (14), 2, 3, 4, 5
	 */
	private boolean isTheWheel() {
		
		if (cardArr[0].getRank() == 2
				&& cardArr[1].getRank() == 3
				&& cardArr[2].getRank() == 4
				&& cardArr[3].getRank() == 5
				&& cardArr[4].getRank() == 14) {
			return true;
		}
		return false;
	}

	/* check if the poker hand is OnePair
	 * when only one pair of cards has the same rank
	 * and the cards other than this pair don't have same rank
	 */
	public boolean isOnePair() { 

		int pair_idx = find_pair_starting_at(0);

		boolean no_other_pairs;
		if (find_pair_starting_at(pair_idx+1) == -1) {
			no_other_pairs = true;
		} else {
			no_other_pairs = false;
		}
		
		if ((pair_idx != -1) && no_other_pairs) {
			return true;
		} else {
			return false;
		}
		
	}
	
	/* check if the poker hand is TwoPair
	 * when two pairs of cards have the same rank
	 * and the poker hand is not FullHouse
	 */
	public boolean isTwoPair() {
		
		int firstPairIdx = find_pair_starting_at(0);
		int second_pairIdx = find_pair_starting_at(firstPairIdx+2);

		if ((firstPairIdx != -1) && (second_pairIdx != -1) && !isFourOfAKind() && !isFullHouse()) {
			return true;
		} else {
			return false;
		}
	}
	
	/* check if the poker hand is Three of A Kind
	 * if there is no same rank card or if the similar pair is index 3 and 4,
	 * then return false;
	 * and it is not Four of A kind or Full House
	 */
	public boolean isThreeOfAKind() {
		int first_pair_idx = find_pair_starting_at(0);
		
		if ((first_pair_idx == -1) || (first_pair_idx == 3)) {
			return false;
		}
		
		
		if ((cardArr[first_pair_idx].getRank() == cardArr[first_pair_idx+2].getRank()) &&
				!isFourOfAKind() && !isFullHouse()) {
			return true;
		} else {
			return false;
		}
	}
	
	/* check if the poker hand is Full House
	 * when card index 0, 1 have same rank and 2, 3, 4 have same rank or
	 * card index 0, 1, 2 have same rank and 3, 4 have same rank
	 */
	public boolean isFullHouse() {
		if ((cardArr[0].getRank() == cardArr[1].getRank()) &&
				 (cardArr[2].getRank() == cardArr[3].getRank()) &&
				 (cardArr[3].getRank() == cardArr[4].getRank())){
			return true;
		}
		if ((cardArr[0].getRank() == cardArr[1].getRank()) &&
				 (cardArr[1].getRank() == cardArr[2].getRank()) &&
				 (cardArr[3].getRank() == cardArr[4].getRank())){
			return true;
		}
		return false;
	}
	
	/* check if the poker hand is Four Of A Kind
	 * when card index 0, 1, 2, 3 have same rank or
	 * card index 1, 2, 3, 4 have same rank
	 */
	public boolean isFourOfAKind() {
		if ((cardArr[0].getRank() == cardArr[1].getRank()) &&
				 (cardArr[1].getRank() == cardArr[2].getRank()) &&
				 (cardArr[2].getRank() == cardArr[3].getRank())) {
			return true;
		}
		if ((cardArr[1].getRank() == cardArr[2].getRank()) &&
				 (cardArr[2].getRank() == cardArr[3].getRank()) &&
				 (cardArr[3].getRank() == cardArr[4].getRank())) {
			return true;
		}
		return false;
	}	
	
	/* check if the poker hand is Straight Flush
	 * when it is both Straight and Flush
	 */
	public boolean isStraightFlush() {
		if (isStraight() && isFlush()) {
			return true;
		}
		return false;
	}
	
	/* get the rank of the poker hand
	 * One Pair: rank of the index of the pair
	 * Tow pair: rank of the card 3
	 * ThreeOfAKind or FourOfAKind or Full House: rank of the card 2
	 * Wheel: 5
	 * other: the rank of the card 4
	 */
	public int getHandRank() {
		if (isOnePair()) {
			return cardArr[find_pair_starting_at(0)].getRank();
		} else if (isTwoPair()) {
			return cardArr[3].getRank();
		} else if (isThreeOfAKind() || isFourOfAKind()|| isFullHouse()) {
			return cardArr[2].getRank();
		} else if (isTheWheel()) {
			return 5;
		} else {
			return cardArr[4].getRank();
		}
	}
	
	/* compare with the other PokerHank about Type value or handRank
	 * compare HandTypeValue first,
	 * if equals, then compare HandRank
	 */
	public int compareTo(PokerHand other) {
		if (getHandTypeValue() < other.getHandTypeValue()) {
			return -1;
		} else if (getHandTypeValue() > other.getHandTypeValue()) {
			return 1;
		} else {
			if (getHandRank() < other.getHandRank()) {
				return -1;
			} else if (getHandRank() > other.getHandRank()) {
				return 1;
			} else {
				return 0;
			}
		}
	}
	
	public int getHandTypeValue() {
		
		if (isStraightFlush()) return 9;
		if (isOnePair()) return 2;			
		if (isTwoPair()) return 3;		
		if (isThreeOfAKind()) return 4;		
		if (isStraight()) return 5;
		if (isFlush()) return 6;
		if (isFullHouse()) return 7;
		if (isFourOfAKind()) return 8;
		return 1;
	}
	
	// helper method: compare the rank of card at index num to num + 1
	// if the rank of two card is the same, return the index num
	private int find_pair_starting_at(int num) {		
		if (num < 0) {
			num = 0;
		}
		if (num >= 4) {
			return -1;
		}
		
		for (int i=num; i<4; i++) {
			if (cardArr[i].getRank() == cardArr[i+1].getRank()) { 
				return i;
			}
		}
		return -1;
	}
}
