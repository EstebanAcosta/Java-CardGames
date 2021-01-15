package GoFish;
/***
 * 
 * @author estebanacosta
 *
 */
public enum Rank
{
    ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN,JACK, QUEEN, KING;

    private int value;

    /**
     * Default Constructor for face values that don't have an associated value
     */
    Rank()
    {

    }
    
    /**
     * Constructor that stores the value of the enum value type
     * @param val
     */
    Rank(int val)
    {
        this.value = val;

    }
}

