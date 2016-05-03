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
 *
 * @author KralTHJ
 */
public class Gap {
    
    String ringName;
    private int startCor;
    private int stopCor;

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
