/*
 * This code is created by:
 * Tom Kral | Bioinformatics intern
 * t.h.j.kral@umcg.nl | +31642935544
 * 
 * Commissioned by:
 * University Medical Center Groningen | Medical Microbiology
 */
package mmb.thjkral.briganalyser.model;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Execute operations on XML files.
 * Utilises the Java Document Object Model.
 * @author KralTHJ
 */
public class ParseXML {

    /**
     * Makes a Document object of the input XML file.
     * @param filePath
     * @return Document doc
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
        
    }//makeDocument()
    
    /**
     *
     * @param fileLocation
     * @param start
     * @param stop
     * @return
     */
    public String getReferenceSequence(String fileLocation, int start, int stop) {
        
        fileLocation = "\\\\zkh\\dfs\\Gebruikers12\\KralTHJ\\Data\\Programs\\BRIG\\BRIG_examples\\Chapter5_6_8_wholeGenomeExamples\\BRIGExample.fna";
        String subSequence = "";
        
        Path path = Paths.get(fileLocation);
        
        try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())){
            
            String line = "";
            int passedLetters = 0;
            
            String regex = "[ATCGatcg]";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(line);
            
            while ((line = br.readLine()) != null) {
                
                for (int i = 0; i < line.length(); i++) {                    
                    if (line.charAt(i) == '$') {
                        passedLetters++;
                        
                        if (passedLetters >= start || passedLetters <= stop) {
                            System.out.println("Found one!");
                            subSequence = subSequence.concat(Character.toString(line.charAt(i)));
                        }
                    }
                    
//                    String currLetter = Character.toString(line.charAt(i));
//                    if (line.charAt(i) >= start && line.charAt(i) <= stop) {
//                        subSequence = subSequence.concat(i);
//                    }
                }
                
                //referenceGenome = subSequence.concat(line);
            }
            br.close();
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        subSequence = subSequence.replaceAll("\\s+","");
        System.out.println(subSequence);
        
        return subSequence;
    }//getReferenceSequence()
    
    
}//class()
