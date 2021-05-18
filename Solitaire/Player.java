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

        for (Entry<Integer, ArrayList<Card>> thatPile : tableau.entrySet())
        {
            if (pile == whichPile)
            {
                for (Card card : thatPile.getValue())
                {
                    Iterator<Card> playersHand = thatPile.getValue().iterator();

                    while (playersHand.hasNext())
                    {
                        Card nextCard = playersHand.next();

                        if (nextCard.equals(card))
                        {

                            playersHand.remove();

                        }
                    }
                }

            }

            whichPile++;

        }
    }

    /****
     * Returns that selected card from their hand
     * @param whichCard
     * @return
     */
    public Card getCardInPlayerCards(int pile, int whichCard)
    {
        return null;
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
                if(c.getSuit() == thisCard.getSuit() && c.getRank() == thisCard.getRank() && c.getColor() == thisCard.getColor())
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

        // This array will contain how many spaces should be placed after
        // the words 'Pile #' This array is only used for display reasons.
        // We want to know how many spaces the program should put
        // right after the words Pile # in order to make the tableau look
        // nice and readable
        int[] howManySpaces = new int[7];

        // helps keep track of where we are in the array
        int count = 0;

        Hashtable<Integer, ArrayList<Card>> tableau = this.tableau;

        System.out.println("Tableau:\n");

        // loop through the tableau table
        for (Entry<Integer, ArrayList<Card>> thatPile : tableau.entrySet())
        {
            // print out the first flipped card in each pile
            System.out.print(thatPile.getValue().get(0) + "  ");

            // get the length of the string representation of each card and store it in the array
            // Subtract 7 to estimate how many spaces more or less we should put after the string
            howManySpaces[count] = thatPile.getValue().get(0).toString().length() - 7;

            count++;

        }

        System.out.println();

        // loop seven times since there are seven piles
        for (int i = 0; i < 7; i++)
        {
            // reset this variable after each iteration
            String spaces = "";

            // loop through the array that contains the number of spaces
            // that should be placed after the string
            for (int j = 0; j < howManySpaces[i]; j++)
            {
                // add that # of spaces to the string
                spaces += " ";
            }

            System.out.print("   Pile " + (i + 1) + spaces);
        }

        System.out.println();
        System.out.println();

    }

}
