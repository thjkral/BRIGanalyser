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
 * Execute operations on XML files.
 * Utilises the Java Document Object Model.
 * @author KralTHJ
 */
public class ReadFile {

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
        
        /*
        What I want:
        >Genome
        acagaattgaTCTGAcggttttctctgacagcatacaatcaagtctaacagtccgccttg
        */
        
        fileLocation = "\\\\zkh\\dfs\\Gebruikers12\\KralTHJ\\Data\\Programs\\BRIG\\BRIG_examples\\Chapter5_6_8_wholeGenomeExamples\\BRIGExample.fna";
        String referenceGenomeSub = "";
        int passedLetters = 0;
        int passedLines = 0;
        
        //edit to indices
        start = start - 1;
        stop = stop - 1;
        
        Path path = Paths.get(fileLocation);
        
        
        
        try (BufferedReader br = Files.newBufferedReader(path, Charset.defaultCharset())){
            
            String line;
            
            while ((line = br.readLine()) != null) {
                
                if (!line.startsWith(">")) {//if line doesn't start with description
                    passedLetters = passedLetters + line.length();
                    
                    if (passedLines != 1) {
                        start = start - passedLetters;
                        stop = stop - passedLetters;
                    }
                    
                    
                    //subsequence is on one line
                    if (start < passedLetters && stop <= passedLetters) {                        
                        referenceGenomeSub = line.substring(start, stop);                        
                    }
                    
                    //subsequence is on multiple lines
                    else if (start <= passedLetters && stop > passedLetters) {
                        referenceGenomeSub = line.substring(start);
                    }
                    else if (passedLetters > stop && !referenceGenomeSub.isEmpty()) {
                        referenceGenomeSub = referenceGenomeSub.concat(line.substring(0, stop));
                    }
                    
                }
                
            }
            br.close();
            
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        
        referenceGenomeSub = referenceGenomeSub.replaceAll("\\s+","");
        System.out.println("referenceGenomeSub= " + referenceGenomeSub);
        
        return referenceGenomeSub;
        
    }//getReferenceSequence()
    
    
}//class()
