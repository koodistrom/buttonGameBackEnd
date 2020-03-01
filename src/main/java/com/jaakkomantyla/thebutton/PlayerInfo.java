package com.jaakkomantyla.thebutton;

import org.springframework.stereotype.Component;

/**
 * Player info sent to front end.
 */
@Component
public class PlayerInfo {
    private String id;
    private int points;
    private int distanceToNextPrice;
    private boolean win;
    private String name;

    /**
     * Instantiates a new Player info.
     */
    public PlayerInfo() {
    }

    /**
     * Instantiates a new Player info.
     *
     * @param points              the points
     * @param distanceToNextPrice the distance to next price
     * @param win                 the win
     * @param name                the name
     */
    public PlayerInfo(int points, int distanceToNextPrice, boolean win, String name, String id) {
        this.points = points;
        this.distanceToNextPrice = distanceToNextPrice;
        this.win = win;
        this.name = name;
        this.id = id;
    }

    /**
     * Instantiates a new Player info.
     *
     * @param name the name
     */
    public PlayerInfo(String name){
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

    /**
     * Gets distance to next price.
     *
     * @return the distance to next price
     */
    public int getDistanceToNextPrice() {
        return distanceToNextPrice;
    }

    /**
     * Sets distance to next price.
     *
     * @param distanceToNextPrice the distance to next price
     */
    public void setDistanceToNextPrice(int distanceToNextPrice) {
        this.distanceToNextPrice = distanceToNextPrice;
    }

    /**
     * Is win boolean.
     *
     * @return the boolean
     */
    public boolean isWin() {
        return win;
    }

    /**
     * Sets win.
     *
     * @param win the win
     */
    public void setWin(boolean win) {
        this.win = win;
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

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "points=" + points +
                ", distanceToNextPrice=" + distanceToNextPrice +
                ", win=" + win + " name="+name+
                '}';
    }
}
