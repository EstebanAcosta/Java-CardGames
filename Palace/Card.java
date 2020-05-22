package Palace;

/***
 * @author estebanacosta
 */
public class Card
{
    private Suit suit;

    private Value value;

    private boolean faceDown;

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

    public void setFaceDown(boolean faceDown)
    {
        this.faceDown = faceDown;
    }

    public boolean isFaceDown()
    {

        return faceDown;
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

    @Override
    public boolean equals(Object o)
    {
        // If the object is compared with itself then return true
        if (o == this)
        {
            return true;
        }

        /*
         * Check if o is an instance of Complex or not "null instanceof [type]" also
         * returns false
         */
        if (!(o instanceof Card))
        {
            return false;
        }

        // typecast o to Card so that we can compare data members
        Card c = (Card) o;

        // Compare the data members and return accordingly
        return this.value == c.getValue() && this.suit == c.getSuit();
    }
}
