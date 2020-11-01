package President;

/***
 * @author estebanacosta
 */
public enum Value
{
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(11), QUEEN(12), KING(13), ACE(14);

    private int value;

    /**
     * Default Constructor for face values that don't have an associated value
     */
    Value()
    {

    }

    /**
     * Constructor that stores the value of the enum value type
     * @param val
     */
    Value(int val)
    {
        this.value = val;

    }

    /**
     * Gets the value associated with the enum value type
     * @return
     */
    public int getValue()
    {
        return value;

    }
}
