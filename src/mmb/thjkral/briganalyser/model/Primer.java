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
    private int length;
    private PrimerOrientation orientation;
    private double meltTemp;
    private double gcContent;
    private boolean isUnique;

    public Primer(int markerNumber, int length, PrimerOrientation orientation, double meltTemp, double gcContent) {
        this.markerNumber = markerNumber;
        this.length = length;
        this.orientation = orientation;
        this.meltTemp = meltTemp;
        this.gcContent = gcContent;
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
        return "Primer{" + "markerNumber=" + markerNumber + ", length=" + length + ", orientation=" + orientation + ", meltTemp=" + meltTemp + ", gcContent=" + gcContent + ", isUnique=" + isUnique + '}';
    }
    
    
    
    
}
