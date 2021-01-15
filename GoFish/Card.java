package GoFish;
/***
 * 
 * @author estebanacosta
 *
 */
public class Card
{
    private Suit suit;

    private Rank rank;

    public Card(Suit suit, Rank rank)
    {

        setSuit(suit);
        setValue(rank);

    }

    public Card(Rank rank)
    {
        setValue(rank);
    }

    public Rank getRank()
    {
        return rank;
    }

    private void setValue(Rank redJoker)
    {
        this.rank = redJoker;
    }

    public Suit getSuit()
    {
        return suit;
    }

    private void setSuit(Suit suit)
    {
        this.suit = suit;
    }

    public String toString()
    {

        if (getSuit() != null)
        {
            return getRank() + " of " + getSuit();
        }

        else
        {
            return getRank() + "";
        }

    }
}
