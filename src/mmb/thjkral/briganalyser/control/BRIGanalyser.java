/*
 * This code is created by:
 * Tom Kral | Bioinformatics intern
 * t.h.j.kral@umcg.nl | +31642935544
 * 
 * Commissioned by:
 * University Medical Center Groningen | Medical Microbiology
 */
package mmb.thjkral.briganalyser.control;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
        
//        String filePath = "\\\\zkh\\dfs\\Gebruikers12\\KralTHJ\\Data\\Workspace\\ProefdataBRIG\\exampleData\\output_12\\scratch\\BRIGExample.fna.xml";
        String filePath = "\\\\zkh\\dfs\\Gebruikers12\\KralTHJ\\Data\\Workspace\\ProefdataBRIG\\ownData\\output\\output_1\\scratch\\SalmonellaEnterica_CT18.fasta.xml";
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
        System.out.println("* reading file");
        Document doc = parser.makeDocument(filePath);
        /*
        Isolate gaps from the doc
        */
        IsolateGaps iso = new IsolateGaps();
        System.out.println("\n* isolating gaps");
        ArrayList<Ring> ringList = iso.isolate(doc);
        
               
        System.out.println("Number of gaps (not unique):");
        for (Ring r : ringList) {
            System.out.println("Ring " + r.getName() + ": " + r.getGapsArray().size());
        }
        
        /*
        Find the unique markers
        */
        FindUniqueMarkers fum = new FindUniqueMarkers();
        System.out.println("\n* finding unique markers");
        ArrayList<UniqueMarker> umList = fum.start(ringList);
        System.out.println("Found " + umList.size() + " unique markers in total");
//        for (UniqueMarker u : umList) {
//            System.out.println(u.toString());
//        }
        

//        CGViewTest test = new CGViewTest();
//        test.test(umList); 
        
        /*
        Isolate primers
        */
        GeneratePrimers gp = new GeneratePrimers();
        
        System.out.println("* generating primers");
        String timeStampBegin = new SimpleDateFormat("dd/MM/yyyy_HH:mm:ss").format(Calendar.getInstance().getTime());
        System.out.println("Started at: " + timeStampBegin);
        
        gp.generate(umList);
        
        String timeStampEnd = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        System.out.println("Ended at: " + timeStampEnd);
        
        
    }//start()

    
    
}//class()

