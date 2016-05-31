/*
 * This code is created by:
 * Tom Kral | Bioinformatics intern
 * t.h.j.kral@umcg.nl | +31642935544
 * 
 * Commissioned by:
 * University Medical Center Groningen | Medical Microbiology
 */
package mmb.thjkral.briganalyser.model;

import mmb.thjkral.briganalyser.enums.PrimerOrientation;

/**
 * Primer object.
 * 
 * 
 * @author KralTHJ
 */
public class Primer{
    
    int markerNumber;                       //The number of the associated UM
    private String sequence;                //The sequence of the primer
    private int length;                     //Number of nucleotides in the Primer
    private int startPos;                   
    private int stopPos;
    private PrimerOrientation orientation;  //Enum to distinguish between forward of reverse
    private double meltTemp;                //Melting temperature of the Primer
    private double gcContent;               //The GC content of the Primer
    private boolean isUnique = false;       //Defines wheter a primer is unique

    /**
     * Constructor of the Primer class.
     * 
     * @param markerNumber  The number of the associated UM
     * @param sequence      The sequence of the primer
     * @param length        Number of nucleotides in the Primer
     * @param orientation   Enum to distinguish between forward of reverse
     * @param meltTemp      Melting temperature of the Primer
     * @param gcContent     Defines wheter a primer is unique
     */
    public Primer(int markerNumber, String sequence, int length, PrimerOrientation orientation, double meltTemp, double gcContent) {
        this.markerNumber = markerNumber;
        this.sequence = sequence;
        this.length = length;
        this.orientation = orientation;
        this.meltTemp = meltTemp;
        this.gcContent = gcContent;
    }

       
    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    
    
    public int getLength() {
        return length;
    }

    public int getStartPos() {
        return startPos;
    }

    public void setStartPos(int startPos) {
        this.startPos = startPos;
    }

    public int getStopPos() {
        return stopPos;
    }

    public void setStopPos(int stopPos) {
        this.stopPos = stopPos;
    }
    
    
    public void setLength(int length) {
        this.length = length;
    }

    public PrimerOrientation getOrientation() {
        return orientation;
    }

    public void setOrientation(PrimerOrientation orientation) {
        this.orientation = orientation;
    }

    public double getMeltTemp() {
        return meltTemp;
    }

    public void setMeltTemp(double meltTemp) {
        this.meltTemp = meltTemp;
    }

    public double getGcContent() {
        return gcContent;
    }

    public void setGcContent(double gcContent) {
        this.gcContent = gcContent;
    }

    public boolean isIsUnique() {
        return isUnique;
    }

    public void setIsUnique(boolean isUnique) {
        this.isUnique = isUnique;
    }

    @Override
    public String toString() {
        return "Primer{" + "markerNumber=" + markerNumber + ", sequence=" + sequence + ", length=" + length + ", orientation=" + orientation + ", meltTemp=" + meltTemp + ", gcContent=" + gcContent + ", isUnique=" + isUnique + '}';
    }

    
}
