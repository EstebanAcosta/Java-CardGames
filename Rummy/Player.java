package Rummy;

import java.util.ArrayList;
import java.util.Collections;
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

    private Hashtable<Integer, ArrayList<Card>> sets = new Hashtable<Integer, ArrayList<Card>>();

    private Hashtable<Integer, ArrayList<Card>> runs = new Hashtable<Integer, ArrayList<Card>>();

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

    public Hashtable<Integer, ArrayList<Card>> getRuns()
    {
        return this.runs;
    }

    public Hashtable<Integer, ArrayList<Card>> getSets()
    {
        return this.sets;
    }

    public ArrayList<Card> getPlayerHand()
    {
        return playerCards;
    }

    public int getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId(int playerId)
    {
        this.playerId = playerId;
    }

    public int getNumOfPlayerCards()
    {
        return this.playerCards.size();

    }

    public void addMultipleToPlayerHand(ArrayList<Card> playerCards)
    {
        this.playerCards.addAll(playerCards);
    }

    public void addOneToPlayerHand(Card oneCard)
    {
        this.playerCards.add(oneCard);
    }

    public Card removeOneFromPlayerCards(int position)
    {
        return playerCards.remove(position - 1);
    }

    /****
     * Removes the selected cards from the player's hand
     * @param cards
     */
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

    public boolean hasASet()
    {
        // find all possible sets
        findSets();

        if (sets.size() > 0)
        {
            return true;
        }

        return false;
    }

    public boolean hasARun()
    {
        // find all possible runs
        findRuns();

        if (runs.size() > 0)
        {
            return true;
        }

        return false;
    }

    public Hashtable<Integer, ArrayList<Card>> findSets()
    {

        ArrayList<Card> copyPlayerCards = new ArrayList<Card>();

        copyPlayerCards.addAll(playerCards);

        Collections.sort(copyPlayerCards);

        // for(Card c : copyPlayerCards)
        // {
        // System.out.println(c);
        // }

        // make a hash table of all the ranks and how many times they appear in the player's hand
        Hashtable<Rank, Integer> timesRankAppears = howManyTimesThisRankAppears();

        // loop through the hash table
        for (Entry<Rank, Integer> entry : timesRankAppears.entrySet())
        {
            ArrayList<Card> set = new ArrayList<Card>();

            // if a certain rank appears four times or three times in a hash table
            if (entry.getValue() == 4 || entry.getValue() == 3)
            {

                Iterator<Card> currentPlayerCards = copyPlayerCards.iterator();

                // continue looping until there are no cards left
                while (currentPlayerCards.hasNext())
                {
                    // get the next card in the current player's hand
                    Card nextCard = currentPlayerCards.next();

                    // if the rank of that card matches with the key in the hash table
                    if (nextCard.getRank() == entry.getKey())
                    {

                        // add that card to the set array list
                        set.add(nextCard);

                        // and remove that card from the current player's hand
                        currentPlayerCards.remove();

                    }
                }

                sets.put(set.get(0).getValueOfCard(), set);

            }
        }

        return sets;
    }

    /***
     * @return a hash table of all the possible runs that can be formed from this player's hand
     */
    public Hashtable<Integer, ArrayList<Card>> findRuns()
    {

        Hashtable<Suit, Integer> timesSuitAppears = howManyTimesThisSuitAppears();

        // loop through the hash table
        for (Entry<Suit, Integer> entry : timesSuitAppears.entrySet())
        {
            ArrayList<Card> run = new ArrayList<Card>();

            // if there is a suit that appears three times or more in the player's hand
            if (entry.getValue() >= 3)
            {
                // get a list of cards that all have the same suit
                ArrayList<Card> cardsOfSameSuit = getAllCardsOfThatSuit(entry.getKey());

                // sort the list in order
                Collections.sort(cardsOfSameSuit);

                // make a deep copy of the list
                ArrayList<Card> copyList = new ArrayList<Card>(cardsOfSameSuit);

                // continue looping through the copy list
                while (copyList.size() >= 3)
                {
                    run = new ArrayList<Card>();

                    // for(Card c : copyList)
                    // {
                    // System.out.println(c);
                    // }
                    //
                    // System.out.println();

                    // loop through the list of cards of the same suit
                    for (int i = 0; i < copyList.size() - 1; i++)
                    {
                        // if this card's value when added one to it is equal to the next card's value
                        if ((copyList.get(i).getValueOfCard() + 1) == copyList.get(i + 1).getValueOfCard())
                        {
                            // add this card to the run
                            run.add(copyList.get(i));

                            // add the subsequent card to the run
                            run.add(copyList.get(i + 1));
                        }

                        else
                        {
                            // break out of this loop because this isn't a run or we are at the end of the loop
                            break;
                        }

                    }

                    // We are going to iterate through the run in order to remove duplicates
                    Iterator<Card> r = run.iterator();

                    // loop through the run
                    while (r.hasNext())
                    {
                        // get the next card
                        Card nextCard = r.next();

                        // if this card appears more than once in the list
                        if (howManyTimesThisCardAppears(nextCard, run) > 1)
                        {
                            // remove it from the list
                            r.remove();
                        }
                    }

                    // if the run that this algorithm has created has at least more than three cards in it
                    if (run.size() >= 3)
                    {
                        // put this run in the table of runs
                        runs.put(runs.size() + 1, run);
                    }

                    // remove the head of the list
                    copyList.remove(0);

                }

            }

        }

        return runs;
    }

    public int howManyTimesThisCardAppears(Card card, ArrayList<Card> list)
    {
        int count = 0;

        for (Card c : list)
        {
            if (c == card)
            {
                count++;
            }
        }

        return count;
    }

    /***
     * @param suit
     * @return a list of cards with the same suit
     */
    public ArrayList<Card> getAllCardsOfThatSuit(Suit suit)
    {

        ArrayList<Card> cardsOfThisSuit = new ArrayList<Card>();

        // loop through the list
        for (Card c : playerCards)
        {
            // if this card has the same suit we are looking for
            if (c.getSuit() == suit)
            {
                // add it to the list
                cardsOfThisSuit.add(c);
            }
        }

        return cardsOfThisSuit;
    }

    public void combineRuns()
    {

    }

    /***
     * @return an array list of all the ranks the player has in their hand
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
     * @return an array list of all the suits the player has in their hand
     */
    public ArrayList<Suit> getAllSuitsPlayerHas()
    {

        ArrayList<Suit> suits = new ArrayList<Suit>();

        for (int i = 0; i < playerCards.size(); i++)
        {
            if (!suits.contains(playerCards.get(i).getSuit()))
            {
                suits.add(playerCards.get(i).getSuit());
            }

        }

        return suits;
    }

    /***
     * Calculates how many times a certain rank appears in the player's hand
     * @param cardPosition
     * @return
     */
    public Hashtable<Rank, Integer> howManyTimesThisRankAppears()
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

        return timesRankAppears;
    }

    /***
     * Calculates how many times a certain suit appears in the player's hand
     */
    public Hashtable<Suit, Integer> howManyTimesThisSuitAppears()
    {
        int count = 0;

        // get an array list of all the ranks this player has in their hand
        ArrayList<Suit> suits = getAllSuitsPlayerHas();

        // create a hash table where the key is rank and the value is the number of times that rank appears in a player's hand
        Hashtable<Suit, Integer> timesSuitAppears = new Hashtable<Suit, Integer>();

        // loop through the ranks the player has
        for (Suit suit : suits)
        {
            // loop through the cards the player has
            for (int i = 0; i < playerCards.size(); i++)
            {
                // if the rank the outer loop is on is equal to this card's rank
                if (playerCards.get(i).getSuit() == suit)
                {
                    // add one to count
                    count++;
                }
            }

            // place the rank and the number of times this rank appears in the table
            timesSuitAppears.put(suit, count);

            // reset the counter when it's time for the outer loop to move on to the next rank
            count = 0;

        }

        return timesSuitAppears;
    }

    /****
     * Prints all the runs this player has to the screen
     */
    public void showRuns()
    {
        System.out.println(getName() + "'s runs:");

        // loop through the list of runs
        for (Map.Entry<Integer, ArrayList<Card>> entry : runs.entrySet())
        {
            // print out the key
            System.out.print(entry.getKey() + " ");

            // print out the run or set associated with that key
            for (Card c : entry.getValue())
            {
                System.out.print(" (" + c + ") ");
            }

            System.out.println();
        }

    }

    /****
     * Prints all the sets this player has to the screen
     */
    public void showSets()
    {
        System.out.println(getName() + "'s sets:");

        // loop through the list of sets
        for (Map.Entry<Integer, ArrayList<Card>> entry : sets.entrySet())
        {
            // print out the key
            System.out.print(entry.getKey() + " ");

            // print out the run or set associated with that key
            for (Card c : entry.getValue())
            {
                System.out.print(" (" + c + ") ");
            }

            System.out.println();
        }

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

}
