/*
 * This code is created by:
 * Tom Kral | Bioinformatics intern
 * t.h.j.kral@umcg.nl | +31642935544
 * 
 * Commissioned by:
 * University Medical Center Groningen | Medical Microbiology
 */
package mmb.thjkral.briganalyser.model;

import java.util.ArrayList;

/**
 *
 * @author KralTHJ
 */
public class Ring {
    
    private String name;    
    private ArrayList gapsArray;
    public ArrayList startPositions;
    public ArrayList stopPositions;

    public Ring(String name, ArrayList startPositions, ArrayList stopPositions) {
        this.name = name;
        this.startPositions = startPositions;
        this.stopPositions = stopPositions;
    }

    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList getGapsArray() {
        return gapsArray;
    }

    public void setGapsArray(ArrayList gapsArray) {
        this.gapsArray = gapsArray;
    }
    
    
    
}
