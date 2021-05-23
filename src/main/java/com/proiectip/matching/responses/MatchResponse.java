/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proiectip.matching.responses;

import java.util.List;


/**
 *
 * * @author Catalin
 * @author Radu
 * @author Andrei
 * @author Serafim
 */
public class MatchResponse {

    List<HelperResponse> helperResponses;

    public MatchResponse(List<HelperResponse> helperResponses) {
        this.helperResponses = helperResponses;
    }

    public MatchResponse() {
    }

    public List<HelperResponse> getHelperResponses() {
        return helperResponses;
    }

    @Override
    public String toString() {
        return "MatchResponse{" + "helperResponses=" + helperResponses + '}';
    }
  
}
