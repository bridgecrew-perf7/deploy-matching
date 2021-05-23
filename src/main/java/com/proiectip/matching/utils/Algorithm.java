package com.proiectip.matching.utils;

import com.google.maps.model.TravelMode;
import com.proiectip.matching.responses.MatchResponse;
import com.proiectip.matching.users.NeederData;
import com.proiectip.matching.users.HelperData;
import java.util.List;
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
public abstract class Algorithm {

    protected final NeederData needer;
    protected List<HelperData> helpers;
    protected Set<HelperData> matches = new TreeSet<>();
    protected MatchResponse  topMatches = new MatchResponse();
    static public int maxTopSize = 3;

    public Algorithm(NeederData needer, List<HelperData> helpers) {
        this.needer = needer;
        this.helpers = helpers;
    }

    public List<HelperData> getHelpers() {
        return helpers;
    }

    public void setHelpers(List<HelperData> helpers) {
        this.helpers = helpers;
    }

    public abstract MatchResponse  getSolution();
}
