/*
 * This code is created by:
 * Tom Kral | Bioinformatics intern
 * t.h.j.kral@umcg.nl | +31642935544
 * 
 * Commissioned by:
 * University Medical Center Groningen | Medical Microbiology
 */
package mmb.thjkral.briganalyser.model;

import mmb.thjkral.briganalyser.enums.BLASTExecution;

/**
 * Class for the execution of a BLAST search.
 * Primers and probes must be unique in order to be usefull in qPCR. This means
 * they cannot bind to one another. Binding elsewhere on the reference genome or
 * on another genome is also not allowed.
 * 
 * @author KralTHJ
 */
public class ExecuteBLAST {
    
    public void start(BLASTExecution execution) {
        
        switch (execution) {
            case LOCAL:
                executeLocal();
                break;
            case EXTERNAL:
                executeExternal();
                break;
            default:
                throw new AssertionError();
        }
        
    }//start()
    
    private void executeLocal() {
        
    }//executeLocal()
    
    private void executeExternal() {
        
    }//executeExternal()
    
    /**
     * Makes a database for BLAST using 'blastdb'.
     */
    private void makeDatabase() {
        
    }//makeDatabase()
    
}//class()
