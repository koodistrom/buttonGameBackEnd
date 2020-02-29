package com.jaakkomantyla.thebutton;

/**
 * Click count utils.
 */
public class ClickCountUtils {

    /**
     * Check if current count wins.
     *
     * @param count the current count of PushCounter
     * @return the amount of points won
     */
    public static int checkWin(int count){
        if(count%500==0){
            return 250;
        }else if (count%100==0){
            return 50;
        }else if(count%10==0){
            return 5;
        }else {
            return 0;
        }
    }

    /**
     * Distance to next price int.
     *
     * @param count  the current count of PushCounter
     * @return int, how many clicks to next price.
     */
    public static int distanceToNextPrice(int count){
        return 10-(count%10);
    }
}
