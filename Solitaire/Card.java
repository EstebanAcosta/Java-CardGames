package Solitaire;

/***
 * @author estebanacosta
 */
public class Card implements Comparable<Card>
{
    private Suit suit;

    private Rank rank;

    private Color color;

    private boolean unflipped;
    
    public Card(Suit suit, Rank value, Color color)
    {

        setSuit(suit);
        setRank(value);
        setColor(color);

    }

    public Card(Suit suit, Rank value)
    {

        setSuit(suit);
        setRank(value);

    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
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

    public void setUnflipped(boolean unflipped)
    {
        this.unflipped = unflipped;
    }

    public boolean isUnflipped()
    {
        return unflipped;
    }

    @Override
    public int compareTo(Card c)
    {
        if (this.getRank().getValue() > c.getRank().getValue())
        {
            return 1;
        }

        else if (this.getRank().getValue() < c.getRank().getValue())
        {
            return -1;
        }

        else
        {
            return 0;
        }
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
            return getColor() + " " + getRank() + " of " + getSuit();
        }

        else
        {
            return getColor() + " " + getRank() + "";
        }

    }

}
