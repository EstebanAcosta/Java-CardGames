package Palace;

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
     * Returns the card in the player's palace
     * @param whichCard
     * @return
     */
    public Card getCardInPlayerPalace(int whichCard)
    {

        return this.playerPalace.get(whichCard - 1);
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

    /**
     * Determines if the player is out of the game
     * @param out
     */
    public void setIsOutStatus(boolean out)
    {
        this.out = out;
    }

    /***
     * Returns the player's out status
     * @return
     */
    public boolean isOut()
    {

        return this.out;
    }

    /***
     * Sets up the player's palace
     * @param playerPalace
     */
    public void setPlayerPalace(ArrayList<Card> playerPalace)
    {
        this.playerPalace = playerPalace;
    }

    public ArrayList<Card> getPlayerPalace()
    {
        return this.playerPalace;
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

            System.out.println("Card " + count + ": " + c + "\n");
            count++;

        }

    }

    /***
     * Prints the cards in the player's palace
     */
    public void showPlayerPalace()
    {
        int count = 1;

        System.out.println("Player's Palace:");
        System.out.println();

        for (Card card : playerPalace)
        {

            if (!card.isFaceDown())
            {
                System.out.print("( " + card + " ) ");

            }
        }

        System.out.println();
        System.out.println();

        for (Card card : playerPalace)
        {

            if (card.isFaceDown())
            {
                System.out.print("( Card " + count + " )");
                count++;
            }
        }

        System.out.println();
        System.out.println();
    }

    /***
     * Helper function that tells you how many cards in the player's hand are available
     * @return
     */
    public ArrayList<Integer> getAvailablePlayerCards()
    {
        ArrayList<Integer> availablePlayerCards = new ArrayList<Integer>();

        for (int i = 0; i < playerCards.size(); i++)
        {

            availablePlayerCards.add(i+1);

        }

        return availablePlayerCards;
    }

    public Card removeFromPlayerPalace(int position)
    {

        return playerPalace.remove(position - 1);
    }

    public void removeMultipleFromPlayerPalace(ArrayList<Card> cards)
    {
        int count = 0;

        Iterator<Card> c = playerPalace.iterator();

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

    public void addToPlayerPalace(Card thisCard)
    {
        this.playerPalace.add(thisCard);
    }

    public void addMultipleToPlayerPalace(ArrayList<Card> cards)
    {
        this.playerPalace.addAll(cards);
    }

    public void addToPlayerCards(Card thisCard)
    {
        this.playerPalace.add(thisCard);
    }

    public void addMultipleToPlayerCards(ArrayList<Card> cards)
    {
        this.playerCards.addAll(cards);
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
