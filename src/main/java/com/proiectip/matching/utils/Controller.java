/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proiectip.matching.utils;

import com.google.maps.model.TravelMode;
import com.proiectip.matching.users.HelperData;
import com.proiectip.matching.users.NeederData;
import com.proiectip.matching.users.Person;
import IP.entity.Helper;
import IP.entity.User;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import IP.repository.HelperRepo;
import IP.repository.NeederRepo;
import IP.repository.UserRepo;
import IP.service.HelperService;
import IP.service.NeederService;
import IP.service.OfferingService;
import IP.service.RequestService;
import IP.service.UserService;
import com.proiectip.matching.responses.MatchResponse;
import com.proiectip.matching.users.Interval;

/**
 *
 * @author Catalin
 * @author Radu
 * @author Andrei
 * @author Serafim
 */
@RestController()
@RequestMapping("/match")
public class Controller {

    @PostMapping()
    public MatchResponse doMatch(@RequestParam("user") String user) {

        NeederData needer = findNeederData(user);
        //HEEEEELPER-
        List<HelperData> helpers = findPossibleHelpers(needer);

        Algorithm doMatch = new MatchingAlgorithm(needer, helpers);
        var topHelpers = doMatch.getSolution();
        return topHelpers;
    }

    private NeederData findNeederData(String user) {
        RequestService request = new RequestService();
        NeederService neederService = new NeederService();
        NeederData needer = new NeederData();

        needer.setTypeOfForm(request.typeOfForm(user));
        if (needer.getTypeOfForm().equals("product")) {
            needer.setNeeds(request.productsNamesByUsername(user));
        } else {
            List<String> services = request.servicesNamesByUsername(user);
            Map<String, Integer> servicesNeeder = new HashMap<>();
            for (var service : services) {
                servicesNeeder.put(service, -1);
            }
            needer.setNeeds(servicesNeeder);
        }

        needer.setAddress(neederService.findAdress(user));
        needer.setUserName(user);
        needer.setLeisureTime(new Interval(neederService.findInterval(user)));

        return needer;
    }

    private List<HelperData> findPossibleHelpers(NeederData needer) {
        List<HelperData> helpers = new ArrayList<>();
        HelperService helperService = new HelperService();
        List<Helper> entityHelpers = helperService.allHelpers();
        OfferingService offeringService = new OfferingService();

        for (var helper : entityHelpers) {
            String username = helperService.findHelperUsername(helper.getIdUser());
            Interval helperInterval = new Interval(helperService.findIntervalH(username));
            if (helper.getIsAvailable() == 1 && (helperInterval.hasOverlap(needer.getLeisureTime()))) { // if the helper is not engaged in another match

                if (offeringService.typeOfFormH(username) != null && offeringService.typeOfFormH(username).equals(needer.getTypeOfForm())) { // if the type of request send by the needer matches what the helper if offering to do
                    String adress = helperService.findAdressH(username);

                    DistanceCalculator distanceCalculator = new DistanceCalculator(adress, needer.getAddress());
                    int distance = distanceCalculator.getDistance(TravelMode.DRIVING);
                    //   if (distance < helper.getDistanceAccepted()) {
                    HelperData helperData = new HelperData();
                    helperData.setDistanceAccepted(helper.getDistanceAccepted());
                    helperData.setAddress(adress);
                    helperData.setUserName(username);
                    helperData.setTypeOfForm(offeringService.typeOfFormH(username));
                    setOffers(helperData);
                    helperData.setDistanceToNeeder(distance);
                    helpers.add(helperData);
                    ////////}
                }
            }
        }
        return helpers;
    }

    private void setOffers(HelperData helperData) {
        OfferingService offeringService = new OfferingService();
        if (helperData.getTypeOfForm().equals("product")) {
            helperData.setOffers(offeringService.productsNamesByUsernameH(helperData.getUserName()));
        } else {
            List<String> services = offeringService.servicesNamesByUsernameH(helperData.getUserName());
            Map<String, Integer> offers = new HashMap<>();
            if (services != null) {
                for (var service : services) {
                    offers.put(service, -1);
                }
            }
            helperData.setOffers(offers);
        }
    }
}
