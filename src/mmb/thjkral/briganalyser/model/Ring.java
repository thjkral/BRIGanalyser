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
    
    private int number;    
    private ArrayList startPositions;
    private ArrayList stopPositions;
    private ArrayList gapsArray;

    public Ring(int number, ArrayList gapsArray) {
        this.number = number;
        this.gapsArray = gapsArray;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public ArrayList getGapsArray() {
        return gapsArray;
    }

    public void setGapsArray(ArrayList gapsArray) {
        this.gapsArray = gapsArray;
    }
    
    
    
}
