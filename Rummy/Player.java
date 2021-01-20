package Rummy;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

/***
 * @author estebanacosta
 */
public class Player
{
    private String name;

    private int playerId;

    private ArrayList<Card> playerCards = new ArrayList<Card>();

    private Hashtable<Integer, ArrayList<Card>> melds = new Hashtable<Integer, ArrayList<Card>>();

    private int pointsWon;

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

    public int getPointsWon()
    {
        return pointsWon;
    }

    public void addsPointsWon(int pointsWon)
    {
        this.pointsWon += pointsWon;

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

    /***
     * Add any sets or runs in this player's list of melds
     * @param set
     */
    public void addMeld(ArrayList<Card> set)
    {
        // the key should be in which order that set/run has been added to the list of melds
        // the value should be the run/set
        melds.put(melds.size() + 1, set);
    }
    
    public boolean isASet()
    {
        return true;
    }
    
    public boolean isARun()
    {
        return false;
    }
    
    public void findSets()
    {
        
    }
    
    public void findRuns()
    {
        
    }

    public void removeMultipleFromPlayerCards(ArrayList<Card> cards)
    {
        int count = 0;

        Iterator<Card> c = playerCards.iterator();

        while (c.hasNext())
        {
            Card nextCard = c.next();

            if (count < cards.size() && nextCard.equals(cards.get(count)))
            {
                c.remove();

                count++;
            }
        }
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
     * Determines if this player has a specific card in their hand
     * @param thisCard
     * @return
     */
    public boolean containsThisCard(Card thisCard)
    {
        for (Card c : playerCards)
        {
            if (c.getSuit() == thisCard.getSuit() && c.getRank() == thisCard.getRank())
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

            System.out.print((i + 1) + ": ( " + playerCards.get(i) + " ) " + playerCards.get(i).getRank() + " appears " + howManyTimesThisRankAppears(i) + "x\n");

        }

        System.out.println();
        System.out.println();

    }

    /***
     * Creates an array list of all the ranks the player has in their hand
     * @return
     */
    public ArrayList<Rank> getAllRanksPlayerHas()
    {
        ArrayList<Rank> ranks = new ArrayList<Rank>();

        for (int i = 0; i < playerCards.size(); i++)
        {
            if (!ranks.contains(playerCards.get(i).getRank()))
            {
                ranks.add(playerCards.get(i).getRank());
            }

        }

        return ranks;
    }

    /***
     * Calculates how many times a certain rank appears in the player's hand
     * and shows the rank and the # of times they appear on the screen
     * @param cardPosition
     * @return
     */
    public void showHowManyTimesThisRankAppears()
    {
        int count = 0;

        // get an array list of all the ranks this player has in their hand
        ArrayList<Rank> ranks = getAllRanksPlayerHas();

        // create a hash table where the key is rank and the value is the number of times that rank appears in a player's hand
        Hashtable<Rank, Integer> timesRankAppears = new Hashtable<Rank, Integer>();

        // loop through the ranks the player has
        for (Rank rank : ranks)
        {
            // loop through the cards the player has
            for (int i = 0; i < playerCards.size(); i++)
            {
                // if the rank the outer loop is on is equal to this card's rank
                if (playerCards.get(i).getRank() == rank)
                {
                    // add one to count
                    count++;
                }
            }

            // place the rank and the number of times this rank appears in the table
            timesRankAppears.put(rank, count);

            // reset the counter when it's time for the outer loop to move on to the next rank
            count = 0;

        }

        System.out.println("How many times the rank appears in " + getName() + "'s hand");
        for (Map.Entry<Rank, Integer> entry : timesRankAppears.entrySet())
        {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "X");

        }
        System.out.println();

    }

    public int howManyTimesThisRankAppears(int cardPosition)
    {
        int count = 0;

        for (int i = 0; i < playerCards.size(); i++)
        {
            if (playerCards.get(cardPosition - 1).getRank() == playerCards.get(i).getRank())
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

            // if the card of that specific rank appears that same number of times or more
            // then
            if (numTimesRankAppears >= howManyCardsToPutDown)
            {
                // that must mean that at least one of the cards in the player's hand appears more than
                // or equal that set number of times
                return true;
            }
        }

        return false;

    }

    /***
     * Determines if the player can play on their turn. Checks if any of their cards is bigger than the middle card, if they have a two,
     * or if they have multiple cards of the same rank
     * @param middleCard
     * @param howManyCardsOfSameRankToPutDown
     * @return
     */
    public boolean canPlayACard(Card middleCard, int howManyCardsOfSameRankToPutDown)
    {

        int count = 1;
        // loop through the player's hand
        for (Card c : playerCards)
        {
            // if the player has a two in their hand
            if (c.getRank() == Rank.TWO)
            {

                return true;

            }
            // or has at least one card that's higher in value than the top card
            else if (c.getValueOfCard() > middleCard.getValueOfCard())
            {
                if (howManyTimesThisRankAppears(count) >= howManyCardsOfSameRankToPutDown)
                {
                    return true;
                }

            }

            count++;

        }

        return false;
    }

}
