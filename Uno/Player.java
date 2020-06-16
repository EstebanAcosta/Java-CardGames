package Uno;

import java.util.ArrayList;
import java.util.Iterator;

public class Player
{
    private String name;

    private int playerId;

    private ArrayList<Card> playerCards = new ArrayList<Card>();

    private boolean isOut;

    private boolean pickedUpCards;

    private boolean gotSkipped;

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

    public void pickedUpCardsStatus(boolean pickedUpCards)
    {
        this.pickedUpCards = pickedUpCards;
    }

    public boolean hasPickedUpMultipleCards()
    {
        return this.pickedUpCards;
    }

    public void gotSkippedStatus(boolean gotSkipped)
    {
        this.gotSkipped = gotSkipped;
    }

    public boolean gotSkipped()
    {
        return this.gotSkipped;
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

    public void addToPlayerCards(Card thisCard)
    {
        this.playerCards.add(thisCard);
    }

    public void addMultipleToPlayerCards(ArrayList<Card> cards)
    {
        this.playerCards.addAll(cards);
    }

    public Card removeFromPlayerCards(int position)
    {
        return playerCards.remove(position - 1);
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

    public boolean canPlayHand(Card middleCard)
    {
        for (Card card : this.playerCards)
        {
            if (card.isWild() || card.getColor() == middleCard.getColor() || card.getValue() == middleCard.getValue())
            {
                return true;
            }
        }

        return false;
    }

    public boolean hasDrawTwo()
    {
        for (Card c : playerCards)
        {
            if (c.getValue() == Value.DRAW_TWO)
            {
                return true;
            }
        }

        return false;

    }

    public boolean hasDrawFour()
    {
        for (Card c : playerCards)
        {
            if (c.isWild() && c.getSpecialValue() == SpecialValue.WILD_DRAW_FOUR)
            {
                return true;
            }
        }

        return false;
    }

    public boolean hasWild()
    {
        for (Card c : playerCards)
        {
            if (c.isWild())
            {
                return true;
            }
        }

        return false;
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
