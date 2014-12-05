package com.brianstacks.fragmentandfilefundamentals;

import java.io.Serializable;

/**
 * Created by Brian Stacks
 * on 11/25/14
 * for FullSail.edu.
 */
public class Games implements Serializable{
    private static final long serialVersionUID = 8733333333330552888L;
    private String tHome;
    private String tAway;
    private String tVenue;


    public  Games(){
        tHome ="";
        tAway ="";
        tVenue = "";
    }

    public String getHome() {
        return tHome;
    }
    public void setHome(String home) {
        tHome = home;
    }
    public String getAway() {
        return tAway;
    }
    public void  setAways(String away) {
        tAway= away;
    }
    public String getVenue() {
        return tVenue;
    }
    public void setVenue(String venue) {
        tVenue=venue;
    }
}
