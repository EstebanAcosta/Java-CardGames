package President;

import java.util.ArrayList;

/***
 * @author estebanacosta
 */
public class Player
{
    private String name;

    private int playerId;

    private ArrayList<Card> playerCards = new ArrayList<Card>();

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

    public ArrayList<Card> getPlayerHand()
    {
        return playerCards;
    }

    public void addMultipleToPlayerHand(ArrayList<Card> remaininingCards)
    {
        this.playerCards = remaininingCards;
    }

    public void addOneToPlayerHand(Card thisCard)
    {
        this.playerCards.add(thisCard);
    }

    public Card getCard(int whichCard)
    {

        return this.playerCards.get(whichCard - 1);
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
