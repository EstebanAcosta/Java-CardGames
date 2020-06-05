package Uno;

/***
 * @author estebanacosta
 */
public class Card
{
    private Color color;

    private Value value;

    private SpecialValue specialValue;

    public Card(Color color, Value value)
    {

        setColor(color);
        setValue(value);

    }

    public Card(SpecialValue specialValue)
    {
        setSpecialValue(specialValue);
    }

    public SpecialValue getSpecialValue()
    {
        return this.specialValue;
    }

    public void setSpecialValue(SpecialValue sv)
    {
        this.specialValue = sv;

    }

    public Value getValue()
    {
        return value;
    }

    private void setValue(Value value)
    {
        this.value = value;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public boolean isWild()
    {
        return (specialValue != null ? true : false);
    }

    public int getValueOfCard()
    {

        return this.value.getValue();
    }

    public String toString()
    {

 
        if (getColor() != null)
        {
            
            if(isWild())
            {
                return getColor() + " " + getSpecialValue();
            }
            
            else
            {
                return getColor() + " " + getValue();

            }
        }

        else
        {
            return getSpecialValue() + "";
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
