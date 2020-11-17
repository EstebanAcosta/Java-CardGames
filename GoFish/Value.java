package GoFish;
/***
 * 
 * @author estebanacosta
 *
 */
public enum Value
{
    ACE(1), TWO(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9), TEN(10),JACK, QUEEN, KING;

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
