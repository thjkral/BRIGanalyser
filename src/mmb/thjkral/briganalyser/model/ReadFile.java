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
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 * Contains methods for reading different files.
 * The application requires different kinds of input. This Class gathers methods
 * to read these files. 
 * 
 * @author KralTHJ
 */
public class ReadFile {

    /**
     * Makes a Document object of a input XML file and returns this.
     * The output of BRIG (used as input here) consists of an XML file. These
     * contain information about the Rings. Files like these adhere to the CGView
     * XML lay-out since it's made by a Java package with the same name.
     * 
     * @param filePath      Location of the XML file
     * @return Document     Document object of the XML file
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
     * Extracts a part of a given reference sequence.
     * Reads a file containing the reference genome and returns a subsequence
     * between a given start- and stopposition. Reading an entire file and 
     * possibly saving it as variable is not an option due to computer memory.
     * This is why the methods only saves the part from the sequence that is 
     * needed. After the last nucleotide is reached, the method terminates.
     * 
     * @param fileLocation  Location of the input file
     * @param start         Begin of the subsequence
     * @param stop          End of the subsequence
     * @return String       Sequence of the UniqueMarker
     */
    public String getFromReferenceSequence(String fileLocation, int start, int stop) {
        
        fileLocation = "\\\\zkh\\dfs\\Gebruikers12\\KralTHJ\\Data\\Workspace\\real_data\\dataSigrid\\genome_1_16090044731-01.fna";
        String referenceGenomeSub = "";
        int passedLetters = 0;
        
        //edit to indices
        start = start - 1;
        stop = stop - 1;
        
        Path path = Paths.get(fileLocation);
        
        try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())){
            
            String line;
            
            while ((line = br.readLine()) != null) {
                
                if (line.startsWith(">")) {
                    continue;
                } else {//if line doesn't start with description
                    passedLetters = passedLetters + line.length();
                }
                
                /*
                Start of by determining if the current line is within range
                */
                if (start < passedLetters) {                    
                    /*
                    adjust the indices for proper substrings
                    */                    
                    int startIndex = start - (passedLetters - line.length());
                    int stopIndex = stop - (passedLetters - line.length());

                    /*
                    Determine if this is the first line encountered within range
                    */
                    if (referenceGenomeSub.isEmpty()) {
                        /*
                        Determine if the stop position is on the current line also
                        */
                        if (stop <= passedLetters) {//is the stop also on this line?
                            referenceGenomeSub = line.substring(startIndex, stopIndex);
                            break;
                        } else {
                            String tempSeq = line.substring(startIndex);
                            referenceGenomeSub = referenceGenomeSub.concat(tempSeq);
                        }

                    } else {
                        /*
                        Determine if the stop positions is on the current line
                        */
                        if (stop <= passedLetters) {
                            String tempSeq = line.substring(0, stopIndex);
                            referenceGenomeSub = referenceGenomeSub.concat(tempSeq);
                            break;
                        } else {
                            referenceGenomeSub = referenceGenomeSub.concat(line);
                        }
                            
                    }
                            
                }
                
            }
            br.close();
            
        } catch (IOException e) {
            System.out.println("Error while reading reference genome: " 
                    + e.getMessage());
        }
        
        /*
        Remove all white space and all characters to upper case before returning
        */
        referenceGenomeSub = referenceGenomeSub.replaceAll("\\s+","");
        referenceGenomeSub = referenceGenomeSub.toUpperCase();
        
        return referenceGenomeSub;
        
    }//getReferenceSequence()
    
    
}//class()
