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
 * Not every primer or probe made by the GeneratePrimers can be used in qPCR 
 * reactions. By checking with the methods in this Class, bad primers can be 
 * separated from the bad.
 * 
 * @author KralTHJ
 */
public class ValidateSequence {
    
    /**
     * Calculates the melting temperature of a primer/probe.
     * The melting temperature, or Tm, is the temperature when 50% of the DNA
     * is melted of its counterpart
     * 
     * @param seq       Sequence to be validated
     * @return double   The melting temperature of the primer/probe
     */
    public double calculateTm (String seq) {
        //TODO: Unnecesarry comment.
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
     * @param seq       Sequence to be validated
     * @return double   GC content of the primer/probe
     */
    public double calculateGc (String seq) {
        
        /*
        Count Guanine and Cytosine nucleotides
        */
        int count = 0;
        double gcContent = 0;
        for (int i = 0; i < seq.length(); i++) {
            if (seq.charAt(i) == 'G' || seq.charAt(i) == 'C') {
                count++;
            }
        }
        
        /*
        Implement formula:
        (G+C) / total * 100%
        */
        gcContent = (count / (double)seq.length()) * 100;
        gcContent = Math.round(gcContent);
        
        return gcContent;
        
    }//calculateGc()
    
    /**
     * If the sequence has to many repeating groups of two nucleotides, the 
     * primer/probe is not eligable for usage.
     * True = valid | False = invalid
     * 
     * @param seq       Sequence to be validated
     * @return boolean  Boolean whether Di-repeats are present
     */
    public boolean checkDiRepeats (String seq) {
        
        /*
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
            System.out.println(seq);
        }
        
        return useable;
    }//checkDiRepeats()
    
    /**
     * If the sequence has to many repeating nucleotides the primer/probe is not 
     * usefull.
     * True = valid | False = invalid
     * 
     * @param seq       Sequence to be validated
     * @return boolean  Whether a primer contains single nucleotide repeats
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
     * True = valid | False = invalid. FOR PRIMERS ONLY
     * 
     * @param seq       Sequence to be validated
     * @return boolean  Whether the last five nucleotides contain 2 G/C or not
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
        Change boolean if there are more than 2 G's or C's
        */
        if (nucC > 2 || nucG > 2) {
            isUsable = false;
        }
        
        return isUsable;
    }//threeEnd()
    
    /**
     * A Guanine at the end of a probe is not allowed.
     * This has a quenching effect of the probe.
     * True = valid | False = invalid
     * 
     * @param seq       Sequence to be validated
     * @return boolean  Whether a probe has a Guanine at the 3' end or not
     */
    public boolean checkThreeTerminal (String seq) {
        
        if (seq.charAt(seq.length() - 1) == 'G' ) {
            return true;
        } else {
            return false;
        }
        
    }//checkThreeTerminal()
    
    /**
     * Evaluates whether a primer or probe can form a 'hairpin' structure.
     * When both ends of a sequence are complementary, there is a possibility
     * that those will anneal. This will render the primer or probe useless.
     * True = valid | False = invalid. FOR PROBES ONLY
     * 
     * @param seq       Sequence to be validated
     * @return boolean  Whether a hairpin can be formed
     */
    public boolean checkHairpin (String seq) {
        
        
        
        return true;
    }//checkHairpin()
    
    
    public void checkUnicity () {
        
    }//checkUnicity()
    
}//Class()
