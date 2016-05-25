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
 * Makes Ring and Gap objects from the XML input.
 * The XML describes the rings BRIG makes. These are not continuous however and
 * have start- and stop positions. The Gaps in between are of great interest and
 * are isolated too.
 * 
 * @author KralTHJ
 */
public class IsolateGaps {
    
    /**
     * Controls the continuity of the Class.
     * 
     * @param doc Document type made by the Documentbuilder package
     * @return ArrayList with Ring objects, each containing a ArrayList with gaps
     */
    public ArrayList<Ring> isolate (Document doc) {
        
        /*
        Steps:
        1. generateRings
        2. add list of gaps to each Ring
        3. return ArrayList
        */        
        ArrayList<Ring> ringList = makeRings(doc);
        makeGaps(ringList);
        return ringList;
    }
    
    
    /**
     * Makes Ring objects from the input XML.
     * @param doc
     * @return ArrayList<Ring>
     */
    private ArrayList<Ring> makeRings(Document doc) {
        
        /*
        Document to Element
        */
        Element el = doc.getDocumentElement();
        
        /*
        All featureSlot elements (rings) in list
        */
        NodeList nList = el.getElementsByTagName("featureSlot");
//        System.out.println("Number of rings: " + nList.getLength());


        /*
        ArrayList for holding Ring objects
        */
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
                /*
                Get start- and stop positions
                */
                startCor.add(Integer.parseInt(features.getAttribute("start")));
                stopCor.add(Integer.parseInt(features.getAttribute("stop")));
            }
            
            /*
            Sort coordinates
            */
            Collections.sort(startCor);
            Collections.sort(stopCor);
            /*
            Create Ring objects and add all to ArrayList
            */
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
    private void makeGaps(ArrayList<Ring> ringList) {
        
        /*
        Iterate over all Ring objects.
        The trick is to connect a stop position to a start position of another
        Ring. The area in between is the Gap. So start positions become stop
        positions and stop positions become start positions. Some iteratations
        require different actions The first Gap is from 0 to the first start 
        position and the last Gap is from the last stop position to the end of 
        the sequence.
        */
        for (Ring ring : ringList) {//iterate list with Rings
            
            ArrayList<Gap> gapList = new ArrayList();
            
            for (int i = 0; i < ring.startPositions.size(); i++) {
                                
                if (i == 0) {//at first iteration
                    Gap g = new Gap(ring.getName(), 0, (int) ring.startPositions.get(i));
                    gapList.add(g);
                }
                else if (i == ring.startPositions.size()) {//at last iteration
                    Gap g = new Gap(ring.getName(), (int) ring.stopPositions.get(i), ring.startPositions.size());
                    gapList.add(g);
                }
                else {//rest of the iterations
                    Gap g = new Gap(ring.getName(), (int) ring.stopPositions.get(i - 1), (int) ring.startPositions.get(i));
                    gapList.add(g);
                }
            }
            ring.setGapsArray(gapList);
        } 
        
        
        
    }//makeGaps()
    
    
    
    
    
}//class
