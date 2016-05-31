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
 * UniqueMarker object.
 * This is a region on the reference genome where the genomes of the other species
 * don't match. 
 * 
 * @author KralTHJ
 */
public class UniqueMarker {
    
    private int number;             //Number of the UniqueMarker
    private int startPosition;      //Start position on the reference genome
    private int stopPosition;       //Stop position on the reference genome
    private int length;             //Number of nucleotides in the UniqueMarker
    private String sequenceForward; //The sequence of the forward strand
    private String sequenceReverse; //The sequence of the reverse strand

    /**
     * Constructor of the UniqueMarker Class.
     * 
     * @param number            Number of the UniqueMarker
     * @param startPosition     Start position on the reference genome
     * @param stopPosition      Stop position on the reference genome
     * @param length            Number of nucleotides in the UniqueMarker
     * @param sequenceForward   The sequence of the forward strand
     * @param sequenceReverse   The sequence of the reverse strand
     */
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
