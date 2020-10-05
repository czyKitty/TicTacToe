import java.util.*;
/**
 * General board game setting
 * Each game should have a board for playing the game
 * Each game contains a list of team who played in the game
 * The game must takes turns
 *
 * @author Zhuyun Chen
 * @date 09/29/2020
 * @note Part of Assignment 2
 */
public abstract class Game{
    public Board gameBoard; // board for game
    
    /** return team who's taking the turn */
    public abstract Team getTurn();
    
    /** return number of turn remain for current team*/
    public abstract int getRemainTurn();
    
    /** print game board */
    public void printBoard(){
        this.gameBoard.printBoard();
    }
    
    /** return board size */
    public int boardSize(){
        return this.gameBoard.getSize();
    }
    
    /** return num grids on board */
    public abstract int numGrids();
    
    /** Show Game static on screen */
    public abstract void printGameStat();
    
    /* reset the game */
    public abstract void resetGame();
    
    /** process single game from beginning to end.*/
    public abstract void gamePlay();
        
}
