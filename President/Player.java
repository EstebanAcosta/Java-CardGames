package President;
import java.util.ArrayList;
/***
 * 
 * @author estebanacosta
 *
 */
public class Player
{
    private String name;

    private int playerId;

    private ArrayList<Card> playerCards = new ArrayList<Card>();

    private int goalCards;

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

    public void setPlayerGoalCards(int goalCards)
    {
        this.goalCards = goalCards;
    }

    public int getPlayerGoalCards()
    {
        return goalCards;
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
     * If all of the player's cards have been uncovered, then that means the player
     * has reached their goal
     * @return
     */
    public boolean hasReachedPersonalGoalCards()
    {

        for (Card c : playerCards)
        {
            if (c.isUnflipped())
            {
                return false;
            }
        }

        return true;
    }

    public ArrayList<Integer> showAvailableCardsToSwitch()
    {
        int count = 1;

        ArrayList<Integer> switchable = new ArrayList<Integer>();

        for (Card c : getPlayerCards())
        {
            if (c.isUnflipped())
            {
                System.out.print("Card " + count + " ");
                switchable.add(count);

            }

            count++;

        }

        System.out.println();


        return switchable;
    }

    public void showPlayerCards()
    {

        System.out.println(getName() + "'s Hand: \n");
        for (int i = 0; i < playerCards.size(); i++)
        {

            if (playerCards.get(i).isUnflipped())
            {
                System.out.print(" ( CARD " + (i + 1) + " )");
            }
            else
            {
                System.out.print(" ( " + playerCards.get(i) + " ) ");
            }

            if ((i + 1) % 5 == 0 && i > 0)
            {

                System.out.println();
                System.out.println();

            }

        }
        
        System.out.println();
        System.out.println();

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

        playerCards.get(position).setUnflipped(false);

        return oldCard;

    }

}
