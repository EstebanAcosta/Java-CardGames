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

    private Hashtable<TypeOfMeld, ArrayList<Card>> melds = new Hashtable<TypeOfMeld, ArrayList<Card>>();

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

    public Hashtable<TypeOfMeld, ArrayList<Card>> getMelds()
    {
        return this.melds;
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

        for (Card card : cards)
        {
            Iterator<Card> playersHand = playerCards.iterator();

            while (playersHand.hasNext())
            {
                Card nextCard = playersHand.next();

                if (nextCard.equals(card))
                {

                    playersHand.remove();

                }
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
     * Adds this specific run to the table of melds
     * @param position
     */
    public void meldRun(int position)
    {

        // add this run to the table of melds
        // key is type of meld which in this case is a run and the value is the array list of cards that form a run
        melds.put(TypeOfMeld.RUN, runs.get(position));

        // remove this run from the player's hand
        removeMultipleFromPlayerCards(runs.get(position));

        // remove this run from the table of runs
        runs.remove(position);
    }

    /***
     * Add this specific set to the table of melds
     * @param position
     */
    public void meldSet(int position)
    {
        // add this set to the table of sets
        // key is type of meld which in this case is a set and the value is the array list of cards that form a set
        melds.put(TypeOfMeld.SET, sets.get(position));

        // remove this set from the player's hand
        removeMultipleFromPlayerCards(sets.get(position));

        // remove this set from the player's hand
        sets.remove(position);

    }

    /***
     * In the Rummy class, the player is asked which card and whose meld they want to. Once they've selected both,
     * this method will take the card they want and add it to the respective meld this player possesses. Method loops through
     * the player's meld and adds this card either to the end of the list of cards or at the beginning depending on
     * the type of meld the card is being added to.
     * @param c
     * @param thisMeld
     */
    public void addToPlayersMeld(Card c, int thisMeld)
    {
        int count = 1;

        // loop through the table
        for (Map.Entry<TypeOfMeld, ArrayList<Card>> entry : getMelds().entrySet())
        {
            // if this is the meld the player wants to add
            if (count == thisMeld)
            {
                // if this meld is a run
                if (entry.getKey() == TypeOfMeld.RUN)
                {
                    // if this card is one less than the first card in the list of cards
                    if ((c.getValueOfCard() + 1) == entry.getValue().get(0).getValueOfCard())
                    {
                        // add the card to the beginning of the list
                        entry.getValue().add(0, c);
                    }

                    // if this card is one more than the last card in the list of cards
                    else if ((c.getValueOfCard() - 1) == entry.getValue().get(entry.getValue().size() - 1).getValueOfCard())
                    {
                        // add the card to the end of the list
                        entry.getValue().add(c);
                    }

                }

                // if this meld is a set
                else
                {
                    // add the card to the end of the list
                    entry.getValue().add(c);
                }

                break;
            }

            count++;
        }

    }

    public void clearRuns()
    {
        runs.clear();
    }

    public void clearSets()
    {
        sets.clear();
    }

    public boolean hasASet()
    {
        if (sets.size() > 0)
        {
            return true;
        }

        return false;
    }

    public boolean hasARun()
    {

        if (runs.size() > 0)
        {
            return true;
        }

        return false;
    }

    public boolean hasAMeld()
    {
        if (melds.size() > 0)
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
        for (Map.Entry<Rank, Integer> entry : timesRankAppears.entrySet())
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
        for (Map.Entry<Suit, Integer> entry : timesSuitAppears.entrySet())
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

                // continue looping through the copy list until there are only 3 elements left
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

                            if (!run.contains(copyList.get(i)))
                            {
                                // add this card to the run
                                run.add(copyList.get(i));

                            }

                            if (!run.contains(copyList.get(i + 1)))
                            {
                                // add the subsequent card to the run
                                run.add(copyList.get(i + 1));

                            }

                        }

                        else
                        {
                            // break out of this loop because this isn't a run or we are at the end of the loop
                            break;
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

    public void showMelds()
    {
        System.out.println(getName() + "'s melds:");

        int count = 1;
        // loop through the list of sets
        for (Entry<TypeOfMeld, ArrayList<Card>> entry : melds.entrySet())
        {
            // print out the key
            System.out.print(count + " " + entry.getKey() + " ");

            // print out the run or set associated with that key
            for (Card c : entry.getValue())
            {
                System.out.print(" (" + c + ") ");
            }

            count++;

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
