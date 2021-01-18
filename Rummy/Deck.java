package Rummy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

/***
 * @author estebanacosta
 */
public class Deck
{

    private Stack<Card> deck = new Stack<Card>();

    public Deck()
    {
        setUpDeck();
    }

    public Stack<Card> getDeck()
    {
        return this.deck;
    }

    public int getSize()
    {
        return this.deck.size();

    }

    /**
     * Draw any number of cards from the deck
     * @param num
     * @return
     */
    public ArrayList<Card> draw(int num)
    {
        ArrayList<Card> cards = new ArrayList<Card>();

        for (int i = 0; i < num; i++)
        {
            cards.add(deck.pop());
        }

        return cards;
    }

    /**
     * Return a card from the top of the deck
     * @return
     */
    public Card draw()
    {

        return deck.pop();

    }

    public void shuffle()

    {
        Collections.shuffle(deck);

        System.out.println("Deck has been shuffled\n");
    }

    public void setUpDeck()
    {
        Stack<Card> deck = new Stack<Card>();

        for (Suit suit : Suit.values())
        {
            for (Rank value : Rank.values())
            {

                deck.add(new Card(suit, value));

            }
        }

        this.deck = deck;
    }

    public String toString()
    {

        String Deck = "";

        int numberOfCardsInSuit = 1;
        for (Card c : deck)
        {
            if (numberOfCardsInSuit % 13 == 0)
            {
                Deck += c.toString() + "\n\n";
                numberOfCardsInSuit = 0;
            }

            else
            {
                Deck += c.toString() + "\n";
            }

            numberOfCardsInSuit++;
        }

        return Deck;

    }

}
