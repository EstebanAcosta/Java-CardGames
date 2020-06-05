package Uno;

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

        System.out.println("\n" + num + " cards have been drawn from the deck");

        return cards;
    }

    /**
     * Return a card from the top of the deck
     * @return
     */
    public Card draw()
    {

        System.out.println("\n one card has been drawn from the deck");
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

        for (int i = 0; i < 2; i++)
        {
            for (Color color : Color.values())
            {
                for (Value value : Value.values())
                {
                    if (value == Value.ZERO)
                    {
                        continue;
                    }

                    deck.add(new Card(color, value));

                }
            }
        }

        for (Color color : Color.values())
        {

            deck.add(new Card(SpecialValue.WILD));

            deck.add(new Card(SpecialValue.WILD_DRAW_FOUR));

            deck.add(new Card(color, Value.ZERO));

        }

        this.deck = deck;
    }

    public String toString()
    {

        String Deck = "";

        int numberOfCardsInSuit = 1;
        for (Card c : deck)
        {
            if (numberOfCardsInSuit % 12 == 0)
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
