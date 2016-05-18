/*
 * This code is created by:
 * Tom Kral | Bioinformatics intern
 * t.h.j.kral@umcg.nl | +31642935544
 * 
 * Commissioned by:
 * University Medical Center Groningen | Medical Microbiology
 */
package mmb.thjkral.briganalyser.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class with methods that help determine the quality of primers.
 * 
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
    
    
    
    /**
     * Calculates the percentage of Guanine and Cytosine nucleotides in the DNA
     * sequence.
     * 
     * @param seq
     * @return
     */
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
    
    /**
     * If the sequence has to many repeating groups of two nucleotides, the primer
     * is not eligable for usage.
     * 
     * @param seq
     * @return boolean
     */
    public boolean checkDiRepeats (String seq) {
        
        /*
        Make booelan. True for no direpeats, false for direpeats in sequence.
        Default is true.
        */
        boolean useable = true;        
        
        /*
        Define pattern
        */
        String regex = "(AT){5}|(AG){5}|(AC){5}|(AA){5}|(TA){5}|(TT){5}|(TG){5}|(TC){5}|(CA){5}|(CC){5}|(CG){5}|(CT){5}|(GA){5}|(GC){5}|(GT){5}|(GG){5}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(seq);
        
        /*
        If repeats are found, the boolean is set to false
        */
        while (m.find()) {
            useable = false;
        }
        
        return useable;
    }//checkDiRepeats()
    
    /**
     * If the sequence has to many repeating nucleotides the primer is not 
     * usefull.
     * 
     * @param seq
     * @return boolean
     */
    public boolean checkMonoRepeats (String seq) {
        
        /*
        Make booelan. True for no direpeats, false for direpeats in sequence.
        Default is true.
        */
        boolean useable = true;
        
        /*
        Define pattern
        */
        String regex = "A{3}|T{3}|C{3}|G{3}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(seq);
        
        /*
        If repeats are found, the boolean is set to false
        */
        while (m.find()) {
            useable = false;
        }
        
        return useable;
        
    }//checkMonoRepeats()
    
    /**
     * The last five nucleotides must contain no more than 2 guanine and cytosine
     * in the last five nucleotides.
     * 
     * @param seq
     * @return boolean
     */
    public boolean threeEnd (String seq) {
        
        /*
        Make booelan. True for no more than 2 Guanine and more than 2 cytosine.
        Default is true.
        */
        boolean isUsable = true;
        
        /*
        Isolate 5 terminal nucleotides
        */
        String subSeq = seq.substring(seq.length() - 5);
        
        /*
        Count the occurences of guanine and cytosine
        */
        int nucC = subSeq.length() - subSeq.replace("C", "").length();
        int nucG = subSeq.length() - subSeq.replace("G", "").length();
        
        /*
        Change boolean is there are more than 2 G's or C's
        */
        if (nucC > 2 || nucG > 2) {
            isUsable = false;
        }
        
        return isUsable;
    }
    

    public boolean checkThreeTerminal (String seq) {
        
        if (seq.charAt(seq.length() - 1) == 'G' ) {
            return true;
        } else {
            return false;
        }
        
    }//checkThreeTerminal()
    
    
    public boolean checkHairpin (String seq) {
        
        
        
        return true;
    }//checkHairpin()
    
    
    public void checkUnicity () {
        
    }//checkUnicity()
    
}//Class()
