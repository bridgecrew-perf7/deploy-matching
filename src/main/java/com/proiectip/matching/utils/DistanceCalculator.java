package com.proiectip.matching.utils;

import com.proiectip.matching.users.NeederData;
import com.proiectip.matching.users.HelperData;
import com.google.gson.Gson;
import com.google.maps.errors.ApiException;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.TravelMode;
import com.proiectip.matching.users.Person;
import java.io.IOException;
import org.json.JSONException;


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
public class DistanceCalculator {

    private final String orig;
    private final String dest;

    public DistanceCalculator(String orig, String dest) {
        this.orig = orig;
        this.dest = dest;
    }

    public int getDistance(TravelMode travelMode) {

        int distMeters = 0;
        String[] origine = new String[]{orig};
        String[] destinatie = new String[]{dest};
        
        

        var instance = GoogleApiContext.getInstance();
        var dist = com.google.maps.DistanceMatrixApi.newRequest(instance.getContext());
        try {
            DistanceMatrix distMat = dist.origins(origine)
                    .destinations(destinatie)
                    .mode(travelMode)
                    .language("en-EN")
                    .await();
            

            String jsonText = new Gson().toJson(distMat);
            distMeters = JsonParser.getDistanceInMeters(jsonText);

        } catch (ApiException | InterruptedException | IOException ex) {
            System.out.println(ex);
        }
        catch(JSONException ex)
        {
            return Integer.MAX_VALUE;
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
        //     MatchingSystem.context.shutdown();
        return distMeters;
    }

}
