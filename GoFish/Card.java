package GoFish;
/***
 * 
 * @author estebanacosta
 *
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

    public Card(Value redJoker)
    {
        setValue(redJoker);
    }

    public Value getValue()
    {
        return value;
    }

    private void setValue(Value redJoker)
    {
        this.value = redJoker;
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
