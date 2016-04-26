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
    
    public static void main(String[] args) {
        
        BRIGanalyser ba = new BRIGanalyser();
        
        String filePath = "\\\\zkh\\dfs\\Gebruikers12\\KralTHJ\\Data\\ProefdataBRIG\\exampleData\\output_10\\scratch\\BRIGExample.fna.xml";
        ba.start(filePath);
    }

    private void start(String filePath) {
        
        ParseXML parser = new ParseXML();
        Document doc = parser.makeDocument(filePath);
        
        //GenerateRings gr = new GenerateRings();
        
        
        if (doc != null) {
            NodeList nList = doc.getElementsByTagName("feature");
            System.out.println("Length: " + nList.getLength());
            
        } else {
            System.out.println("Problems!");
        }
        
    }
    
    
}
