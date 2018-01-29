import java.util.Random;

//  CARD. A playing card. It's immutable.

final class Card {

	//  RANK NAME. Printable names of card ranks.

	private static final String[] rankName = {
		"ace", //   0
		"two", //   1
		"three", //   2
		"four", //   3
		"five", //   4
		"six", //   5
		"seven", //   6
		"eight", //   7
		"nine", //   8
		"ten", //   9
		"jack", //  10
		"queen", //  11
		"king" //  12
	};

	//  SUIT NAME. Printable names of card suits.

	private static final String[] suitName = {
		"spade", //  0
		"heart", //  1
		"diamond", //  2
		"club" //  3
	};

	private int rank; //  Card rank, between 0 and 12 inclusive.
	private int suit; //  Card suit, between 0 and  3 inclusive.

	//  CARD. Constructor. Make a new CARD with the given RANK and SUIT.

	public Card(int rank, int suit) {
		if (0 <= suit && suit <= 3 && 0 <= rank && rank <= 12) {
			this.rank = rank;
			this.suit = suit;
		} else {
			throw new IllegalArgumentException("No such card.");
		}
	}

	//  GET RANK. Return the RANK of this card.

	public int getRank() {
		return rank;
	}

	//  GET SUIT. Return the SUIT of this card.

	public int getSuit() {
		return suit;
	}

	//  TO STRING. Return a string that describes this card, for printing only. For
	//  example, we might return "the queen of diamonds" or "the ace of hearts".

	public String toString() {
		return "the " + rankName[rank] + " of " + suitName[suit] + "s";
	}
}

class Deck {
	private Card[] cards;
	private Random random;
	private int index;
	public Deck() {
		this.index = 0;
		this.random = new Random();
		this.cards = new Card[52];
		for (int suit = 0; suit < 4; suit += 1) {
			for (int rank = 0; rank < 13; rank += 1) {
				this.cards[4 * rank + suit] = new Card(rank, suit);
			}
		}
	}
	public void shuffle() {
		for (int i = this.cards.length - 1; i >= 1; i -= 1) {
			int j = Math.abs(random.nextInt()) % (i + 1);
			Card temp = this.cards[i];
			this.cards[i] = this.cards[j];
			this.cards[j] = temp;
		}
	}
	public boolean canDeal() {
		return this.index < this.cards.length;
	}
	public Card deal() {
		if (!this.canDeal()) {
			throw new IllegalArgumentException("There are no more cards to deal!");
		}
		return this.cards[this.index++];
	}
}

class Tableau {
	private class Pile {
		private Card card;
		private Pile next;
		public Pile(Card card) {
			this.card = card;
		}
	}
	private Deck deck;
	private Pile pile;
	public Tableau() {
		this.deck = new Deck();
		this.deck.shuffle();
		this.addPile(this.deck.deal());
	}
	public void addPile(Card card) {
		Pile pile = new Pile(card);
		pile.next = this.pile;
		this.pile = pile;
		System.out.println("Added " + card.toString() + ".");
	}
	private boolean canMerge() {
		if (!this.hasManyPiles())
			return false;
		return this.canPutOn(this.pile.card, this.pile.next.card);
	}
	private boolean canPutOn(Card left, Card right) {
		return left.getSuit() == right.getSuit() || left.getRank() > right.getRank();
	}
	private boolean hasManyPiles() {
		return this.pile.next != null;
	}
	private void mergeTwoPiles() {
		if (!this.canMerge())
			throw new IllegalStateException("Can't merge!");
		Card c1 = this.pile.card;
		Card c2 = this.pile.next.card;
		this.pile.next.card = c1;
		this.pile = this.pile.next;
		System.out.println("Merged " + c1.toString() + " and " + c2.toString() + ".");
	}
	private void results() {
		if (this.hasManyPiles()) {
			System.out.println("Lost the game.");
			// System.exit(1);
		} else {
			System.out.println("Won the game.");
			// System.exit(0);
		}
	}
	public void play() {
		while (this.deck.canDeal()) {
			this.addPile(this.deck.deal());
			while (this.canMerge()) {
				this.mergeTwoPiles();
			}
		}
		results();
	}
}

