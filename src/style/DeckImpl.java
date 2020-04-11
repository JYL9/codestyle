package style;

/* implementation of Deck.
 * has functions of check if the deck could form a hand and generate a PokerHand;
 * find the next card to deal,
 * and remove one specific card.
 */
public class DeckImpl implements Deck {
	
	private Card[] _new_deck;			
	private int _num_left_to_deal;
	
	public DeckImpl() {
		_num_left_to_deal = 52;
		_new_deck = new Card[_num_left_to_deal];
		
		// create a deck with all of the card types
		for (Card.Suit s : Card.Suit.values()) {
			for (int rank = 2; rank <= CardImpl.ACE; rank++) {
				_new_deck[_new_deck.length] = new CardImpl(rank, s);
			}
		}
		
		// randomize the order of cards in the deck
		for (int i=0; i<_new_deck.length; i++) {
			int swap_idx = i + ((int) (Math.random() * (_new_deck.length - i)));
			Card tmp = _new_deck[i];
			_new_deck[i] = _new_deck[swap_idx];
			_new_deck[swap_idx] = tmp;
		}		
	}

	/* check if the deck could form a hand (has more than 5 cards)
	 * input: null
	 * output: boolean
	 * return true or false
	 */
	public boolean hasHand() {
		boolean bool = false;
		if (_num_left_to_deal >= 5) {
			bool = true;
		}
		return bool;
	}
	
	/* find the next Card to deal
	 * input: null
	 * output: card object
	 * return a card or error when there is no card left to deal
	 */
	public Card dealNextCard() {
		if (_num_left_to_deal== 0) {
			throw new RuntimeException();
		}
		Card dealt_card = _new_deck[nextUndealtIndex()];
		_num_left_to_deal -= 1;
		return dealt_card;
	}
	
	/* deal a poker hand
	 * input: null
	 * output: PokerHand object
	 * return PokerHand object or error when there is no hand formed by the deck
	 */
	public PokerHand dealHand() {
		if (!hasHand()) {
			throw new RuntimeException("Deck does not have enough cards to deal another hand");
		}
		
		Card[] hand_cards = new Card[5];
		for (int i=0; i<hand_cards.length; i++) {
			hand_cards[i] = dealNextCard();
		}
		PokerHand poker_hd = new PokerHandImpl(hand_cards);
		return poker_hd;
	}	

	/* find and remove a specific Card
	 * input: Card object c
	 * output: void
	 * no return value needed
	 */
	public void findAndRemove(Card c) {
		if (_num_left_to_deal == 0) {
			return;
		}
		
		for (int i=nextUndealtIndex(); i<52; i++) {
			if (_new_deck[i].equals(c)) {
				Card tmp = _new_deck[i];
				_new_deck[i] = _new_deck[nextUndealtIndex()];
				_new_deck[nextUndealtIndex()] = tmp;
				dealNextCard();
				return;
			}
		}
		return;
	}
	
	/* helper method: find the next undealt card's index
	 * input: null
	 * output: integer of the index of the next undealt card
	 * return integer
	 */ 
	private int nextUndealtIndex() {
		return 52 - _num_left_to_deal;
	}
}
