package com.proiectip.matching.users;

import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Catalin
 * @author Radu
 * @author Andrei
 * @author Serafim
 */
public class HelperData extends Person {

    private Map<String, Integer> offers;
    private int distanceAccepted; //in meters
    private float score;
    private int distanceToNeeder;
    private float quantityScore;

    public int getSpecificResourceQuantity(String resource) {
        return offers.get(resource);
    }

    public Map<String, Integer> getOffers() {
        return offers;
    }

    public void setOffers(Map<String, Integer> offers) {
        this.offers = offers;
    }

    public HelperData(String userName, String address) {
        super(userName, address);
    }

    public HelperData() {
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public int getDistanceAccepted() {
        return distanceAccepted;
    }

    public void setDistanceAccepted(int distance) {
        this.distanceAccepted = distance;
    }

    @Override
    public String toString() {
        return userName + " " + score + " " + distanceToNeeder;
    }

//    @Override
//    public String toString() {
//        return "HelperData{ userName="+ userName + "offers=" + offers + '}';
//    }
    

    /**
     * @return the distanceToNeeder
     */
    public int getDistanceToNeeder() {
        return distanceToNeeder;
    }

    /**
     * @param distanceToNeeder the distanceScore to set
     */
    public void setDistanceToNeeder(int distanceToNeeder) {
        this.distanceToNeeder = distanceToNeeder;
    }

    /**
     * @return the quantityScore
     */
    public float getQuantityScore() {
        return quantityScore;
    }

    /**
     * @param quantityScore the quantityScore to set
     */
    public void setQuantityScore(float quantityScore) {
        this.quantityScore = quantityScore;
    }

    public static int compareByScore(HelperData helper1, HelperData helper2) {
        if (helper1.getScore() < helper2.getScore()) {
            return 1;
        } else if (helper1.getScore() == helper2.getScore() && helper1.getDistanceToNeeder() > helper2.getDistanceToNeeder()) {
            return 1;
        } else if (helper1.getScore() == helper2.getScore() && helper1.getDistanceToNeeder() == helper2.getDistanceToNeeder() && helper1.getQuantityScore() < helper2.getQuantityScore()) {
            return 1;
        } else if (helper1.getScore() == helper2.getScore() && helper1.getDistanceToNeeder() == helper2.getDistanceToNeeder() && helper1.getQuantityScore() == helper2.getQuantityScore()) {
            return helper1.userName.compareTo(helper2.userName);
        }
        return -1;
    }

}
