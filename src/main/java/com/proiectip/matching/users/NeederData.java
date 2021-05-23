package com.proiectip.matching.users;

import com.proiectip.matching.users.Person;
import java.util.HashSet;
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
public class NeederData extends Person {

    private Map<String,Integer> needs;

    public Map<String, Integer> getNeeds() {
        return needs;
    }

    public void setNeeds(Map<String, Integer> needs) {
        this.needs = needs;
    }

    public NeederData(String userName, String address) {
        super(userName, address);
    }
    
      public NeederData() {
    }

}
