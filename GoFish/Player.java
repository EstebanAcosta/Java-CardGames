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

    private Hashtable<Integer,ArrayList<Card>> books = new Hashtable<Integer, ArrayList<Card>>();

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

    public Card removeOneFromPlayerHand(int position)
    {
        return playerCards.remove(position - 1);
    }

    public void removeMultipleFromHand(ArrayList<Card> cards)
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
    
    /***
     * Every time a player has a book that book will be added to the list of books
     * @param book
     */
    public void addBooks(ArrayList<Card> book)
    {
        books.put(book.get(0).getValueOfCard(), book);
    }
    
    public Hashtable<Integer,ArrayList<Card>> getListOfBooks()
    {
        return this.books;
    }
    
    /***
     * Prints all of this player's books
     */
    public void showListOfBooks()
    {
        System.out.println(getName() + "'s list of books: \n");
        
        for(Map.Entry<Integer, ArrayList<Card>> entry : books.entrySet()) 
        {
            System.out.print(entry.getKey() + ": ");
            
            for(Card c : entry.getValue())
            {
                System.out.print(c + " ");
            }
            
            System.out.println();
            System.out.println();
        }
            
    }

    /***
     * Calculates how many ranks the player has in their hand
     * @return
     */
    public ArrayList<Value> getAllRanksPlayerHas()
    {
        ArrayList<Value> ranks = new ArrayList<Value>();

        for (int i = 0; i < playerCards.size(); i++)
        {
            if (!ranks.contains(playerCards.get(i).getValue()))
            {
                ranks.add(playerCards.get(i).getValue());
            }

        }

        return ranks;
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
