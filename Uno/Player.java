package Uno;

import java.util.ArrayList;

public class Player
{
    private String name;

    private int playerId;

    private ArrayList<Card> playerCards = new ArrayList<Card>();

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

    public int getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId(int playerId)
    {
        this.playerId = playerId;
    }

    public ArrayList<Card> getPlayerCards()
    {
        return playerCards;
    }

    public void setPlayerCards(ArrayList<Card> playerCards)
    {
        this.playerCards = playerCards;
    }

    public boolean isOut()
    {
        return this.isOut;
    }

    public void setOutStatus(boolean isOut)
    {
        this.isOut = isOut;
    }

    /***
     * Returns the card in the player's hand
     * @param whichCard
     * @return
     */
    public Card getCardInPlayerCards(int whichCard)
    {

        return this.playerCards.get(whichCard - 1);
    }

    /***
     * How many cards are in the player's hand
     * @return
     */
    public int getNumPlayerCards()
    {
        return this.playerCards.size();

    }

    /***
     * Prints the cards in the player's hand
     */
    public void showPlayerCards()
    {
        int count = 1;

        System.out.println();

        for (Card c : getPlayerCards())
        {

            System.out.println("Card " + count + ": (" + c + ")\n");

            count++;

        }

    }

}
