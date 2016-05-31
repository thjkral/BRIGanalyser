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
 * 
 * Requires an ArrayList with Ring objects.
 * @author KralTHJ
 */
public class FindUniqueMarkers {
    //TODO: Make this comment single line
    /*
    GLOBAL VALUES. PLEASE DELETE WHEN MORE FUNCTIONALITY IS PRESENT!
    */
    //TODO: Turn this into an enum (for example UniqueMarkersDefaults.DEAFULT_TOTAL_LENGTH, UniqueMarkersDefaults.DEFAULT_NUM_RINGS)
    int totalLength = 5357923;
    int numRings = 2;
    
    /**
     * Coordinates the actions of this class by given the right information to 
     * the corresponding method.
     * 
     * @param ringList
     * @return 
     */
    public ArrayList<UniqueMarker> start(ArrayList<Ring> ringList) {
        
        ArrayList<ArrayList<Gap>> overlapList = compare(ringList);
        ArrayList<UniqueMarker> umList = isolateUniqueMarkers(overlapList);
        
        return(umList);
        
    }//start()

    
    /**
     * Compares the gaps of the first ring to the gaps of other rings.
     * The goal is to determine which gaps are overlapping. This means there
     * might be a suitable UniqueMarker.
     * 
     * @param ringList  ArrayList with Ring objects
     * @return 
     */
    private ArrayList<ArrayList<Gap>> compare(ArrayList<Ring> ringList) {
        
        /*
        This ArrayList contains other ArrayLists which contain gaps that overlap
        */
        ArrayList<ArrayList<Gap>> overlappingGaps = new ArrayList<>();
        
        /*
        Take the first Ring object from the ArrayList and compare all his Gap
        objects with those of the other Ring objects
        */
        for (int i = 0; i < ringList.get(0).getGapsArray().size(); i++) {//for every Gap in Ring 1
            
            Gap gapOne = (Gap) ringList.get(0).getGapsArray().get(i);
            int start = gapOne.getStartCor();
            int stop = gapOne.getStopCor();


            //TODO: Remove this.
            /*
            
            */
            ArrayList<Gap> matches = new ArrayList<>();
            matches.add(gapOne);


            //TODO: Tip: if you have to describe what you are doing in the comments, your code isn't written clear enough.
            // Try to use variable names that describe the object and only use comments to describe WHY you are doing
            // something, not HOW. Another programmer should be able to distill what is going on by reading the code,
            // not the comments.
            for (int j = 1; j < ringList.size(); j++) {//for every other Ring
                
                for (int k = 0; k < ringList.get(j).getGapsArray().size(); k++) {//for every Gap of that Ring
                    
                    Gap gapTwo = (Gap) ringList.get(j).getGapsArray().get(k);
                    int startTwo = gapTwo.getStartCor();
                    int stopTwo = gapTwo.getStopCor();
                    
                    if (start >= startTwo && stop <= stopTwo) {
                        matches.add(gapTwo);
                    }
                    
                }
            }
            if (matches.size() == numRings) {//if all rings share overlap
                overlappingGaps.add(matches);
            }
            
        }
        //TODO: Remove commented code.
//        System.out.println("There are " + overlappingGaps.size() + " overlapping gaps");
        return overlappingGaps;
        
    }//compare()
    
    /**
     * Makes UniqueMarker objects from a list of gaps.
     * 
     * @param overlappingGaps
     * @return 
     */
    private ArrayList<UniqueMarker> isolateUniqueMarkers (ArrayList<ArrayList<Gap>> overlappingGaps) {
       
        ArrayList<UniqueMarker> umList = new ArrayList<>();
        
        ReadFile parser = new ReadFile();


        //TODO: Describe why, not how. (see comment above)
        /*
        Determine the start and stop position of the UniqueMarker on the reference
        genome. From the overlapping gaps, this is the highest start position
        and the lowest stop position. This is done by comparing one gap to the 
        rest. Every time a better position is discovered, the best position is
        saved.
        */
        for (int i = 0; i < overlappingGaps.size(); i++) {//for every array
            
            //set highest startposition to first Gap in ArrayList
            int startHighest = overlappingGaps.get(i).get(0).getStartCor();
            //set lowest stopposition to first Gap in ArrayList
            int stopLowest = overlappingGaps.get(i).get(0).getStopCor();
            
            for (int j = 0; j < overlappingGaps.get(i).size(); j++) {//for every gap in array
                
                int startCurr = overlappingGaps.get(i).get(j).getStartCor();
                int stopCurr = overlappingGaps.get(i).get(j).getStopCor();
                
                if (startCurr >= startHighest) {
                    startHighest = startCurr;
                }
                if (stopCurr <= stopLowest) {
                    stopLowest = stopCurr;
                }
            }
            
            /*
            First, calculate the length (= distance between to positions) of the
            possible UniqueMarker
            */
            int difference = stopLowest - startHighest;
            
            /*
            If the length is sufficient: isolate the sequence from the reference-
            genome and make a UniqueMarker object.
            */
            if (difference >= 100) {
                String markerSequence = parser.getFromReferenceSequence("", startHighest, stopLowest);
                //TODO: Remove commented code
//                String tempSequence = "ATACAGATATAGACAAGCGCGCGCCCGCTAGAGAGCACGTCGCGCGAGCGTGTTTGCGCGCGAAAAGCGCGCTGAGATTCGCGCATACAGATATAGACAAGCGCGCGCCCGCTAGAGAGCACGTCGCGCGAGCGTGTTTGCGCGCGAAAAGCGCGCTGAGATTCGCGC";
                UniqueMarker um = new UniqueMarker(i, 
                        startHighest, 
                        stopLowest, 
                        difference, 
                        markerSequence,
                        makeComplementary(markerSequence));
                umList.add(um);
            }
            //TODO: Remove commented code
//            else if (difference >= 24 && difference < 500) {
//                
//                
//                
//            }
            
        }
        
        return umList;
        
    }//isolateUniqueMarkers()
    
    
    
    /**
     * Returns the complement of a given DNA sequence.
     * 
     * @param sequence  DNA sequence
     * @return String   Sequence complementary to input
     */
    private String makeComplementary (String sequence) {
        //TODO: Make this into single line comment
        /*
        Reverse the sequence
        */
        String seqComp = new StringBuilder(sequence).reverse().toString();

        //TODO: No shit, sherlock.
        /*
        With StringBuilder(), a new String is made.
        */
        StringBuilder complement = new StringBuilder();
        
        /*
        Traverse through the sequence. Everytime a nucleotide is encountered,
        add to the new String
        */
        for (int i = 0; i < seqComp.length(); i++) {
            char c = seqComp.charAt(i);
            
            if (c == 'A') {
                complement.append('T');
            }
            if (c == 'T') {
                complement.append('A');
            }
            if (c == 'G') {
                complement.append('C');
            }
            if (c == 'C') {
                complement.append('G');
            }            
        }
        
        return complement.toString();
        
    }//makeComplementary()
    
}//class()
