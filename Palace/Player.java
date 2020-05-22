package Palace;

import java.util.ArrayList;

/***
 * @author estebanacosta
 */
public class Player
{
    private String name;

    private int playerId;

    private ArrayList<Card> playerCards = new ArrayList<Card>();

    private int goalCards;

    private boolean out;

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

    public ArrayList<Card> getPlayerCards()
    {
        return playerCards;
    }

    public void setPlayerCards(ArrayList<Card> playerCards)
    {
        this.playerCards = playerCards;
    }

    public Card getCard(int whichCard)
    {

        return this.playerCards.get(whichCard - 1);
    }

    public int getNumPlayerCards()
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

    public void setIsOutStatus(boolean out)
    {
        this.out = out;
    }

    public boolean isOut()
    {

        return this.out;
    }

    
    public void showAvailableCards()
    {
        int count = 1;

        System.out.println();

        for (Card c : getPlayerCards())
        {

            if (c.isFaceDown() == false)
            {
                System.out.println("Card " + count + ": " + c + "\n");
                count++;

            }

        }

    }

    public void showPalace()
    {
        for (int i = 0; i < playerCards.size(); i++)
        {

        }
    }

    public void showPlayerCards()
    {

        System.out.println(getName() + "'s Hand: \n");
        for (int i = 0; i < playerCards.size(); i++)
        {

            if (!playerCards.get(i).isFaceDown())
            {
                System.out.print(" ( " + playerCards.get(i) + " ) ");
            }

            if ((i + 1) % 5 == 0 && i > 0)
            {

                System.out.println();
                System.out.println();

            }

        }

        System.out.println();
        System.out.println();

    }

    /**
     * Replace the player's card in that position with the new card
     * @param newCard
     * @return
     */
    public Card changeCards(int position, Card newCard)
    {

        position = position - 1;

        Card oldCard = playerCards.get(position);

        playerCards.set(position, newCard);

        playerCards.get(position).setFaceDown(false);

        return oldCard;

    }

}
