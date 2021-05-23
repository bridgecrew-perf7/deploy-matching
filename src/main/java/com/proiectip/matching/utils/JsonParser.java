package com.proiectip.matching.utils;


import org.json.JSONArray;
import org.json.JSONObject;

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
public class JsonParser {

    public static int getDistanceInMeters(String googleMapsData) {
        JSONObject mapJson = new JSONObject(googleMapsData);
        JSONArray arr = mapJson.getJSONArray("rows");

        mapJson = arr.getJSONObject(0);
        arr = mapJson.getJSONArray("elements");

        mapJson = arr.getJSONObject(0);

        return mapJson.getJSONObject("distance").getInt("inMeters");
    }

}
