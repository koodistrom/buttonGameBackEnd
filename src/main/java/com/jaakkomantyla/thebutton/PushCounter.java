package com.jaakkomantyla.thebutton;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Push counter tracks the game situation.
 */
@Entity
public class PushCounter {


    @Id
    @GeneratedValue
    private int id;
    private int counter;

    /**
     * Instantiates a new Push counter.
     */
    public PushCounter() {
        this.counter = 0;
    }

    /**
     * Gets counter.
     *
     * @return the counter
     */
    public int getCounter() {
        return counter;
    }

    /**
     * Sets counter.
     *
     * @param counter the counter
     */
    public void setCounter(int counter) {
        this.counter = counter;
    }

    @Override
    public String toString() {
        return "PushCounter{" +
                "id=" + id +
                ", counter=" + counter +
                '}';
    }
}
