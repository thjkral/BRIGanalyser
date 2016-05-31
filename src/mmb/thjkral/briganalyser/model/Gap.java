/*
 * This code is created by:
 * Tom Kral | Bioinformatics intern
 * t.h.j.kral@umcg.nl | +31642935544
 * 
 * Commissioned by:
 * University Medical Center Groningen | Medical Microbiology
 */
package mmb.thjkral.briganalyser.model;

/**
 * The rings in BRIG output are almost never continuous, the space in between is
 * defined as a Gap object.
 * 
 * @author KralTHJ
 */
public class Gap {
    
    String ringName;
    private int startCor;
    private int stopCor;

    /**
     * Constructor for Gap object.
     * 
     * @param ringName  Name of the Ring it belongs to
     * @param startCor  Start position in Ring
     * @param stopCor   Stop position in Ring
     */
    public Gap(String ringName, int startCor, int stopCor) {
        this.ringName = ringName;
        this.startCor = startCor;
        this.stopCor = stopCor;
    }

    
    public int getStartCor() {
        return startCor;
    }

    public void setStartCor(int startCor) {
        this.startCor = startCor;
    }

    public int getStopCor() {
        return stopCor;
    }

    public void setStopCor(int stopCor) {
        this.stopCor = stopCor;
    }

    @Override
    public String toString() {
        return "Gap{" + "ringName=" + ringName + ", startCor=" + startCor + ", stopCor=" + stopCor + '}';
    }

      
    
    
}
