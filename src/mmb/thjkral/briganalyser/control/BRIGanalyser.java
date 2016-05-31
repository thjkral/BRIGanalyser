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
        //TODO: Make the filePath an argument given to this method, use the filePath below as default if it is undefined, like so:
        // String filePath = args[index] || Defaults.FILE_PATH
        String filePath = "\\\\zkh\\dfs\\Gebruikers12\\KralTHJ\\Data\\Workspace\\outputBRIG\\dataSigrid\\output_1\\scratch\\genome_1_16090044731-01.fna.xml";
        ba.start(filePath);
    }//main() // TODO: Remove these after methods, unnecessary bloat

    /**
     * -
     * @param filePath 
     */
    private void start(String filePath) {
        
        //TODO: Make this into single line comment
        /*
        Make Document object from XML file
        */
        ReadFile parser = new ReadFile();
        //TODO: Tip, turn all the System.out.println statements into a Logger class which automatically formats the text.
        // this will make your code easier to read and improves consitency of the log output. For example, if you make
        // a logger which automagically adds a timestamp before the message you will get nicely formatted/useful information
        // like so:
        // [31-05-2016, 14:23, BRIGanalyser] reading file
        System.out.println("* reading file");
        Document doc = parser.makeDocument(filePath);

        //TODO: Make this into single line comment
        /*
        Isolate gaps from the doc
        */
        IsolateGaps iso = new IsolateGaps();
        System.out.println("\n* isolating gaps");
        ArrayList<Ring> ringList = iso.isolate(doc);
        
               
        System.out.println("\tNumber of gaps (not unique):");
        for (Ring r : ringList) {
            System.out.println("\tRing " + r.getName() + ": " + r.getGapsArray().size());
        }

        //TODO: Make this into single line comment
        /*
        Find the unique markers
        */
        FindUniqueMarkers fum = new FindUniqueMarkers();
        System.out.println("\n* finding unique markers");
        ArrayList<UniqueMarker> umList = fum.start(ringList);
        System.out.println("\tFound " + umList.size() + " unique markers in total");
        
        //some UM info
        int avgLength = 0;
        int biggest = 0;
        
        for (UniqueMarker u : umList) {
//            System.out.println(u.toString()); // TODO: Remove commented code.
            avgLength = avgLength + u.getLength();
            if (u.getLength() > biggest) {
                biggest = u.getLength();
            }
        }
        System.out.println("\tAverage length in scope: " + (avgLength / umList.size()));
        System.out.println("\tLongest UniqueMarker is " + biggest + " bp");


        // TODO: Remove commented code.
//        CGViewTest test = new CGViewTest();
//        test.test(umList); 

        //TODO: Make this into single line comment
        /*
        Isolate primers
        */
        GeneratePrimers gp = new GeneratePrimers();
        
        System.out.println("\n* generating primers");
        String timeStampBegin = new SimpleDateFormat("HH:mm:ss (dd/MM/yyyy)").format(Calendar.getInstance().getTime());
        System.out.println("\t--Started making primers at: " + timeStampBegin + "--");
        
        gp.generate(umList);
        
        String timeStampEnd = new SimpleDateFormat("HH:mm:ss (dd/MM/yyyy)").format(Calendar.getInstance().getTime());
        System.out.println("\t--Ended making primers at: " + timeStampEnd + "--");
        
        
    }//start()

    
    
}//class()

