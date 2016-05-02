/*
 * This code is created by:
 * Tom Kral | Bioinformatics intern
 * t.h.j.kral@umcg.nl | +31642935544
 * 
 * Commissioned by:
 * University Medical Center Groningen | Medical Microbiology
 */
package mmb.thjkral.briganalyser.control;

//import mmb.thjkral.briganalyser.view.*;
import java.util.ArrayList;
import mmb.thjkral.briganalyser.model.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
        
        if (doc != null) {            
            generateRings(doc);
        } else {
            throw new NullPointerException();
        }
        
    }

    private void generateRings(Document doc) {
        
        Element el = doc.getDocumentElement();
        
        NodeList nList = el.getElementsByTagName("featureSlot");
        System.out.println("Length: " + nList.getLength());

        int totalLength = Integer.parseInt(el.getAttribute("sequenceLength"));
        System.out.println("Total length: " + totalLength);

        ArrayList<Ring> ringArray = new ArrayList();

        for (int i = 0; i < nList.getLength(); i++) {
            Element ringTag = (Element) nList.item(i);

        }


//        for (int i = 0; i < nList.getLength(); i++) {
//
//            NodeList fList = nList.item(i).getChildNodes();
//            System.out.println(nList.item(i).getNodeName()
//                + " No. "
//                + i
//                + " has "
//                + fList.getLength()
//                + " childnodes. First is called: "
//                + fList.item(0).getNodeName());
//
//        }


    }
    
    
}

