/*
 * This code is created by:
 * Tom Kral | Bioinformatics intern
 * t.h.j.kral@umcg.nl | +31642935544
 * 
 * Commissioned by:
 * University Medical Center Groningen | Medical Microbiology
 */
package mmb.thjkral.briganalyser.model;

/**
 * Class with methods that help determine the quality of primers
 * @author KralTHJ
 */
public class ValidatePrimers {
    
    /**
     * Calculates the melting temperature of a primer.
     * The melting temperature, or Tm, is the temperature when 50% of the DNA
     * is melted of its counterpart
     * 
     * @param seq
     * @return melting temperature
     */
    public double calculateTm (String seq) {
               
        /*
        Count all nucleotides
        */
        int nucA = seq.length() - seq.replace("A", "").length();
        int nucT = seq.length() - seq.replace("T", "").length();
        int nucC = seq.length() - seq.replace("C", "").length();
        int nucG = seq.length() - seq.replace("G", "").length();
        
        /*
        Implement formula:
        4°C*(# G/C nucleotides) + 2°C*(# A/T nucleotides)
        (idea for future: formula with salt-correction)
        */
        double meltTemp = (4*(nucG+nucC)) + (2*(nucA+nucT));
        
        return meltTemp;
    }//calculateTm()
    
    
    
    public double calculateGc (String seq) {
        
        /*
        Count Guanine and Cytosine nucleotides
        */
        int nucC = seq.length() - seq.replace("C", "").length();
        int nucG = seq.length() - seq.replace("G", "").length();
        
        /*
        Implement formula:
        (G+C) / total * 100%
        */
        double gcContent = ((nucG + nucC) / seq.length()) * 100;
        
        return gcContent;
    }//calculateGc()
    
    
    public boolean checkDiRepeats () {
        
        
        
        return false;
    }//checkDiRepeats()
    
    
    public void checkMonoRepeats () {
        
    }//checkMonoRepeats()
    
    
    public void checkUnicity () {
        
    }//checkUnicity()
    
}//Class()
