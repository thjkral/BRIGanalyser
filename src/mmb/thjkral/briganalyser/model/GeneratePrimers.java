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
            makeForwardPrimers(u.getSequenceForward(), u.getNumber());//make forward
            makeReversePrimers(u.getSequenceReverse(), u.getNumber());//make reverse
        }
        
        
        
    }//generate()
    
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
        
        for (int i = maxAvailableSeq; i <= minAvailableSeq; i++) {//from min cutoff to max cutoff
            for (int j = minPrimerLength; j <= maxPrimerLength; j++) {//for every desired primer length
                for (int k = 0; k <= seqF.length(); k++) {//for every letter
                    int end = k + j;
                    if (end <= i) {
                        String primerSeq = seqF.substring(k, end);
                        
                        
                        double meltTemp = validate.calculateTm(primerSeq);
                        double gcContent = validate.calculateGc(primerSeq);
                        
                        
                        
                        if (meltTemp >= 60 && meltTemp <= 65) {
                            //to database
                        }
                        if (gcContent >= 50 && gcContent <= 65) {
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
        
        for (int i = maxAvailableSeq; i <= minAvailableSeq; i++) {//from min cutoff to max cutoff
            for (int j = minPrimerLength; j <= maxPrimerLength; j++) {//for every desired primer length
                for (int k = 0; k <= seqR.length(); k++) {//for every letter
                    int end = k + j;
                    if (end <= i) {
                        String primerSeq = seqR.substring(k, end);
                        Primer p = new Primer(number, primerSeq, primerSeq.length(), PrimerOrientation.FORWARD);
                        
                        double meltTemp = validate.calculateTm(p.getSequence());
                        double gcContent = validate.calculateGc(p.getSequence());                    
                        if (meltTemp >= 60 && meltTemp <= 65) {
                            //to database
                        }
                        if (gcContent >= 50 && gcContent <= 65) {
                            //to database
                        }  
                        
                    }
                    
                }
            }
        }
        
        
    }//makeReversePrimers()
    
    
    
}//class()
