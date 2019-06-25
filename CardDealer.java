/*
 * CardDealer.java -- main class for Lab13
 *
 * @author CPSC 115
 */

/**
 *  CardDealer contains methods for performing various
 *  card game operations.
 */
public class CardDealer {

    /**
     *  A deck of Cards.
     */
    private Deck deck;

    /**
     *  Index of top card in the deck.
     */
    private int top; 

    /**
     *  Default constructor creates a Deck of 52 cards.
     */
    public CardDealer() {
	deck = new Deck();
    }


    /**
     * Constructor creates a deck for the game s.
     * @param s the name of the card game, e.g., Euchre
     */
    public CardDealer(String s) {
	deck = new Deck(s);
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


    /**
     * Code to test the methods.
     */
    public static void main(String args[]) {
        // Create a CardDealer and print it.
        System.out.println("Exercises 0:  Create a standard deck and print it.");
	CardDealer game = new CardDealer();
	System.out.println(game);

        // Shuffle the deck and print the game again.
        System.out.println("Exercises 0:  Initialize the game and print it.");
	game.init();
	System.out.println(game);

        System.out.println("\nExercises 1 & 2:  Deal, sort, and print 5 7-card hands with high and low scores.");
        // An array of 5 hands.
	Deck hand[] = new Deck[5];

        // Deal, sort, and print 5 hands of 7 cards each.
	System.out.println("\nFive sorted poker hands with high scores");
	game.init();
	for (int i = 0; i < hand.length; i++) {
	    hand[i] = game.deal(7);
	    hand[i].sort();
	    System.out.println(hand[i] + " (" + hand[i].highScore(5) + ")");
	}
	System.out.println("\nFive sorted poker hands with low scores");
	CardDealer game1 = new CardDealer();
	game1.init();
	for (int i = 0; i < hand.length; i++) {
	    hand[i] = game1.deal(7);
	    hand[i].sort();
	    System.out.println(hand[i] + "(" + hand[i].lowScore(5) + ")");
	}

        System.out.println("\nExercises 3:  Not shown. Sort in suit/rank order.");
        System.out.println("Uncomment Card.compareTo() and recompile and run.");
        game = new CardDealer();
        game.init();
        Deck deck = game.deal(52);
        deck.sort();
	System.out.println(deck);

        // Create and print a euchre game
        System.out.println("\nExercises 4:  Create and print a Euchre and Piquet deck.");
	game = new CardDealer("euchre");
        System.out.println();
	System.out.println(game);

        // Create and print a piquet game
	game = new CardDealer("piquet");
        System.out.println();
	System.out.println(game);
    }

}