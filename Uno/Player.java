package Uno;

import java.util.ArrayList;

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

}
