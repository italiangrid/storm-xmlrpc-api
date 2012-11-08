/**
 * 
 */
package it.grid.storm.srm.types;

/**
 * @author Michele Dibenedetto
 *
 */
public enum Recursion
{
    
    FULL(false),NONE(false),LIMITED(true);
    
    private final boolean specificable;
    
    private Recursion(Boolean specificable)
    {
        this.specificable = specificable;
    }
    
    public boolean isSpecificable()
    {
        return specificable;
    }
}
