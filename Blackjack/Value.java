package Blackjack;

/***
 * @author estebanacosta
 */
public enum Value
{
    TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10), JACK(10), QUEEN(10), KING(10), ACE(1, 11);

    private int value;

    private int otherValue;

    /**
     * /**
     * Constructor that stores the value of the enum value type
     * @param val
     */
    Value(int val)
    {
        this.value = val;

    }

    /**
     * /**
     * Constructor that stores the value of the enum value type
     * @param val
     */
    Value(int val, int otherVal)
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

    public int getOtherValue()
    {
        return otherValue;

    }
}
