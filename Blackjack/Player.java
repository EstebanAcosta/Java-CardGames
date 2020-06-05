package Blackjack;

import java.util.ArrayList;

/***
 * @author estebanacosta
 */
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

    public Player()
    {

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

    public int getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId(int playerId)
    {
        this.playerId = playerId;
    }

    public void addToPlayerCards(Card thisCard)
    {
        this.playerCards.add(thisCard);
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
     * Prints the cards in the player's hand
     */
    public void showPlayerCards()
    {
        int count = 1;

        System.out.println();

        for (Card c : getPlayerCards())
        {
            if (c.isFaceDown())
            {
                System.out.println("Card " + count + ": ( Card " + count + " )\n");
            }

            else
            {
                System.out.println("Card " + count + ": (" + c + ")\n");
            }

            count++;

        }

    }

    public int getTotalSumOfCards()
    {
        int sum = 0;

        for (Card c : playerCards)
        {
            // if one of the cards in the player
            // hand is an ace,
            if (c.getValue() == Value.ACE)
            {
                // and if adding 11 to the running sum will go over 21, add 1 to the running sum
                if (sum + 11 > 21)
                {
                    sum += 1;
                }

                // if adding 11 to the running sum won't go over 21, add 11 to the running sum
                else
                {
                    sum += 11;
                }

            }

            else
            {
                sum += c.getValueOfCard();
            }

        }

        return sum;

    }

    public boolean over21()
    {

        return (getTotalSumOfCards() > 21 ? true : false);

    }

}
