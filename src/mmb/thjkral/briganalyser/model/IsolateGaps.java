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
import java.util.Collections;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 * @author KralTHJ
 */
public class IsolateGaps {
    
    public ArrayList<Gap> isolate (Document doc) {
        
        //generateRings
        ArrayList<Ring> ringList = generateRings(doc);
        
        //get gaps
        ArrayList<Gap> gapList = makeGaps(ringList);
        return gapList;
        
    }
    
    
    /**
     * Makes Ring objects from the input XML.
     * @param doc
     * @return ArrayList<Ring>
     */
    private ArrayList<Ring> generateRings(Document doc) {
        
        //Dcocument to Element
        Element el = doc.getDocumentElement();
        
        //All featureSlot elements (rings) in list
        NodeList nList = el.getElementsByTagName("featureSlot");
        System.out.println("Number of rings: " + nList.getLength());

        //get the total length of the reference genome
        int totalLength = Integer.parseInt(el.getAttribute("sequenceLength"));
        System.out.println("Total length reference: " + totalLength);

        //ArrayList for holding Ring objects
        ArrayList<Ring> ringArray = new ArrayList();
        
        for (int i = 0; i < nList.getLength(); i++) {
            Element ringTag = (Element) nList.item(i);
            //fetch all featureRange nodes
            NodeList startStopCor = ringTag.getElementsByTagName("featureRange");
            
            //make ArrayLists for coordinates
            ArrayList startCor = new ArrayList();
            ArrayList stopCor = new ArrayList();
            
            for (int j = 0; j < startStopCor.getLength(); j++) {//traverse all featureRange nodes
                Element features = (Element) startStopCor.item(j);
                //get startposotion
                startCor.add(Integer.parseInt(features.getAttribute("start")));
                //get stoppositions
                stopCor.add(Integer.parseInt(features.getAttribute("stop")));
            }
            
            //sort coordinates
            Collections.sort(startCor);
            Collections.sort(stopCor);
            
            //create Ring objects
            Ring r = new Ring(Integer.toString(i), startCor, stopCor);
            ringArray.add(r);
        }
        
        return ringArray;

    }//generateRings()

    /**
     * Isolates gaps based on the Rings.
     * @param ringList
     * @return ArrayList<Gap> gapList
     */
    private ArrayList makeGaps(ArrayList<Ring> ringList) {
        
        ArrayList<Gap> gapList = new ArrayList();        
        
        for (Ring ring : ringList) {//iterate list with Rings
                        
            ArrayList start = ring.startPositions;
            ArrayList stop = ring.stopPositions;
            
            System.out.println("Starts: "
                + start.size()
                + " | Stops: "
                + stop.size());
            
            for (int i = 0; i < ring.startPositions.size(); i++) {
                
                //make a new start position from previous stop position
                int newStart = (int) ring.stopPositions.get(i) - 1;
                
                if (i == 0) {//at first iteration
                    Gap g = new Gap(0, i);
                    gapList.add(g);
                }
                else if (i == ring.startPositions.size()) {//at last iteration
                    Gap g = new Gap(newStart, newStart);
                }
                else {//rest of the iterations
                    Gap g = new Gap(newStart, i);
                    gapList.add(g);
                }
            }            
        } 
        
        return gapList;
        
        
    }//makeGaps()
    
    
    
    
    
}//class
