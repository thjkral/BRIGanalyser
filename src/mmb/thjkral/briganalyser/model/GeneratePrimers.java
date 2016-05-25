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
import mmb.thjkral.briganalyser.enums.PrimerOrientation;

/**
 *
 * @author KralTHJ
 */
public class GeneratePrimers {
    
    int count = 0;
    int countTotal = 0;

    public void generate(ArrayList<UniqueMarker> umList) {
        
                
        for (UniqueMarker u : umList) {
            makePrimers(u.getSequenceForward(), PrimerOrientation.FORWARD, u.getNumber());
            makePrimers(u.getSequenceReverse(), PrimerOrientation.REVERSE, u.getNumber());
        }
        
        System.out.println("\nMade " + countTotal + " primers in total");
        System.out.println("Saved " + count + " primers");
        
    }//generate()
    
    
    /**
     * Makes primers from any given sequence provided with the number of the
     * coresponding UniqueMarker and the orientation of the strand
     * 
     * @param seq
     * @param orientation
     * @param number 
     */
    private void makePrimers (String seq, PrimerOrientation orientation, int number) {
        /*
        GLOBAL VALUES. PLEASE DELETE WHEN MORE FUNCTIONALITY IS PRESENT!
        */
        int maxProdLength = 10;
        int minProdLength = 6;
        int maxPrimerLength = 10;
        int minPrimerLength = 8;
        
        
        /*
        A primer cannot be made for the entire sequence of an UniqueMarker. There
        has to be room left for another primer + productlength. The number of
        nucleotides at the end of the sequence that cannot be used is expressed
        as the cut off.
        */
        int maxCutOff = maxPrimerLength + maxProdLength;
        int minCutOff = minPrimerLength + minProdLength;
        
        /*
        With the cut off calculated, the maximal sequence available for making
        primers can be known. This is used to traverse through the sequence
        */
        int maxAvailableSeq = seq.length() - maxCutOff;
        int minAvailableSeq = seq.length() - minCutOff;
        
        /*
        Instance of the class ValidatePrimers. This contains methods to check the
        quality of primers.
        */
        ValidatePrimers validate = new ValidatePrimers();
        
        
        /*
        Order of traversion:
        1. from the minimal to the maximal nucleotides that must be preserved
           (see maxCutoff for example
        2. For every desired primer length
        3. Voor elke letter/nucleotide in de sequentie
        */
        for (int i = maxAvailableSeq; i <= minAvailableSeq; i++) {      //from min cutoff to max cutoff
            for (int j = minPrimerLength; j <= maxPrimerLength; j++) {  //for every desired primer length
                for (int k = 0; k <= seq.length(); k++) {               //for every letter
                    int end = k + j;
                    if (end <= i) {
                        String primerSeq = seq.substring(k, end);
                        
                        /*
                        If a primer originates from the reverse strand, it must be reversed. It 
                        is far easier in Java when you can start at the beginning of the String
                        */
                        if (orientation == PrimerOrientation.REVERSE) {
                            seq = new StringBuilder(seq).reverse().toString();
                        }
                        
                        double meltTemp = validate.calculateTm(primerSeq);
                        double gcContent = validate.calculateGc(primerSeq);
                        
                        countTotal++;
                        
                        /*
                        Continue is the primer has the correct melt tempersture
                        and GC content
                        */
                        if (/*meltTemp >= 58.0 && meltTemp <= 60.0 &&*/ gcContent >= 30.0 && gcContent <= 80.0) {
                            boolean diRepeat = validate.checkDiRepeats(primerSeq);
                            boolean monoRepeat = validate.checkMonoRepeats(primerSeq);
                            
                            if (monoRepeat && diRepeat) {
                                count++;
                                Primer p = new Primer(number, primerSeq, primerSeq.length(), orientation);
                            }
                            
                        }
                        
                        
                    }
                }
            }
        }
        
        
        
    }//makePrimers()
 
}//class()
