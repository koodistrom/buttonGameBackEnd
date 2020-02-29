package com.jaakkomantyla.thebutton;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Score info saved to high score repository.
 */
@Entity
public class Score {

    @Id
    private String id;
    private String name;
    private int points;

    /**
     * Instantiates a new Score.
     */
    public Score(){

    }

    /**
     * Instantiates a new Score.
     *
     * @param id     the id
     * @param name   the name
     * @param points the points
     */
    public Score(String id, String name, int points) {
        this.id = id;
        this.name = name;
        this.points = points;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets points.
     *
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * Sets points.
     *
     * @param points the points
     */
    public void setPoints(int points) {
        this.points = points;
    }
}
