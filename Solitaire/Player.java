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

    /***
     * In order to determine if it's possible for the player to start working
     * with the empty pile, this method determines if there are any kings in the
     * tableau
     * @return true if there are kings in the tableau, false if there aren't
     */
    public boolean hasKingInTableau()
    {
        for (Entry<Integer, ArrayList<Card>> thatPile : tableau.entrySet())
        {

            for (Card c : thatPile.getValue())
            {
                if (c.isUnflipped() == false && c.getRank() == Rank.KING)
                {
                    return true;
                }
            }

        }

        return false;
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
                if (c.isUnflipped() == false && c.getRank() == Rank.ACE)
                {
                    return true;
                }
            }

        }

        return false;
    }

    public ArrayList<Card> findAces()
    {

        int pile = 1;

        ArrayList<Card> allAces = new ArrayList<Card>();

        // loop through each pile of the tableau
        for (Entry<Integer, ArrayList<Card>> thatPile : tableau.entrySet())
        {

            int position = 0;

            // create an iterator using the cards player wants to remove

            Iterator<Card> pileCards = thatPile.getValue().iterator();

            // loop through each card in the pile
            while (pileCards.hasNext())
            {
                // get the next card in the pile
                Card nextCard = pileCards.next();

                // if this card is face up and is an ace
                if (nextCard.isUnflipped() == false && nextCard.getRank() == Rank.ACE)
                {

                    // if that pile has more than one card and the next card is unflipped
                    if (thatPile.getValue().size() > 1 && position + 1 < thatPile.getValue().size() &&
                    thatPile.getValue().get(position + 1).isUnflipped())
                    {
                        // Flip that card up
                        thatPile.getValue().get(position + 1).setUnflipped(false);
                    }

                    allAces.add(nextCard);

                    // remove that card from the pile
                    pileCards.remove();

                }

                // if this card is facing down, then that means there
                // is no reason to continue looping through the rest of cards
                // since every card after this face down card is also face down
                // and we are only interested in the face up cards that are aces
                else if (nextCard.isUnflipped())
                {
                    break;
                }

                position++;

            }

            pile++;
        }

        return allAces;

    }

    public int getNumCardsInTableauPile(int pile)
    {

        // store the number of cards in that pile
        return tableau.get(pile).size();

    }

    /***
     * Add multiple sorted cards to the end of a specific pile in the tableau
     * @param pile
     * @param newCards
     */
    public void addMultipleToTableau(int pile, ArrayList<Card> newCards)
    {

        tableau.get(pile).addAll(newCards);

    }

    /***
     * Add a new card to the end of a specific pile in the tableau
     * @param pile
     * @param card
     */
    public void addOneToTableau(int pile, Card card)
    {
        tableau.get(pile).add(card);

    }

    /***
     * Removes one card from the tableau in a specific pile
     * @param pile
     * @param position
     * @return the card that is being removed
     */
    public Card removeOneFromTableau(int pile, int position)
    {

        return tableau.get(pile).remove(position);
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
                for (Card card : cards)
                {
                    // create an iterator using the cards player wants to remove
                    Iterator<Card> pileCards = thatPile.getValue().iterator();

                    // loop through the pile of cards
                    while (pileCards.hasNext())
                    {
                        // get the next card in the pile
                        Card nextCard = pileCards.next();

                        if (nextCard.equals(card))
                        {

                            // remove that card from the pile
                            pileCards.remove();

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

        return tableau.get(pile).get(whichCard - 1);
    }

    /***
     * Determines if this player has a specific card in their tableau
     * @param thisCard
     * @return true if that card exists in their tableau, otherwise return false
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
     * Determines if the tableau has an empty pile
     * @return true if there is an empty pile, false if there isn't an empty pile
     */
    public boolean isThereEmptySpaceInTableau()
    {
        // loop through the piles of the tableau
        for (Entry<Integer, ArrayList<Card>> thatPile : tableau.entrySet())
        {
            // if this pile has at least one card
            if (thatPile.getValue().size() == 0)
            {
                return true;
            }
        }

        return false;
    }

    /***
     * Shows the player's tableau
     */
    public void showPlayerTableau()
    {

        Hashtable<Integer, ArrayList<Card>> tableau = this.tableau;

        System.out.println(getName() + "'s Tableau:\n");

        int pileNum = 0;

        String[][] t = new String[7][10];
        
        
        
        for (int row = 0; row < t.length; row++)
        {
            for (int col = 0; col < t[row].length; col++)
            {
                
                t[row][col] = "";
            }
            
        }

        // loop through the tableau table
        for (Entry<Integer, ArrayList<Card>> thatPile : tableau.entrySet())
        {

            int posit = 0;
            
            for (Card c : thatPile.getValue())
            {

                if (c.isUnflipped() == false)
                {
                    t[posit][pileNum] = "( " + c + " )   ";

                }

                else
                {
                    t[posit][pileNum] = "( Card " + (posit + 1) + " )                  ";

                }

                posit++;
            }

            pileNum++;

        }
        
        
        for(int i = 0 ; i < t.length; i++)
        {
            System.out.print("Pile " + (i + 1) + "                      "); 
        }
        
        System.out.println();
 

        for (int row = 0; row < t.length; row++)
        {
            for (int col = 0; col < t[row].length; col++)
            {
                
                System.out.print(t[row][col]);
            }
            
            System.out.println();
            System.out.println();
        }

    }

}
