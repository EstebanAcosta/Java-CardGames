package Uno;

/***
 * @author estebanacosta
 */
public class Card
{
    private Color color;

    private Value value;

    private boolean faceDown;

    public Card(Color suit, Value value)
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

    public Color getColor()
    {
        return color;
    }

    private void setSuit(Color color)
    {
        this.color = color;
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

        if (getColor() != null)
        {
            return getValue() + " of " + getColor();
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
        return this.value == c.getValue() && this.color == c.getColor();
    }
}
