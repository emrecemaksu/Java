/* 
 *  Added toString(), Removed most static methods.
 */

/**
 * A Deck is an array of cards.
 */
class Deck {

    /**
     * The array containing the Deck's Cards.
     */
    private Card[] cards;
    private String type;

    /**
     * Parametric constructor makes a Deck with room for n Cards (but no Cards yet).
     * @param n the size of the Deck
     * @return an Deck with room for n Cards
     */
    public Deck(int n) {
	this.cards = new Card[n];
    }

    /**
     * Parametric constructor makes a Deck of a certain type
     * @param s the type of deck -- e.g., euchre or piquet
     * @return a Deck of a certain type
     */
    public Deck(String s) {
	type = s;
	if (type.equals("euchre")) {
	    this.cards = new Card[24];
	    int index = 0;
	    for (int suit = 0; suit <= 3; suit++) {
		for (int rank = 9; rank <= 13; rank++) {
		    this.cards[index] = new Card(suit, rank);
		    index++;
		}
		this.cards[index] = new Card(suit, 1);  // Ace
		index++;
	    }
	}
	if (type.equals("piquet")) {
	    this.cards = new Card[32];
	    int index = 0;
	    for (int suit = 0; suit <= 3; suit++) {
		for (int rank = 7; rank <= 13; rank++) {
		    this.cards[index] = new Card(suit, rank);
		    index++;
		}
		this.cards[index] = new Card(suit, 1);  // Ace
		index++;
	    }
	}
    }

    /**
     * Default constructor makes a standard Deck of 52 cards.
     * 
     * The Cards are ordered in the standard way:
     * AC 2C ... KC AD 2D ... KD AH 2H ... KH AS 2S ... KS
     *
     * @return a Deck with 52 standard Cards.
     */
    public Deck() {
	type = "poker";
	this.cards = new Card [52];
	
	int index = 0;
	for (int suit = 0; suit <= 3; suit++) {
	    for (int rank = 1; rank <= 13; rank++) {
		this.cards[index] = new Card(suit, rank);
		index++;
	    }
	}
    }

    /**
     * Returns a String representation of the Deck.
     *
     * @return a string with Card names
     */
    public String toString() {
	String s = type + " ";
	for (int i=0; i < cards.length; i++) {
	    s += cards[i] + " ";
	}
	return s;
    }

    /**
     * Finds a card in a deck of cards.
     *
     * @param deck a Deck of 0 or more Cards
     * @param card a Card with a suit and rank
     * @return the index of the first occurrence of card in deck  or -1 if no occurrence
     */

    public static int findCard (Deck deck, Card card) {
	for (int i = 0; i< deck.cards.length; i++) {
	    if (card.equals(deck.cards[i])) {
		return i;
	    }
	}
	return -1;
    }

    /**
     * Generate a random int in the range [low, high).
     * @param low the bound in the range
     * @param high the upper bound of the range
     * @return a random int between low and high, including low but not including high. 
     */
    public static int randInt(int low, int high) {
	int range = high - low;
	return low + (int)(Math.random() * range);
    }

    /**
     * Swaps the cards at indexes i and j in the cards array.
     * @param i index of one card
     * @param j index of the other card
     */
    private void swapCards(int i, int j) {
	Card temp = cards[i];
	cards[i] = cards[j];
	cards[j] = temp;
    }

    /**
     * Shuffles the deck.
     *
     * Algorithm: For each card, C, in the deck choose a random card whose
     * index is >= C's index and swap them. 
     */
    public void shuffle() {
	for (int i = 0; i < cards.length; i++) {
	    int j = Deck.randInt(i, cards.length);
	    swapCards(i,j);
	}
    }

    /**
     * Sorts the deck using selection sort algorithm.
     *
     * Algorithm:  For each card, C,  in the deck, find the smallest
     *  card whose index is >= C's index and swap them.
     */
    public void sort() {
	for (int i = 0; i < cards.length; i++) {
	    int j = indexLowestCard(i, cards.length-1);
	    swapCards(i, j);
	}
    }

    /**
     * Finds the lowest card in Deck in the range low to high inclusive, i.e., [low, high].
     * @param low the lower bound of the range
     * @param high the upper bound of the range, included in the range
     * @return the index of the lowest card in the range [low,high]
     */
    private int indexLowestCard(int low, int high) {
	int lowIndex = low;
	for (int i = low; i <= high; i++) {
	    Card card = cards[i];
	    int comp = card.compareTo(cards[lowIndex]);
	    if (comp < 0) {
		lowIndex = i;
	    }
	}
	return lowIndex;
    }

    /**
     * Find the lowest card in the deck
     * @return the index of the lowest card in the Deck.
     */
    public int indexLowestCard() {
	return indexLowestCard(0, cards.length-1);
    }

    /**
     * Makes a new deck of cards from a subset of cards from the original.
     *
     * The subdeck contains clones of the original cards.
     * @param low the lower bound of the range of the subdeck
     * @param high the upper bound of the range of the subdeck, inclusive
     * @param a new Deck consisting of copies of the cards at locations low...high of this Deck.
     */
    public Deck subdeck(int low, int high) {
	Deck sub = new Deck(high-low+1);
	
	for (int i = 0; i<sub.cards.length; i++) {
	    sub.cards[i] = cards[low+i];
	}
	return sub;
    }

    /**
     * Returns the sum of the highest n cards in the deck
     * @param n the number of cards to count in the score
     * @retrun the sum of ranks of the n cards
     */
    public int highScore(int n) {
	this.sort();
	int sum = 0;

	// Start at the end of the array and go right to left
	for (int i = cards.length-1; i > cards.length - (n+1);  i--) {
	    sum += cards[i].getRank();
	}
	return sum;
    }

    /**
     * Returns the sum of the lowest n cards in the deck
     * @param n the number of cards to count in the score
     * @retrun the sum of ranks of the n cards
     */
    public int lowScore(int n) {
	this.sort();
	int sum = 0;
	for (int i = 0;  i < n;  i++) {  // start at the beginning of the array
	    sum += cards[i].getRank();
	}
	return sum;
    }


    /**
     *  Code to test the Deck class.
     */
    public static void main(String args[]) {

	Deck deck = new Deck();   // Create a deck of 52 cards and print it
	System.out.println("Printing the original deck.");
	System.out.println(deck);
	System.out.println("The Queen of Spades is at location " + Deck.findCard(deck, new Card(3,12)));
	System.out.println("The index of lowest card is " + deck.indexLowestCard());    

	deck.shuffle();
	System.out.println("\nPrinting the shuffled deck.");
	System.out.println(deck);
	System.out.println("The Queen of Spades is at location " + Deck.findCard(deck, new Card(3,12)));
	System.out.println("The index of lowest card is " + deck.indexLowestCard());    

	deck.sort();
	System.out.println("\nPrinting the sorted deck.");
	System.out.println(deck);
	System.out.println("The Queen of Spades is at location " + Deck.findCard(deck, new Card(3,12)));
	System.out.println("The index of lowest card is " + deck.indexLowestCard());    
    }
}