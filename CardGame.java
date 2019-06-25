
public class CardGame {
	
	/**
     *  A deck of Cards.
     */
	public Deck deck;
	/**
     *  Index of top card in the deck.
     */
	private int top; 
	
	/**
     *  Default constructor creates a Deck of 52 cards.
     */
	public CardGame() {
		deck = new Deck();
	}
	
	/**
     *  Shuffles the deck and resets the top.
     */
    public void init() {
	deck.shuffle();
	top = 0;
    }
    
    /**
     *  Deals n cards returning a hand (a subdeck).
     * @param n the number of cards to deal
     * @return a Deck of n cards dealt from the top of this's deck.
     */
    public Deck deal(int n) {
	Deck hand = deck.subdeck(top, top + n - 1);
	top += n;
	return hand;
    }
	
    /**
     * Returns a string representation of the deck
     * @return the current location of top plus all the cards in the deck
     */
    public String toString() {
	return "(" + top + ") " + deck;
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CardGame game = new CardGame();
		// An array of 4 hands.
		for (int j = 0; j < 3; j++) {
			Deck hand[] = new Deck[4];
			// Deal, sort, and print 4 hands of 5 cards each.
			System.out.println("\nFive sorted poker hands with high scores and low scores");
			game.init();
			for(int i = 0; i<hand.length; i++) {
				hand[i] = game.deal(5);
				hand[i].sort();
				System.out.println(hand[i]);
			}
		}
	}
}
