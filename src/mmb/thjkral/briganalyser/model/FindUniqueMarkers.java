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
 * Finds unique regions in the genome of the outbreak strain.
 * Requires an ArrayList with Ring objects.
 * @author KralTHJ
 */
public class FindUniqueMarkers {
    
    /**
     *
     * @param ringList
     */
    public void start(ArrayList<Ring> ringList) {
        
        compare(ringList);
        
    }

    private void compare(ArrayList<Ring> ringList) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
