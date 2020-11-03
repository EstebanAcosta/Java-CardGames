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

    /***
     * Determines if this player has a specific card in their hand
     * @param thisCard
     * @return
     */
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

    /***
     * Shows the player's hand
     */
    public void showPlayerCards()
    {

        System.out.println("\n" + getName() + "'s Hand: \n");

        for (int i = 0; i < playerCards.size(); i++)
        {

            System.out.print((i + 1) + ": ( " + playerCards.get(i) + " )\n");

        }

        System.out.println();
        System.out.println();

    }

    /***
     * Shows the player's hand but now shows how many times each rank appears in their hand
     */
    public void showPlayerCardsWithNumTimesTheyAppear()
    {
        System.out.println("\n" + getName() + "'s Hand: \n");

        for (int i = 0; i < playerCards.size(); i++)
        {

            System.out.print((i + 1) + ": ( " + playerCards.get(i) + " ) " + playerCards.get(i).getValue() + " appears " + howManyTimesThisRankAppears(i) + "x\n");

        }

        System.out.println();
        System.out.println();

    }

    public int howManyTimesThisRankAppears(int cardPosition)
    {
        int count = 0;

        for (int i = 0; i < playerCards.size(); i++)
        {
            if (playerCards.get(cardPosition).getValue() == playerCards.get(i).getValue())
            {
                count++;
            }
        }
        return count;
    }

    /***
     * Checks to see if the user has two or more of the same card in their hand.
     * for example, if the user wants to to be able to put down two cards of the same rank down in the middle
     * This method checks to see if a certain rank appears twice. If a certain rank appears twice or whatever
     * number of times the user wants then the method will return true and if no rank appears twice or whatever
     * number of times the user specified then then the method will return false. This will help the game
     * determine whether or not the user can put down two cards or more cards of the same rank down
     * @param numTimesSameRankAppears
     * @return
     */
    public boolean hasManyCardsOfSameRank(int howManyCardsToPutDown)
    {
        // take a card from the cards in the player's hand
        for (int i = 0; i < playerCards.size(); i++)
        {
            // store that card's rank
            int rank = playerCards.get(i).getValueOfCard();

            // have a variable to check to see how many times that card appears in the player's hand
            int numTimesRankAppears = 0;

            // loop through the player's hand
            for (int j = 0; j < playerCards.size(); j++)
            {
                // check if the randomly selected card's rank is equal to the rank of the card
                if (rank == playerCards.get(j).getValueOfCard())
                {
                    // add one to count
                    numTimesRankAppears++;
                }
            }

            if (numTimesRankAppears >= howManyCardsToPutDown)
            {
                return true;
            }
        }

        return false;

    }

    /****
     * Returns that selected card from their hand
     * @param whichCard
     * @return
     */
    public Card getCardInPlayerCards(int whichCard)
    {
        return this.playerCards.get(whichCard - 1);
    }

    /***
     * Determines if the player can play on their turn. Checks if any of their cards is bigger than the middle card, if they have a two,
     * or if they have multiple cards of the same rank
     * @param middleCard
     * @return
     */
    public boolean canPlayACard(Card middleCard)
    {
        for (Card c : playerCards)
        {
            if (c.getValue() == Value.TWO || c.getValueOfCard() > middleCard.getValueOfCard())
            {
                return true;
            }

        }

        return false;
    }

}
