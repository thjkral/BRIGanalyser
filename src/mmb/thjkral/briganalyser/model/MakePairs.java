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
public class MakePairs {
    
    public void pair (ArrayList<Primer> primerListForward, ArrayList<Primer> primerListReverse) {
        
        int count = 0;
        
        
        for (Primer pf : primerListForward) {
            for (Primer pr : primerListReverse) {
                
                System.out.println("Primer " + pf.markerNumber + " with primer " + pr.markerNumber);
                
                double difference;
                if (pf.getMeltTemp() > pr.getMeltTemp()) {
                    difference = pf.getMeltTemp() - pr.getMeltTemp();
                } else {
                    difference = pr.getMeltTemp() - pf.getMeltTemp();
                }
                
                if (difference >= 1 && difference <= 6) {
                    count++;
                }
                
            }
        }
        
        for (int i = 0; i < primerListForward.size(); i++) {
            for (int j = 0; j < primerListReverse.size(); j++) {
                
                double tmForward = primerListForward.get(i).getMeltTemp();
                double tmReverse = primerListReverse.get(j).getMeltTemp();

                double difference;
                if (tmForward > tmReverse) {
                    difference = tmForward - tmReverse;
                }
                
                else if (tmForward < tmReverse) {
                    difference = tmReverse - tmForward;
                } else {
                    difference = 0;
                }
                
                if (difference >= 1 && difference <= 6) {
                    count++;
                }
                
            }
        }
        
        System.out.println("\tAble to form " + count + " pairs");
        
        
    }
    
}
