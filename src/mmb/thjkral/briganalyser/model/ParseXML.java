/*
 * This code is created by:
 * Tom Kral | Bioinformatics intern
 * t.h.j.kral@umcg.nl | +31642935544
 * 
 * Commissioned by:
 * University Medical Center Groningen | Medical Microbiology
 */
package mmb.thjkral.briganalyser.model;


import java.io.FileInputStream;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author KralTHJ
 */
public class ParseXML {

    /**
     *
     * @param filePath
     * @return
     */
    public Document makeDocument(String filePath) {
        
        DocumentBuilderFactory DomFactory = 
                DocumentBuilderFactory.newInstance();
        
        DocumentBuilder builder = null;
        
        try {
            builder = DomFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.out.println("Error: "
                + e.getMessage());
        }
        
        Document doc = null;
        
        try {
            doc = builder.parse(new FileInputStream(filePath));
        } catch (NullPointerException | SAXException | IOException e) {
            System.out.println("Error: "
                + e.getMessage());
        }
        
        if (doc != null) {
            return doc;
        } else {
            return null;
        }
        
    }
    
    
}
