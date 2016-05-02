/*
 * This code is created by:
 * Tom Kral | Bioinformatics intern
 * t.h.j.kral@umcg.nl | +31642935544
 * 
 * Commissioned by:
 * University Medical Center Groningen | Medical Microbiology
 */
package mmb.thjkral.briganalyser.control;

import java.util.ArrayList;
import mmb.thjkral.briganalyser.model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 * Main class.
 * All operations will be executed from here
 * @author KralTHJ
 */
public class BRIGanalyser {
    
    /**
     * Main mathod.
     * From here, all logic will be started.
     * @param args
     */
    public static void main(String[] args) {
        
        BRIGanalyser ba = new BRIGanalyser();
        
        String filePath = "\\\\zkh\\dfs\\Gebruikers12\\KralTHJ\\Data\\Workspace\\ProefdataBRIG\\exampleData\\output_12\\scratch\\BRIGExample.fna.xml";
        ba.start(filePath);
    }

    private void start(String filePath) {
        
        /*
        1. Parse XML, make Document object
        2. Make Ring objects
        3. Make Gap objects
        4. Identify Unique Markers
        */
        
        //make Document object
        ParseXML parser = new ParseXML();
        Document doc = parser.makeDocument(filePath);
        
        //generate rings from the doc
        ArrayList<Ring> ringList = generateRings(doc);
        
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
                startCor.add(features.getAttribute("start"));
                //get stoppositions
                stopCor.add(features.getAttribute("stop"));
            }
            
            //create Ring objects
            Ring r = new Ring(Integer.toString(i), startCor, stopCor);
            ringArray.add(r);            
        }
        
        return ringArray;

    }
    
    
}

