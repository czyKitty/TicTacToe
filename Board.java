/*
 * The general board for board game
 *
 * @author Zhuyun Chen
 * @date 09/29/2020
 * @note Part of Assignment 2
 */
public abstract class Board {
    
    /** return size of the board */
    public abstract int getSize();
        
    /* reset the board */
    public abstract void reset();
        
    /** Print current board to screen */
    public abstract void printBoard();
    
    /* return true if all grids are filled */
    public abstract boolean isFull();
        
}
