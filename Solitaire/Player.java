package Solitaire;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;

/***
 * @author estebanacosta
 */
public class Player
{
    private String name;

    private int playerId;

    private Hashtable<Integer, ArrayList<Card>> tableau = new Hashtable<Integer, ArrayList<Card>>();

    public Player(int id)
    {
        setPlayerId(id);
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId(int playerId)
    {
        this.playerId = playerId;
    }

    public Hashtable<Integer, ArrayList<Card>> getTableau()
    {
        return tableau;
    }

    public void setTableau(Hashtable<Integer, ArrayList<Card>> tableau)
    {
        this.tableau = tableau;
    }

    /**
     * In order to start a foundation in solitaire we need an ace,
     * this methods allows us to quickly find an ace before doing
     * anything else
     * @return true if there is an ace in the tableau, otherwise return false
     */
    public boolean hasAceInTableau()
    {
        for (Entry<Integer, ArrayList<Card>> thatPile : tableau.entrySet())
        {
            for (Card c : thatPile.getValue())
            {
                if (c.getRank() == Rank.ACE)
                {
                    return true;
                }
            }
        }

        return false;
    }

    public int getNumCardsInTableauPile(int pile)
    {
        int sizeOfPile = 0;

        int whichPile = 1;

        // Start looping through the piles in the tableau
        for (Entry<Integer, ArrayList<Card>> thatPile : tableau.entrySet())
        {
            if (pile == whichPile)
            {
                // store the number of cards in that pile
                sizeOfPile = thatPile.getValue().size();

                break;
            }

            whichPile++;
        }

        return sizeOfPile;

    }

    /***
     * Add multiple sorted cards to the end of a specific pile in the tableau
     * @param pile
     * @param newCards
     */
    public void addMultipleToTableau(int pile, ArrayList<Card> newCards)
    {

        int whichPile = 1;

        // Start looping through the piles in the tableau
        for (Entry<Integer, ArrayList<Card>> thatPile : tableau.entrySet())
        {
            if (pile == whichPile)
            {
                // add all the new sorted cards to the end of the selected pile
                thatPile.getValue().addAll(newCards);

                break;
            }

            whichPile++;
        }

    }

    /***
     * Add a new card to the end of a specific pile in the tableau
     * @param pile
     * @param card
     */
    public void addOneToTableau(int pile, Card card)
    {
        int whichPile = 1;

        // Start looping through the piles in the tableau
        for (Entry<Integer, ArrayList<Card>> thatPile : tableau.entrySet())
        {
            if (pile == whichPile)
            {
                // add all the new card to the end of the selected pile
                thatPile.getValue().add(card);

                break;
            }

            whichPile++;
        }
    }

    /***
     * Removes one card from the tableau in a specific pile
     * @param pile
     * @param position
     * @return the card that is being removed
     */
    public Card removeOneFromTableau(int pile, int position)
    {
        int whichPile = 1;

        Card removed = null;

        // Start looping through the piles in the tableau
        for (Entry<Integer, ArrayList<Card>> thatPile : tableau.entrySet())
        {
            if (pile == whichPile)
            {
                removed = thatPile.getValue().remove(position - 1);

                break;

            }

            whichPile++;
        }

        return removed;
    }

    /****
     * Removes the selected cards from the player's hand
     * @param cards
     */
    public void removeMultipleFromPlayerCards(int pile, ArrayList<Card> cards)
    {
        int whichPile = 1;

        // loop through each pile in the tableaus
        for (Entry<Integer, ArrayList<Card>> thatPile : tableau.entrySet())
        {
            if (pile == whichPile)
            {
                // loop through the pile's cards
                for (Card card : thatPile.getValue())
                {
                    // create an iterator using the cards player wants to remove
                    Iterator<Card> playersHand = thatPile.getValue().iterator();

                    // loop through the pile of cards
                    while (playersHand.hasNext())
                    {
                        // get the next card in the pile
                        Card nextCard = playersHand.next();

                        if (nextCard.equals(card))
                        {

                            // remove that card from the
                            playersHand.remove();

                        }
                    }
                }

            }

            whichPile++;

        }
    }

    /****
     * Loop through each pile in the tableau until the program finds the pile
     * that the player selected, go through that pile and try to find the card
     * the player wants
     * @param whichCard
     * @return that selected card from the tableau
     */
    public Card getCardInPlayerTableau(int pile, int whichCard)
    {
        Card thatCard = null;

        int whichPile = 1;

        for (Entry<Integer, ArrayList<Card>> thatPile : tableau.entrySet())
        {
            if (pile == whichPile)
            {
                int position = 1;

                for (Card card : thatPile.getValue())
                {
                    if (position == whichCard)
                    {
                        thatCard = card;

                        break;
                    }

                    position++;

                }

                break;
            }
        }

        return thatCard;
    }

    /***
     * Determines if this player has a specific card in their hand
     * @param thisCard
     * @return
     */
    public boolean containsThisCard(Card thisCard)
    {
        for (Entry<Integer, ArrayList<Card>> thatPile : tableau.entrySet())
        {
            for (Card c : thatPile.getValue())
            {
                if (c.getSuit() == thisCard.getSuit() && c.getRank() == thisCard.getRank() && c.getColor() == thisCard.getColor())
                {
                    return true;
                }
            }
        }
        return false;
    }

    /***
     * Shows the player's hand
     */
    public void showPlayerTableau()
    {

        Hashtable<Integer, ArrayList<Card>> tableau = this.tableau;

        System.out.println(getName() + "'s Tableau:\n");

        int count = 1;

        // loop through the tableau table
        for (Entry<Integer, ArrayList<Card>> thatPile : tableau.entrySet())
        {
            // print out the first flipped card in each pile
            System.out.println(thatPile.getValue().get(0) + "  ");

            System.out.println("Pile " + count);
            
            System.out.println();
            System.out.println();

            count++;

        }

        System.out.println();
        System.out.println();

    }

}