public class Main {
	public static void main(String[] args) {
		new Tableau().play();
	}
}

/*

NOTES:
A winning seed is 7389. Put this number in Random() constructor. Another one is 46720. Here is the output for 46720:

Added the two of spades.
Added the queen of clubs.
Merged the queen of clubs and the two of spades.
Added the four of clubs.
Merged the four of clubs and the queen of clubs.
Added the three of diamonds.
Added the queen of hearts.
Merged the queen of hearts and the three of diamonds.
Merged the queen of hearts and the four of clubs.
Added the ace of hearts.
Merged the ace of hearts and the queen of hearts.
Added the eight of spades.
Merged the eight of spades and the ace of hearts.
Added the queen of spades.
Merged the queen of spades and the eight of spades.
Added the six of hearts.
Added the jack of diamonds.
Merged the jack of diamonds and the six of hearts.
Added the ten of diamonds.
Merged the ten of diamonds and the jack of diamonds.
Added the nine of hearts.
Added the seven of hearts.
Merged the seven of hearts and the nine of hearts.
Added the seven of spades.
Added the eight of clubs.
Merged the eight of clubs and the seven of spades.
Merged the eight of clubs and the seven of hearts.
Added the four of diamonds.
Added the ace of spades.
Added the jack of hearts.
Merged the jack of hearts and the ace of spades.
Merged the jack of hearts and the four of diamonds.
Merged the jack of hearts and the eight of clubs.
Merged the jack of hearts and the ten of diamonds.
Added the six of spades.
Added the king of diamonds.
Merged the king of diamonds and the six of spades.
Merged the king of diamonds and the jack of hearts.
Merged the king of diamonds and the queen of spades.
Added the four of spades.
Added the ten of spades.
Merged the ten of spades and the four of spades.
Added the ten of clubs.
Added the eight of hearts.
Added the six of diamonds.
Added the five of hearts.
Added the five of clubs.
Added the queen of diamonds.
Merged the queen of diamonds and the five of clubs.
Merged the queen of diamonds and the five of hearts.
Merged the queen of diamonds and the six of diamonds.
Merged the queen of diamonds and the eight of hearts.
Merged the queen of diamonds and the ten of clubs.
Merged the queen of diamonds and the ten of spades.
Merged the queen of diamonds and the king of diamonds.
Added the four of hearts.
Added the nine of clubs.
Merged the nine of clubs and the four of hearts.
Added the six of clubs.
Merged the six of clubs and the nine of clubs.
Added the seven of clubs.
Merged the seven of clubs and the six of clubs.
Added the jack of spades.
Merged the jack of spades and the seven of clubs.
Added the three of hearts.
Added the three of spades.
Added the two of hearts.
Added the king of hearts.
Merged the king of hearts and the two of hearts.
Merged the king of hearts and the three of spades.
Merged the king of hearts and the three of hearts.
Merged the king of hearts and the jack of spades.
Merged the king of hearts and the queen of diamonds.
Added the seven of diamonds.
Added the five of diamonds.
Merged the five of diamonds and the seven of diamonds.
Added the ace of clubs.
Added the ten of hearts.
Merged the ten of hearts and the ace of clubs.
Merged the ten of hearts and the five of diamonds.
Merged the ten of hearts and the king of hearts.
Added the two of diamonds.
Added the two of clubs.
Added the king of spades.
Merged the king of spades and the two of clubs.
Merged the king of spades and the two of diamonds.
Merged the king of spades and the ten of hearts.
Added the ace of diamonds.
Added the nine of spades.
Merged the nine of spades and the ace of diamonds.
Merged the nine of spades and the king of spades.
Added the three of clubs.
Added the nine of diamonds.
Merged the nine of diamonds and the three of clubs.
Added the five of spades.
Added the jack of clubs.
Merged the jack of clubs and the five of spades.
Merged the jack of clubs and the nine of diamonds.
Merged the jack of clubs and the nine of spades.
Added the eight of diamonds.
Added the king of clubs.
Merged the king of clubs and the eight of diamonds.
Merged the king of clubs and the jack of clubs.
Won the game.

*/