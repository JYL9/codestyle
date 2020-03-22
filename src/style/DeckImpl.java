package style;

public class DeckImpl implements Deck {
	
	private Card[] newVar;			
	private int _num_left_to_deal;
	
	public DeckImpl() {
		_num_left_to_deal = 52;
		newVar = new Card[_num_left_to_deal];

		int cidx = 0;
		for (Card.Suit s : Card.Suit.values()) {
			for (int rank = 2; rank <= CardImpl.ACE; rank++) {
				newVar[cidx] = new CardImpl(rank, s);
				cidx += 1;
			}
		}
		
		for (int i=0; i<newVar.length; i++) {
			int swap_idx = i + ((int) (Math.random() * (newVar.length - i)));
			Card tmp = newVar[i];
			
			newVar[i] = newVar[swap_idx];
			
			newVar[swap_idx] = tmp;
		}		
	}

	// check if the deck could form a hand (has more than 5 cards)
	// Returns boolean
	public boolean hasHand() {
		boolean bool = false;
		if (_num_left_to_deal >= 5) {
			bool = true;
		}
		return bool;
	}
	
	//find the next dealt Card array
	//Returns card
	public Card dealNextCard() {
		if (_num_left_to_deal== 0) {
			throw new RuntimeException();
		}
		Card dealtCard = newVar[nextUndealtIndex()];
		_num_left_to_deal -= 1;
		return dealtCard;
	}
	
	//deal a poker hand
	//Returns PokerHand
	public PokerHand dealHand() {
		if (hasHand() == false) {
			throw new RuntimeException("Deck does not have enough cards to deal another hand");
		}
		
		Card[] hand_cards = new Card[5];
		for (int i=0; i<hand_cards.length; i++) {
			
			hand_cards[i] = dealNextCard();
		}
		PokerHand h = new PokerHandImpl(hand_cards);
		return h;
	}	

	//find and remove a specific Card
	//Returns void
	public void findAndRemove(Card c) {
		if (_num_left_to_deal == 0) {
			return;
		}
		
		for (int i=nextUndealtIndex(); i<52; i++) {
			if (newVar[i].equals(c)) {
				Card tmp = newVar[i];
				newVar[i] = newVar[nextUndealtIndex()];
				newVar[nextUndealtIndex()] = tmp;
				dealNextCard();
				return;
			}
		}
		return;
	}
	private int nextUndealtIndex() {
		int x = 52-_num_left_to_deal;
		return x;
	}
}
