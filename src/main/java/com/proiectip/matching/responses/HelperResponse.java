/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proiectip.matching.responses;

import java.util.Map;

/**
 *
  * @author Catalin
 * @author Radu
 * @author Andrei
 * @author Serafim
 */
public class HelperResponse {
    private String username;
    private String adress;
    private int distance;
    private float score;
    private Map<String, Integer> commonResources;

    public HelperResponse(String username, String adress, int distance, float score, Map<String, Integer> commonResources) {
        this.username = username;
        this.adress = adress;
        this.distance = distance;
        this.score = score;
        this.commonResources = commonResources;
    }
    
    public HelperResponse()
    {
        
    }

    @Override
    public String toString() {
        return "HelperResponse{" + "username=" + username + ", distance=" + distance + ", score=" + score + '}';
    }

    
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public Map<String, Integer> getCommonResources() {
        return commonResources;
    }

    public void setCommonResources(Map<String, Integer> commonResources) {
        this.commonResources = commonResources;
    }
    
    
}
