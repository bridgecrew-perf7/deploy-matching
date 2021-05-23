package com.proiectip.matching;

import com.proiectip.matching.utils.GoogleApiContext;
import com.proiectip.matching.utils.MatchingAlgorithm;
import com.proiectip.matching.utils.DistanceCalculator;
import com.proiectip.matching.utils.Algorithm;
import com.google.maps.model.TravelMode;
import com.proiectip.matching.users.NeederData;
import com.proiectip.matching.users.HelperData;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.util.HashMap;
import IP.root.ManagerFactorySingleton;
import IP.service.NeederService;
import com.proiectip.matching.responses.MatchResponse;

@SpringBootApplication
public class MatchingApplication {

    public static void initialise() {
        ManagerFactorySingleton.setName("heroku_ab221cef7d5f484");
        var instance = GoogleApiContext.getInstance();
        NeederService needer5 = new NeederService();
//        System.out.println(needer5.findAdress("dario.altenwerth"));
    }

    public static void test() {
        List<HelperData> helpers = new ArrayList<>(Arrays.asList(new HelperData("Catalin", "Aleea Tudor Neculai nr. 97, Iasi, Iasi"),
                new HelperData("Radu", "str. Transilvaniei nr. 317, Buzau, Buzau"),
                new HelperData("Ricardo", "Pacuret, Iasi"),
                new HelperData("Serafim", "Pacuret, Iasi, Iasi")));

        helpers.get(0).setOffers(new HashMap<>());
        helpers.get(0).getOffers().put("paine", 3);
        helpers.get(0).getOffers().put("faina", 5);
        helpers.get(0).getOffers().put("oua", 10);
        helpers.get(0).getOffers().put("ulei", 2);
        helpers.get(0).getOffers().put("cordon bleu", 4);
        helpers.get(0).getOffers().put("masti", 25);

        helpers.get(0).setDistanceAccepted(6500);
        helpers.get(1).setDistanceAccepted(8900);
        helpers.get(2).setDistanceAccepted(6700);
        helpers.get(3).setDistanceAccepted(7500);

        helpers.get(1).setOffers(new HashMap<>());
        helpers.get(1).getOffers().put("paine", 5);
        helpers.get(1).getOffers().put("faina", 10);
        helpers.get(1).getOffers().put("oua", 5);
        helpers.get(1).getOffers().put("sare", 100);
        helpers.get(1).getOffers().put("zahar", 2);
        helpers.get(1).getOffers().put("masti", 100);
        helpers.get(1).getOffers().put("medicamente", 8);
        helpers.get(1).getOffers().put("apa", 10);
        helpers.get(1).getOffers().put("Cola", 8);

        helpers.get(2).setOffers(new HashMap<>());
        helpers.get(2).getOffers().put("paine", 1);
        helpers.get(2).getOffers().put("bere", 100000);
        helpers.get(2).getOffers().put("oua", 23);
        helpers.get(2).getOffers().put("ulei", 5);
        helpers.get(2).getOffers().put("covrigi", 12);
        helpers.get(2).getOffers().put("hartie igienica", 12);
        helpers.get(2).getOffers().put("aquarele", 4);
        helpers.get(2).getOffers().put("bataie", 10);

        helpers.get(3).setOffers(new HashMap<>());
        helpers.get(3).getOffers().put("bere", 6);
        helpers.get(3).getOffers().put("oua", 8);
        helpers.get(3).getOffers().put("ulei", 4);
        helpers.get(3).getOffers().put("covrigi", 3);
        helpers.get(3).getOffers().put("medicamente", 7);
        helpers.get(3).getOffers().put("hartie igienica", 6);
        helpers.get(3).getOffers().put("aquarele", 2);
        helpers.get(3).getOffers().put("mentosane", 9);

        NeederData andrei = new NeederData("Andrei", "Str. Sf. Teodor nr. 14, Iasi, Iasi, Romania");
        andrei.setNeeds(new HashMap<>());
        andrei.getNeeds().put("paine", 1);
        andrei.getNeeds().put("fructe", 25);
        andrei.getNeeds().put("cordon bleu", 10000000);
        andrei.getNeeds().put("masti", 10);
        andrei.getNeeds().put("oua", 60);
        andrei.getNeeds().put("conserve", 4);
        andrei.getNeeds().put("mentosane", 15);
        andrei.getNeeds().put("Cola", 5);
        

        Algorithm match = new MatchingAlgorithm(andrei, helpers);

        TravelMode travelMode = TravelMode.valueOf("WALKING"); //valoarea data ca parametru in valueOf este preluata din baza de date
        System.out.println(new DistanceCalculator(andrei.getAddress(), helpers.get(0).getAddress()).getDistance(travelMode));

        MatchResponse solution = match.getSolution();
        System.out.println(solution);
//        instance.getContext().shutdown();
    }

    public static void main(String[] args) {
        SpringApplication.run(MatchingApplication.class, args);
        initialise();
        test();

    }

}
