package GoFish;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;

/***
 * @author estebanacosta
 */
public class Player
{
    private String name;

    private int playerId;

    private ArrayList<Card> playerCards = new ArrayList<Card>();

    private Hashtable<Rank, ArrayList<Card>> books = new Hashtable<Rank, ArrayList<Card>>();

    public Player(int id)
    {
        setPlayerId(id);
    }

    public int getPlayerId()
    {
        return playerId;
    }

    public void setPlayerId(int playerId)
    {
        this.playerId = playerId;
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

    public void setPlayerCards(ArrayList<Card> remaininingCards)
    {
        this.playerCards = remaininingCards;
    }

    public Card getCard(int whichCard)
    {

        return this.playerCards.get(whichCard - 1);
    }

    public int getNumPlayerCards()
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

    /***
     * Every time a player has a book that book will be added to the list of books
     * @param book
     */
    public void addBooks(ArrayList<Card> book)
    {
        books.put(book.get(0).getRank(), book);
    }

    /***
     * 
     * @return a list of all of the current player's books
     */
    public Hashtable<Rank, ArrayList<Card>> getListOfBooks()
    {
        return this.books;
    }

    /***
     * Prints all of this player's books
     */
    public void showListOfBooks()
    {
        System.out.println(getName() + "'s list of books: \n");

        for (Map.Entry<Rank, ArrayList<Card>> entry : books.entrySet())
        {
            System.out.print(entry.getKey() + ": ");

            for (Card c : entry.getValue())
            {
                System.out.print(" ( " + c + " ) ");
            }

            System.out.println();
            System.out.println();
        }

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
     * @param cardPosition
     * @return
     */
    public Hashtable<Rank, Integer> howManyTimesThisRankAppears()
    {
        int count = 0;

        //get an array list of all the ranks this player has in their hand
        ArrayList<Rank> ranks = getAllRanksPlayerHas();

        //create a hash table where the key is rank and the value is the number of times that rank appears in a player's hand
        Hashtable<Rank, Integer> timesRankAppears = new Hashtable<Rank, Integer>();

        
        //loop through the ranks the player has
        for (Rank rank : ranks)
        {
            //loop through the cards the player has
            for (int i = 0; i < playerCards.size(); i++)
            {
                //if the rank the outer loop is on is equal to this card's rank
                if (playerCards.get(i).getRank() == rank)
                {
                    //add one to count
                    count++;
                }
            }
            
            //place the rank and the number of times this rank appears in the table
            timesRankAppears.put(rank, count);

            //reset the counter when it's time for the outer loop to move on to the next rank
            count = 0;

        }
        
        return timesRankAppears;
    }

    /***
     * Shows all the cards the player has in their hand
     */
    public void showPlayerCards()
    {

        System.out.println(getName() + "'s Hand: \n");

        for (int i = 0; i < playerCards.size(); i++)
        {
            System.out.print(" ( " + playerCards.get(i) + " ) ");

            if ((i + 1) % 5 == 0 && i > 0)
            {

                System.out.println();
                System.out.println();

            }

        }

        System.out.println();
        System.out.println();
    }

}
