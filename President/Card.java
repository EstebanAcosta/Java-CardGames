package President;

/***
 * @author estebanacosta
 */
public class Card
{
    private Suit suit;

    private Rank rank;

    public Card(Suit suit, Rank value)
    {

        setSuit(suit);
        setRank(value);

    }

    public Rank getRank()
    {
        return rank;
    }

    private void setRank(Rank rank)
    {
        this.rank = rank;
    }

    public int getValueOfCard()
    {

        return this.rank.getValue();
    }

    public Suit getSuit()
    {
        return suit;
    }

    private void setSuit(Suit suit)
    {
        this.suit = suit;
    }


    @Override
    public boolean equals(Object o)
    {
        if (o == null)
        {
            return false;
        }

        if (o == this)
        {
            return true;
        }

        if (!getClass().equals(o.getClass()))
        {
            return false;
        }

        Card c = (Card) o;

        return this.getSuit() == c.getSuit() && this.getRank() == c.getRank();
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
