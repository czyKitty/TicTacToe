import java.util.Arrays;

/*
 * The NxN board for board game
 * max size is 10x10, and min size if 3x3
 *
 * @author Zhuyun Chen
 * @date 09/29/2020
 * @note Part of Assignment 2
 */
public class SquareBoard extends Board {
    
    private char[] gameBoard; //array represent marks on board
    private int side;
    
    /**
     * Default Constructor
     * 3x3 board
     */
    public SquareBoard(){
        this(3);
    }
    
    /**
    * Constructor for user defined board size
    * if self defined size larger than 10x10, use 10x10 board
    * if self defined size smaller than 3x3, use 3x3 board
    * @param side, side of the board
    */
    public SquareBoard(int side){
        if (side > 10) side = 10;
        else if (side < 3) side = 3;
        this.gameBoard = new char[side*side];
        this.side = side;
    }
    
    public int getSize(){
        return this.side;
    }
    
    /** Change mark at position x,y */
    public void setGrid(int x, int y, char mark){
        this.gameBoard[this.side*x+y] = mark;
    }
        
    /**
    * Return mark on grid at position x,y */
    public char getGrid(int x, int y){
        return this.gameBoard[this.side*x+y];
    }
        
    /* reset the board */
    public void reset() {
        this.gameBoard = new char[this.side*this.side];
    }
        
    /**
    * Print current board to screen
    */
    public void printBoard() {
        System.out.println("+---+---+---+");
        for (int i = 0; i < this.side; i++) {
            System.out.print("| ");
            for (int j = 0; j < this.side; j++) {
                if (this.gameBoard[this.side*i+j] == '\u0000'){
                    // if no marker on board, print number for position
                    System.out.print(String.format("%02d", this.side*i+j) + " | ");
                }
                else System.out.print(this.gameBoard[this.side*i+j] + " | ");
            }
            System.out.println();
        }
        System.out.println("+---+---+---+");
    }
        

    /* return true if all grids are filled */
    public boolean isFull() {
        for (int i = 0; i < this.gameBoard.length; i++){
            if (this.gameBoard[i] == '\u0000') return false;
        }
        return true;
    }
        
}
