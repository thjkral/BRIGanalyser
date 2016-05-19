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
    }//main()

    /**
     * -
     * @param filePath 
     */
    private void start(String filePath) {
        
        
        /*
        Make Document object from XML file
        */
        ReadFile parser = new ReadFile();
        Document doc = parser.makeDocument(filePath);
        
        /*
        Isolate gaps from the doc
        */
        IsolateGaps iso = new IsolateGaps();
        ArrayList<Ring> ringList = iso.isolate(doc);
        
//        System.out.println("Number of gaps (not unique):");
//        for (Ring r : ringList) {
//            System.out.println("Ring " + r.getName() + ": " + r.getGapsArray().size());
//        }
        
        /*
        Find the unique markers
        */
        FindUniqueMarkers fum = new FindUniqueMarkers();
        ArrayList<UniqueMarker> umList = fum.start(ringList);
//        System.out.println("Found " + umList.size() + " unique markers in total");
        
                
        
        /*
        Isolate primers
        */
        GeneratePrimers gp = new GeneratePrimers();
        gp.generate(umList);
        
        
    }//start()

    
    
}//class()

