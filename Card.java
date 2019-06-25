import java.util.Arrays;

class Card {

  int suit;  // Cards are represented by a suit (0-4) and rank (1-13)
  int rank;

  /**
   * Default constructor
   */
  Card() {
    this.suit = 0;
    this.rank = 0;
  }

  /**
   *  Constructor creates a Card given its suit and rank
   */
  Card(int suit, int rank) {
    this.suit = suit;
    this.rank = rank;
  }

  /**
   *  Prints the Card c
   */
  public static void printCard(Card c) {
     String suits[] = {"C", "D", "H", "S"};
     String ranks[] = {"narf", "A", "2", "3","4","5","6","7",
				  "8","9","10","J","Q", "K"};

    System.out.println(ranks[c.rank] + " of " + suits[c.suit]);     
  }

  /**
   *  Returns true iff c1 and c2 are equivalent cards.
   */
  public static boolean sameCard(Card c1, Card c2) {
    return (c1.suit == c2.suit && c1.rank == c2.rank);
  }

  /**
   *  Compares 2 cards by suit and then rank.
   *   Assumes spades > heards > diamonds > clubs.
   */
  public static int compareCard(Card c1, Card c2) {
    if (c1.suit > c2.suit)
      return 1;
    if (c1.suit < c2.suit)
      return -1;
    if (c1.rank > c2.rank) 
      return 1;
    if (c1.rank < c2.rank) 
      return -1;
    return 0;
  }

  /**
   * Does a linear search of the array to find a card or 
   *  return -1 if it is not there.
   */
  public static int findCard(Card[] cards, Card card) {
    for (int i = 0; i < cards.length; i++) {
      if (sameCard(cards[i], card)) {
	return i;
      }
    }
    return -1;
  }

  /**
   * Does a binary search of array to find a card or
   *  return -1 if it is not there.
   */
  public static int findBisect(Card[] cards, Card card, int low, int high) {
    System.out.println(low + "," + high);
    if (high < low) {
      return -1;
    }
    int mid = (low + high)/2;
    int comp = compareCard(cards[mid], card);
    if (comp == 0) {
      return mid;
    } else if (comp > 0) {                          // mid is too high
      return findBisect(cards, card, low, mid-1); 
    } else {                                        // mid is too low
      return findBisect(cards, card, mid+1, high); 
    }
  }

  /**
   *  Prints an array of cards.
   */
  public static void printDeck(Card[] cards) {
    for (int i = 0; i < cards.length; i++) {
      printCard(cards[i]);
    }
  }
  
  public static boolean hasAce(Card[] cards) {
	    for (int i = 0; i < cards.length; i++) {
	      if (cards[i].rank == 1) {
	        return true;
	      }
	    }
	    return false;
	  }
  
  public static int handScore(Card[] cards) {
	  int sum = 0;
	  for (int i = 0; i < cards.length; i++) {
		  if (cards[i].rank == 1) {
		        sum += 10;
		      }
		  else if (cards[i].rank >= 11) {
			sum += 10;
		}
		  else {
			sum += cards[i].rank;
		}
	  }
	  return sum;
  }
  public static boolean hasFlush(Card[] cards) {
	  for (int i = 0; i < cards.length-1; i++) {
		  if (cards[i].suit == cards[i+1].suit) {
			return true;
		}
	  }
	return false;
  }
  public static boolean hasStraight(Card[] cards) {
	  Arrays.sort(cards);
	  for (int i = 0; i < cards.length-1; i++) {
		if (cards[i].rank + 1 == cards[i+1].rank) {
			return true;
		}
	}
	return false;
  }
  /**
   *  Run simple tests of this class.
   */
  public static void main(String args[]) {
    Card card1 = new Card(3,11);
    Card card2 = new Card(2,1);
    Card card3 = new Card(2,1);
    Card card4 = card3;
    System.out.print("card1: ");    // This is inconvenient
    printCard(card1);
    System.out.print("card2: ");
    printCard(card2);
    System.out.print("card3: ");
    printCard(card3);
    System.out.print("card4: ");
    printCard(card4);

    if (sameCard(card1, card2)) {
      System.out.println("card1 and card2 are equivalent");
    } else {
      System.out.println("card1 and card2 are NOT equivalent");
    }

    if (sameCard(card2, card3)) {
      System.out.println("card2 and card3 are equivalent");
    } else {
      System.out.println("card2 and card3 are NOT equivalent");
    }


    if (sameCard(card3, card4)) {
      System.out.println("card3 and card4 are equivalent");
    } else {
      System.out.println("card3 and card4 are NOT equivalent");
    }

    int comp = compareCard(card1, card2);
    if (comp < 0) {
      System.out.println("card1 comes before card2");
    } else if (comp > 0) {
      System.out.println("card2 comes before card1");
    } else {
      System.out.println("card1 and card2 are equivalent");
    }

    Card[] cards = new Card[52];

    if (cards[0] == null) {
      System.out.println("No cards yet");
    }

    int index = 0;
    for (int suit = 0; suit <= 3; suit++) {
      for (int rank = 1; rank <= 13; rank++) {
	cards[index] = new Card(suit, rank);
        index++;
      }
    }

    if (cards[0] == null) {
      System.out.println("No cards yet");
    } else {
      System.out.println("We have a deck of cards");
    }
    
    printDeck(cards);
 
    System.out.println("Finding card1");
    System.out.println(findBisect(cards, card1, 0, 51));

    Card fakeCard = new Card(0, 15);
    System.out.println("Finding 15 of diamonds");
    System.out.println(findBisect(cards, fakeCard, 0, 51));
    
    Card hand1[] = new Card[2];
    hand1[0] = new Card(0,1);
    hand1[1] = new Card(0,5);
    System.out.println("Hand1 has an Ace is: " + hasAce(hand1));
    System.out.println("Hand1 score is: " + handScore(hand1));
    Card hand2[] = new Card[2];
    hand2[0] = new Card(0,4);
    hand2[1] = new Card(0,5);
    System.out.println("Hand1 has an Ace is: " + hasAce(hand2));
    System.out.println("Hand1 score is: " + handScore(hand2));
    Card hand3[] = new Card[5];
    hand3[0] = new Card(0,1);
    hand3[1] = new Card(1,5);
    hand3[2] = new Card(2,10);
    hand3[3] = new Card(0,6);
    hand3[4] = new Card(1,1);     
    System.out.println("Hand3 is a Flush: " + hasFlush(hand3));
    System.out.println("Hand3 is a Straigth: " + hasStraight(hand3));
 }
}