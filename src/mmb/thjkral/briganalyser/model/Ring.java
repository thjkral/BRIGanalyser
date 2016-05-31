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
    
    private String name;                //Name of the Ring
    private ArrayList gapsArray;        //ArrayList with a the gaps on the Ring
    public ArrayList startPositions;    //ArrayList of start positions in a Ring
    public ArrayList stopPositions;     //ArrayList of stop positions in a Ring

    /**
     * Constructor for Ring object.
     * 
     * @param name              Name of the Ring
     * @param startPositions    ArrayList of start positions in a Ring
     * @param stopPositions     ArrayList of stop positions in a Ring
     */
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

    @Override
    public String toString() {
        return "Ring{" + "name=" + name + ", gapsArray=" + gapsArray + ", startPositions=" + startPositions + ", stopPositions=" + stopPositions + '}';
    }
    
    
    
}
