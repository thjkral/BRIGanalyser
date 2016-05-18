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

    public void generate(ArrayList<UniqueMarker> umList) {
        
                
        for (UniqueMarker u : umList) {
            makePrimers(u.getSequenceForward(), PrimerOrientation.FORWARD, u.getNumber());
            makePrimers(u.getSequenceReverse(), PrimerOrientation.REVERSE, u.getNumber());
        }
        
        
        
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
        int count = 0;
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
        
        if (orientation == PrimerOrientation.REVERSE) {
            seq = new StringBuilder(seq).reverse().toString();
        }
        
        for (int i = maxAvailableSeq; i <= minAvailableSeq; i++) {      //from min cutoff to max cutoff
            for (int j = minPrimerLength; j <= maxPrimerLength; j++) {  //for every desired primer length
                for (int k = 0; k <= seq.length(); k++) {              //for every letter
                    int end = k + j;
                    if (end <= i) {
                        String primerSeq = seq.substring(k, end);
                        count++;
                        
                        double meltTemp = validate.calculateTm(primerSeq);
                        double gcContent = validate.calculateGc(primerSeq);
                        
                        
                        
                        if ((meltTemp >= 58 && meltTemp <= 60) && (gcContent >= 30 && gcContent <= 80)) {
                            //to database
                        }
                        
                        Primer p = new Primer(number, primerSeq, primerSeq.length(), orientation);
                        
                    }
                    
                }
            }
        }
        
        
    }//makePrimers()
    
    
    
    
    
    
    private void makeForwardPrimers (String seqF, int number) {
        /*
        GLOBAL VALUES. PLEASE DELETE WHEN MORE FUNCTIONALITY IS PRESENT!
        */
        int maxProdLength = 10;
        int minProdLength = 6;
        int maxPrimerLength = 10;
        int minPrimerLength = 8;
        
        int maxCutOff = maxPrimerLength + maxProdLength;
        int minCutOff = minPrimerLength + minProdLength;
        
        int maxAvailableSeq = seqF.length() - maxCutOff;
        int minAvailableSeq = seqF.length() - minCutOff;
        
        ValidatePrimers validate = new ValidatePrimers();
        
        for (int i = maxAvailableSeq; i <= minAvailableSeq; i++) {      //from min cutoff to max cutoff
            for (int j = minPrimerLength; j <= maxPrimerLength; j++) {  //for every desired primer length
                for (int k = 0; k <= seqF.length(); k++) {              //for every letter
                    int end = k + j;
                    if (end <= i) {
                        String primerSeq = seqF.substring(k, end);
                        
                        
                        double meltTemp = validate.calculateTm(primerSeq);
                        double gcContent = validate.calculateGc(primerSeq);
                        
                        
                        
                        if ((meltTemp >= 58 && meltTemp <= 60) && (gcContent >= 30 && gcContent <= 80)) {
                            //to database
                        }
                        
                        Primer p = new Primer(number, primerSeq, primerSeq.length(), PrimerOrientation.FORWARD);
                        
                    }
                    
                }
            }
        }
        
        
    }//makeForwardPrimers()
    
    private void makeReversePrimers (String seqR, int number) {
        /*
        GLOBAL VALUES. PLEASE DELETE WHEN MORE FUNCTIONALITY IS PRESENT!
        */
        int maxProdLength = 10;
        int minProdLength = 6;
        int maxPrimerLength = 10;
        int minPrimerLength = 8;
        
        int maxCutOff = maxPrimerLength + maxProdLength;
        int minCutOff = minPrimerLength + minProdLength;
        
        int maxAvailableSeq = seqR.length() - maxCutOff;
        int minAvailableSeq = seqR.length() - minCutOff;
        
        seqR = new StringBuilder(seqR).reverse().toString();
        ValidatePrimers validate = new ValidatePrimers();
        
        for (int i = maxAvailableSeq; i <= minAvailableSeq; i++) {      //from min cutoff to max cutoff
            for (int j = minPrimerLength; j <= maxPrimerLength; j++) {  //for every desired primer length
                for (int k = 0; k <= seqR.length(); k++) {              //for every letter
                    int end = k + j;
                    if (end <= i) {
                        String primerSeq = seqR.substring(k, end);
                        Primer p = new Primer(number, primerSeq, primerSeq.length(), PrimerOrientation.REVERSE);
                        
                        double meltTemp = validate.calculateTm(p.getSequence());
                        double gcContent = validate.calculateGc(p.getSequence());
                        
                        if ((meltTemp >= 58 && meltTemp <= 60) && (gcContent >= 30 && gcContent <= 80)) {
                            //to database
                        }
                        
                    }
                    
                }
            }
        }
        
        
    }//makeReversePrimers()
    
    
    
    
}//class()
