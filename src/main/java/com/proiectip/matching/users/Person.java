package com.proiectip.matching.users;

import com.google.maps.model.TravelMode;
import com.proiectip.matching.utils.DistanceCalculator;
import java.util.Objects;

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
public abstract class Person implements Comparable<Person>{
    protected String userName;
    protected String address;
    protected String typeOfForm;
    protected Interval leisureTime;

    public Person(String userName, String address) {
        this.userName = userName;
        this.address = address;
    }

    public Person() {
    }

    public String getTypeOfForm() {
        return typeOfForm;
    }

    public void setTypeOfForm(String typeOfForm) {
        this.typeOfForm = typeOfForm;
    }

    public Interval getLeisureTime() {
        return leisureTime;
    }

    public void setLeisureTime(Interval leisureTime) {
        this.leisureTime = leisureTime;
    }
    
    

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    public int getDistance(Person destinator, TravelMode travelMode) {
        DistanceCalculator dist = new DistanceCalculator(this.address, destinator.getAddress());
        return dist.getDistance(travelMode);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Person other = (Person) obj;
        if (!Objects.equals(this.userName, other.userName)) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Person o) {
        return this.userName.compareTo(o.userName);
    }
}
