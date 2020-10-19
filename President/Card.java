package President;

/***
 * @author estebanacosta
 */
public class Card
{
    private Suit suit;

    private Value value;

    public Card(Suit suit, Value value)
    {

        setSuit(suit);
        setValue(value);

    }

    public Value getValue()
    {
        return value;
    }

    private void setValue(Value value)
    {
        this.value = value;
    }

    public int getValueOfCard()
    {

        return this.value.getValue();
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

        return this.getSuit() == c.getSuit() && this.getValue() == c.getValue();
    }
    
    public String toString()
    {

        if (getSuit() != null)
        {
            return getValue() + " of " + getSuit();
        }

        else
        {
            return getValue() + "";
        }

    }
}
