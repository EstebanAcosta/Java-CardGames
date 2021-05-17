package Solitaire;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
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
        
        for (Entry<Integer, ArrayList<Card>> entry : tableau.entrySet())
        {
            if (pile == whichPile)
            {
                sizeOfPile = entry.getValue().size();
                
                break;
            }
            
            whichPile++;
        }
        
        return sizeOfPile;

    }

    public void addMultipleToTableau(int pile, ArrayList<Card> newCards)
    {
        
        int whichPile = 1;
        
        for (Entry<Integer, ArrayList<Card>> entry : tableau.entrySet())
        {
            if (pile == whichPile)
            {
                
            }
            
            whichPile++;
        }
        
    }

    public void addOneToTableau(int pile, Card card)
    {
  int whichPile = 1;
        
        for (Entry<Integer, ArrayList<Card>> entry : tableau.entrySet())
        {
            if (pile == whichPile)
            {
                
            }
            
            whichPile++;
        }
    }

    public Card removeOneFromTableau(int pile, Card card)
    {
        int whichPile = 1;
        
        Card removed = null;
        
        for (Entry<Integer, ArrayList<Card>> entry : tableau.entrySet())
        {
            if (pile == whichPile)
            {
                
            }
            
            whichPile++;
        }
        
        
        return removed;
    }

    /****
     * Removes the selected cards from the player's hand
     * @param cards
     */
    public void removeMultipleFromPlayerCards(ArrayList<Card> cards)
    {

        for (Card card : cards)
        {
            Iterator<Card> playersHand = playerCards.iterator();

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

    /****
     * Returns that selected card from their hand
     * @param whichCard
     * @return
     */
    public Card getCardInPlayerCards(int pile , int whichCard)
    {
        return this.playerCards.get(whichCard - 1);
    }

    /***
     * Determines if this player has a specific card in their hand
     * @param thisCard
     * @return
     */
    public boolean containsThisCard(Card thisCard)
    {
        for (Entry<Integer, ArrayList<Card>> entry : tableau.entrySet())
        {
            
        }
        return false;
    }

    public int howManyTimesThisCardAppears(Card card, ArrayList<Card> list)
    {
        int count = 0;

        for (Card c : list)
        {
            if (c == card)
            {
                count++;
            }
        }

        return count;
    }

    /***
     * Shows the player's hand
     */
    public void showPlayerTableau()
    {

        System.out.println(getName() + "'s Hand: \n");
        
        
//        for (int i = 0; i < playerCards.size(); i++)
//        {
//
//            if (playerCards.get(i).isUnflipped())
//            {
//                System.out.print(" ( CARD " + (i + 1) + " )");
//            }
//            else
//            {
//                System.out.print(" ( " + playerCards.get(i) + " ) ");
//            }
//
//            if ((i + 1) % 5 == 0 && i > 0)
//            {
//
//                System.out.println();
//                System.out.println();
//
//            }
//
//        }

        System.out.println();
        System.out.println();

    }

}
