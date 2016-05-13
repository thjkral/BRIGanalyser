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
public class GeneratePrimers {

    public void generate(ArrayList<UniqueMarker> umList) {
        
        /*
        GLOBAL VALUES. PLEASE DELETE WHEN MORE FUNCTIONALITY IS PRESENT!
        */
        int prodLength = 6;
        int maxPrimerLength = 10;
        int minPrimerLength = 8;
        
        ArrayList<Primer> forwardPrimers = new ArrayList();
        
        for (UniqueMarker um : umList) {
            
            int begin = 0;
            int half = um.getLength() / 2;
            int currPrimerLength = minPrimerLength;
            
            while (currPrimerLength <= maxPrimerLength) {
              
                
            }
            
            
            
        }
        
        
    }//generate()
    
    
}//class()
