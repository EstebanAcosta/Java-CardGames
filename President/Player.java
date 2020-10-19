package President;

import java.util.ArrayList;
import java.util.Iterator;
/***
 * @author estebanacosta
 */
public class Player
{
    private String name;

    private int playerId;

    private ArrayList<Card> playerCards = new ArrayList<Card>();

    private boolean isPresident;

    private boolean isVicePresident;

    private boolean isScum;

    private boolean isViceScum;

    private boolean isOut;

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

    public boolean isVicePresident()
    {
        return isVicePresident;
    }

    public void setVicePresident(boolean isVicePresident)
    {
        this.isVicePresident = isVicePresident;
    }

    public boolean isPresident()
    {
        return isPresident;
    }

    public void setPresident(boolean isPresident)
    {
        this.isPresident = isPresident;
    }

    public boolean isViceScum()
    {
        return isViceScum;
    }

    public void setViceScum(boolean isViceScum)
    {
        this.isViceScum = isViceScum;
    }

    public boolean isScum()
    {
        return isScum;
    }

    public void setScum(boolean isScum)
    {
        this.isScum = isScum;
    }

    public boolean isOut()
    {
        return isOut;
    }

    public void setOut(boolean isOut)
    {
        this.isOut = isOut;
    }

    public ArrayList<Card> getPlayerHand()
    {
        return playerCards;
    }

    public void addMultipleToPlayerHand(ArrayList<Card> playerCards)
    {
        this.playerCards.addAll(playerCards);
    }

    public void addOneToPlayerHand(Card oneCard)
    {
        this.playerCards.add(oneCard);
    }

    public Card getCard(int whichCard)
    {

        return this.playerCards.get(whichCard);
    }

    public int getNumOfPlayerCards()
    {
        return this.playerCards.size();

    }

    public int getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId(int playerId)
    {
        this.playerId = playerId;
    }
    
    public Card removeOneFromPlayerCards(int position)
    {
        return playerCards.remove(position);
    }

    public void removeMultipleFromPlayerCards(ArrayList<Card> cards)
    {
        int count = 0;

        Iterator<Card> c = playerCards.iterator();

        while (c.hasNext())
        {
            Card nextCard = c.next();

            if (nextCard.equals(cards.get(count)))
            {
                c.remove();

                count++;
            }
        }
    }

    public boolean containsThisCard(Card thisCard)
    {
        for (Card c : playerCards)
        {
            if (c.getSuit() == thisCard.getSuit() && c.getValue() == thisCard.getValue())
            {
                return true;
            }
        }

        return false;
    }

    public void showPlayerCards()
    {

        System.out.println(getName() + "'s Hand: \n");
        for (int i = 0; i < playerCards.size(); i++)
        {

            System.out.print(" ( " + playerCards.get(i) + " ) ");

            if ((i + 1) % 5 == 0 && i > 0)
            {

                System.out.println();
                System.out.println();

            }

        }

        System.out.println();
        System.out.println();

    }

}
