/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proiectip.matching.utils;

import com.google.maps.GeoApiContext;

/**
 *
 * @author Catalin
 * @author Radu
 * @author Andrei
 * @author Serafim
 */
public class GoogleApiContext {
    
    private static GoogleApiContext instance = null;
    private GeoApiContext context;
    
    private GoogleApiContext()
    {
        this.context = new GeoApiContext.Builder().apiKey("AIzaSyD3_lITYqjuuyGevhi1iL8i7iais5W6rjQ").build();
    }

    public GeoApiContext getContext() {
        return context;
    }
    
    public static GoogleApiContext getInstance()
    {
        if(instance == null)
            instance = new GoogleApiContext();
        return instance;
    }
}
