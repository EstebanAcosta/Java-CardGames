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

    private ArrayList<Card> playerPalace = new ArrayList<Card>();

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

    public void setPlayerPalace(ArrayList<Card> playerPalace)
    {
        this.playerPalace = playerPalace;
    }

    public ArrayList<Card> getPlayerPalace()
    {
        return this.playerPalace;
    }

    public void showPlayerCards()
    {
        int count = 1;

        System.out.println();

        for (Card c : getPlayerCards())
        {

            System.out.println("Card " + count + ": " + c + "\n");
            count++;

        }

    }

    public void showPalace()
    {
        for (int i = 0; i < playerPalace.size(); i++)
        {
            System.out.println(playerPalace.get(i));
        }
    }

    public Card removeFromPalace(int position)
    {

        return playerPalace.remove(position - 1);
    }

    public Card removeFromPlayerCards(int position)
    {
        return playerCards.remove(position - 1);
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
