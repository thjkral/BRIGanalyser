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
public class UniqueMarker {
    
    private int number;
    private int startPosition;
    private int stopPosition;
    private int length;
    private String sequenceForward;
    private String sequenceReverse;

    public UniqueMarker(int number, int startPosition, int stopPosition, int length, String sequenceForward, String sequenceReverse) {
        this.number = number;
        this.startPosition = startPosition;
        this.stopPosition = stopPosition;
        this.length = length;
        this.sequenceForward = sequenceForward;
        this.sequenceReverse = sequenceReverse;
    }


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getStopPosition() {
        return stopPosition;
    }

    public void setStopPosition(int stopPosition) {
        this.stopPosition = stopPosition;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getSequenceForward() {
        return sequenceForward;
    }

    public void setSequenceForward(String sequenceForward) {
        this.sequenceForward = sequenceForward;
    }

    public String getSequenceReverse() {
        return sequenceReverse;
    }

    public void setSequenceReverse(String sequenceReverse) {
        this.sequenceReverse = sequenceReverse;
    }

    @Override
    public String toString() {
        return "UniqueMarker{" + "number=" + number + ", startPosition=" + startPosition + ", stopPosition=" + stopPosition + ", length=" + length + ", sequenceForward=" + sequenceForward + ", sequenceReverse=" + sequenceReverse + '}';
    }
    
    
    

}
