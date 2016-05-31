/*
 * This code is created by:
 * Tom Kral | Bioinformatics intern
 * t.h.j.kral@umcg.nl | +31642935544
 * 
 * Commissioned by:
 * University Medical Center Groningen | Medical Microbiology
 */
package mmb.thjkral.briganalyser.model;
import ca.ualberta.stothard.cgview.*;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author KralTHJ
 */
public class CGViewTest implements CgviewConstants {
    //TODO: I assume this is a feature test. If so, move into a seperate package/directory/namespace to seperate concerns.
    public void test (ArrayList<UniqueMarker> umList){
        int length = 5054053;
	Cgview cgview = new Cgview(length);
	
	//some optional settings
	cgview.setWidth(1000);
	cgview.setHeight(1000);
	cgview.setBackboneRadius(1600.0f);
	cgview.setTitle("Test");
	cgview.setLabelPlacementQuality(10);
	cgview.setShowWarning(true);
	cgview.setLabelLineLength(8.0d);
	cgview.setLabelLineThickness(0.5f);
        
	//create a FeatureSlot to hold sequence features
        FeatureSlot featureSlot = new FeatureSlot(cgview, DIRECT_STRAND);
        for (UniqueMarker u : umList) {//for every Ring
            
            
            Feature feature = new Feature(featureSlot);
            feature.setColor(Color.RED);
            FeatureRange fr = new FeatureRange(feature, u.getStartPosition(), u.getStopPosition());
            fr.setDecoration(DECORATION_CLOCKWISE_ARROW);
        }
        
//        try {
//        CgviewFactory factory = new CgviewFactory();
//        factory.addToCgviewFromFile(cgview, "\\\\zkh\\dfs\\Gebruikers12\\KralTHJ\\Data\\Workspace\\ProefdataBRIG\\exampleData\\output_12\\scratch\\BRIGExample.fna.xml");
//        } catch (SAXException | IOException e) {
//            System.out.println("Whoops: " + e.getMessage());
//        }
        
	try {
	    //create a PNG file
            CgviewIO.writeToPNGFile(cgview, "\\\\zkh\\dfs\\Gebruikers12\\KralTHJ\\Data\\Workspace\\oefendata\\testFile_3.png");
	}
        catch (IOException e) {
	    e.printStackTrace(System.err);
        }
    }
    
}
