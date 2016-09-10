package edu.orangecoastcollege.cs273.dkim127.tapcounter;

/**
 * Keeps track of the number of taps performed by the user.
 * @author Austin Kim (C02328977)
 */
public class Counter {
    private int mCount;

    /**
     * Increments the count by one.
     *
     */
    public void tap()
    {
        mCount++;
    }

    /**
     * Returns the running total to the caller.
     * @return the running total of taps
     */
    public int getCount()
    {
        return mCount;
    }

    /**
     * Resets the current running total.
     */
    public void reset()
    {
        setCount(0);
    }

    /**
     * Assigns the value of the argument to mCount.
     * @param count new count to replace mCount
     */
    public void setCount(int count)
    {
        mCount = count;
    }
}
