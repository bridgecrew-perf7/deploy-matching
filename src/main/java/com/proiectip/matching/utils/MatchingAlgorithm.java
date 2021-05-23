package com.proiectip.matching.utils;

import com.proiectip.matching.users.NeederData;
import com.proiectip.matching.users.HelperData;
import com.google.maps.model.TravelMode;
import com.proiectip.matching.responses.HelperResponse;
import com.proiectip.matching.responses.MatchResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;


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
public class MatchingAlgorithm extends Algorithm {

    public MatchingAlgorithm(NeederData needer, List<HelperData> helpers) {
        super(needer, helpers);
    }

    @Override
    public MatchResponse getSolution() {

        Set<HelperData> chosenHelpers = new TreeSet<>(HelperData::compareByScore);

        for (var helper : helpers) {

//            int distance = (int)helper.getDistanceToNeeder();
            DistanceCalculator distanceCalculator = new DistanceCalculator(helper.getAddress(), needer.getAddress());
            int distance = distanceCalculator.getDistance(TravelMode.DRIVING);
            //if (distance <= helper.getDistanceAccepted()) {
            helper.setDistanceToNeeder(distance); ///second compare criteria
            calculateScores(helper, needer);
            selectTopMatches((TreeSet) chosenHelpers, helper);
            //}
        }
        MatchResponse topMatches = createMatchResponse(chosenHelpers, needer);
        return topMatches;
    }

    private void calculateScores(HelperData helper, NeederData needer) {
        int needsCount = needer.getNeeds().size();
        float mainScore = 0, quantityScore = 0;
        Map<String, Integer> auxNeeds = new HashMap<>(needer.getNeeds());
        auxNeeds.keySet().retainAll(helper.getOffers().keySet());
        
        int helperResourceQuantity;
        for (var resource : auxNeeds.keySet()) {
            helperResourceQuantity = helper.getSpecificResourceQuantity(resource);
            mainScore += (float) 100 / (float) needsCount * Float.min(1, (float) helperResourceQuantity / (float) auxNeeds.get(resource));

            if (helperResourceQuantity > auxNeeds.get(resource)) {
                quantityScore += (float) ((float) 1 / Float.max(1, helperResourceQuantity - auxNeeds.get(resource)));
            }
        }
        helper.setScore(mainScore); // first compare criteria
        helper.setQuantityScore(quantityScore); /// 3rd compare criteria
    }

    private MatchResponse createMatchResponse(Set<HelperData> chosenHelpers, NeederData needer) {
        List<HelperResponse> helpersResponse = new ArrayList<>();
        for (var help : chosenHelpers) {
            Map<String, Integer> auxNeeds = new HashMap<>(needer.getNeeds());
            auxNeeds.keySet().retainAll(help.getOffers().keySet());
            for (var resource : auxNeeds.keySet()) {
                int helperResourceQuantity = help.getSpecificResourceQuantity(resource);
                auxNeeds.put(resource, Integer.min(helperResourceQuantity, auxNeeds.get(resource)));
            }

            helpersResponse.add(new HelperResponse(help.getUserName(), help.getAddress(), help.getDistanceToNeeder(), help.getScore(), auxNeeds));
        }
        return new MatchResponse(helpersResponse);
    }

    private void selectTopMatches(TreeSet<HelperData> setHelpers, HelperData helper) {
        setHelpers.add(helper);
        if (setHelpers.size() > maxTopSize) {
            setHelpers.pollLast();
        }
    }
}
