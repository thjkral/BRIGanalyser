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
 *
 * @author KralTHJ
 */
public class Primer{
    
    int markerNumber;
    private String sequence;
    private int length;
    private PrimerOrientation orientation;
    private double meltTemp;
    private double gcContent;
    private boolean isUnique = false;

    public Primer(int markerNumber, String sequence, int length, PrimerOrientation orientation) {
        this.markerNumber = markerNumber;
        this.sequence = sequence;
        this.length = length;
        this.orientation = orientation;
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
