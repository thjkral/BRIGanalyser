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
        
    }//start()

    private void compare(ArrayList<Ring> ringList) {
        
        ArrayList<ArrayList<Gap>> overlappingGaps = new ArrayList<>();
        
        //compare gaps of first ring with the other gaps
        for (int i = 0; i < ringList.get(0).getGapsArray().size(); i++) {//for every Gap in Ring 1
            
            Gap gapOne = (Gap) ringList.get(0).getGapsArray().get(i);
            int start = gapOne.getStartCor();
            int stop = gapOne.getStopCor();
            
            ArrayList<Gap> matches = new ArrayList<>();
            matches.add(gapOne);
            
            for (int j = 0; j < ringList.size(); j++) {//for every other Ring
                
                for (int k = 0; k < ringList.get(j).getGapsArray().size(); k++) {//for every Gap of that Ring
                    
                    Gap gapTwo = (Gap) ringList.get(j).getGapsArray().get(k);
                    int startTwo = gapTwo.getStartCor();
                    int stopTwo = gapTwo.getStopCor();
                    
                    if (start >= startTwo && stop <= stopTwo) {
                        matches.add(gapTwo);
                    } else {
                        continue;
                    }
                    
                    
                }
            }
            overlappingGaps.add(matches);
        }
        
        
        //isolate UMs
        System.out.println("Number of overlappers: " + overlappingGaps.size());
        overlappingGaps = isolateUniqueMarkers(overlappingGaps);
        System.out.println("Number of valid overlappers: " + overlappingGaps.size());
        System.out.println(overlappingGaps.get(0));
        
        
        
    }//compare()
    
    private ArrayList<ArrayList<Gap>> isolateUniqueMarkers (ArrayList<ArrayList<Gap>> overlappingGaps) {
        
        //is the length of each array the same as the number of rings?
        //delete all who have length < number of rings
        
        for (int i = 0; i < overlappingGaps.size(); i++) {
            ArrayList currList = (ArrayList) overlappingGaps.get(i);
            if (currList.size() < 6) {
                overlappingGaps.remove(i);
            }
        }
        
        return overlappingGaps;
        
    }//isolateUniqueMarkers()
    
}//class()
