/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proiectip.matching.users;

import java.sql.Time;

/**
 *
 * @author Catalin
 * @author Radu
 * @author Andrei
 * @author Serafim
 */
public class Interval {

    private final Time begin;
    private final Time end;

    public Interval(Time begin, Time end) {
        this.begin = begin;
        this.end = end;
    }
    
    public Interval(Time[] interval)
    {
        this.begin = interval[0];
        this.end = interval[1];
    }

    public Time getBegin() {
        return begin;
    }

    public Time getEnd() {
        return end;
    }
    
    public boolean hasOverlap(Interval interval){
        return !(this.end.before(interval.getBegin()) && !this.begin.after(interval.getEnd()));
    }
}
