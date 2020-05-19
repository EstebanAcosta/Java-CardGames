package Palace;

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
}
