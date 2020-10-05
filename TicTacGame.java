import java.util.*;
/**
 * Game with specific settings for tic tac toe
 * Game rules:
 * 1. There should be 2 and only 2 teams with each team assigned with 1 token (O and X by default)
 * 2. Each team have 1 player by default and they are named as X and O by default
 * 3. Team win if there is n tokens from same team in a row/col/diagnal
 *
 * @author Zhuyun Chen
 * @date 09/29/2020
 * @note Part of Assignment 2
 */
public class TicTacGame extends Game {
    public ArrayList<Team> teamList; // list of teams
    public int turn; // index of team that's taking the turn
    public SquareBoard gameBoard; // board for game
    public int winNum; // number of token required in a row/col/diagnal to win
    
    /**
    * Default constructor
    * Two teams are marked by O and X, each takes 1 turn
    * Default game setting
    */
    public TicTacGame(){
        this(new ArrayList<Team>(), 3, 3);
        this.teamList.add(new Team("O",'O',1));
        this.teamList.add(new Team("X",'X',1));
    }
    
    /**
     * Full user define Constructor
     * @param team1, team2, Team team 1 and team 2
     * @param boardSize, size of the board
     * @param winNum, number of mark in same row/col/diag to win
     */
    public TicTacGame(ArrayList<Team> teamList, int boardSize, int winNum){
        this.teamList = teamList;
        this.gameBoard = new SquareBoard(boardSize);
        this.winNum = winNum;
        this.turn = 0;
    }
    
    /** return team who's taking the turn */
    public Team getTurn(){
        return this.teamList.get(this.turn);
    }
    
    /** return number of turn remain for current team*/
    public int getRemainTurn(){
        return this.teamList.get(this.turn).getTurn();
    }
    
    /** return num grids on board */
    public int numGrids(){
        int side = this.boardSize();
        return side*side;
    }
    
    /** return mark on grids with given position */
    public char getGrid(int pos){
        int side = this.boardSize();
        int x = pos/side;
        int y = pos - x*side;
        return this.gameBoard.getGrid(x, y);
    }
    
    /** Set mark on grids with given position */
    public void setGrid(int pos, char mark){
        int side = this.boardSize();
        int x = pos/side;
        int y = pos - x*side;
        this.gameBoard.setGrid(x, y, mark);
    }
    
    /**
     * Next turn of the game
     * if there's turn left for current team, count and let them continue
     * otherwise, switch to next team and reset turn for team who end the turn
     */
    public void nextTurn(){
        Team currTeam = this.teamList.get(this.turn);
        currTeam.subTurn();
        int remainTurn = currTeam.getTurn();
        // when no turns left, next team
        if (remainTurn == 0){
            currTeam.resetTurn();
            if (this.turn < this.teamList.size()-1) this.turn += 1;
            else this.turn = 0;
            return;
        }
    }
    
    /** return teams */
    public ArrayList<Team> getTeams(){
        return this.teamList;
    }
    
    /** Game static */
    public void printGameStat(){
        System.out.println("+++++ Game Stat +++++");
        for (int i=0; i<this.teamList.size();i++){
            Team curTeam = this.teamList.get(i);
            System.out.println("Player "+ curTeam.getTeamName());
            System.out.println("Win: " + curTeam.getWin()+" Draw: " + curTeam.getDraw());
        }
    }
    
    /** check if the game draw, and add draw count to each team */
    public boolean checkDraw(){
        if (this.gameBoard.isFull()){
            for(int i=0; i<this.teamList.size();i++){
                this.teamList.get(i).draw();
            }
            return true;
        }
        return false;
    }
    
    /**
     * check if any team win
     * Return true if any team win (when have n mark in a rol/col/diag)
     * change team win and lose count
     */
    public boolean checkWin(){
        // check if anyone win in a row
        char winTeam = this.checkRow();
        if (winTeam == '\u0000'){
            // if no one win in row, check if anyone win in a col
            winTeam = this.checkCol();
            if (winTeam == '\u0000'){
                // check if anyone win in a diag (or return null if no winner)
                winTeam = this.checkDiag();
                if (winTeam == '\u0000') return false;
            }
        }
       
        // current team win
        this.teamList.get(this.turn).win();
        return true;
    }
    
    /**
     * Check if n marks in same row.
     * @return mark that meet win condition, or null if no match.
     **/
    public char checkRow() {
        int size = this.gameBoard.getSize();
        char mark = '\u0000'; // mark for token
        int count = 1; // count for matchs
        // look through every row on board
        for (int i=0; i<size; i++){
            // look through col 0 to width-number need to be in row
            for (int j=0;j<=size-this.winNum;j++){
                mark = this.gameBoard.getGrid(i,j); // mark at given position
                if (mark == '\u0000') break;
                count = 1;
                // look for next nth marks in row and count for matches
                for (int k=1; k<this.winNum; k++){
                    if (this.gameBoard.getGrid(i,j+k) == mark){
                        count += 1;
                        if (count == this.winNum) return mark;
                    }
                    else break;
                }
            }
        }
        return '\u0000';
    }
    
    /**
     * Check if n marks in same col.
     * @return mark that meet win condition, or null if no match.
     **/
    public char checkCol() {
        int size = this.gameBoard.getSize();
        char mark = '\u0000'; // mark for token
        int count = 1; // count for matchs
        
        // look through every col on board
        for (int i=0; i<size; i++){
            // look through row 0 to width-number need to be in col
            for (int j=0;j<=size-this.winNum;j++){
                mark = this.gameBoard.getGrid(j,i); // mark at given position
                if (mark == '\u0000') break;
                count = 1;
                // look for next nth marks in col and count for matches
                for (int k=1; k<this.winNum; k++){
                    if (this.gameBoard.getGrid(j+k,i) == mark){
                        count += 1;
                        if (count == this.winNum) return mark;
                    }
                    else break;
                }
                
            }
        }
        return '\u0000';
    }
    
    
    /**
     * Check if n marks in same diag.
     * @return mark that meet win condition, or null if no match.
     **/
    public char checkDiag() {
        int side = this.gameBoard.getSize();
        char mark = '\u0000'; // mark for token
        int count = 1; // count for matchs
        // check rightward diag
        for (int i=0; i<= (side-this.winNum);i++){
            for (int j=i; j<side;j++){
                mark = this.gameBoard.getGrid(i,j); // mark at given position
                // if that position is empty, skip
                if (mark == '\u0000') break;
                count = 1;
                for (int k=1;k<this.winNum;k++){
                    if (this.gameBoard.getGrid(i+k,j+k) == mark){
                        count += 1;
                        if (count == this.winNum) return mark;
                    }
                    else break;
                }
            }
        }

        // check leftward diag
        for (int i=0; i<= (side-this.winNum);i++){
            // check rightward diag
            for (int j=(side-1); j>=(this.winNum-1);j--){
                mark = this.gameBoard.getGrid(i,j); // mark at given position
                // if that position is empty, skip
                if (mark == '\u0000') break;
                count = 1;
                for (int k=1;k<this.winNum;k++){
                    if (this.gameBoard.getGrid(i+k,j-k) == mark){
                        count += 1;
                        if (count == this.winNum) return mark;
                    }
                    else break;
                }
            }
        }
        return '\u0000';
    }
    
    /* reset the board and allow user to swich sequence */
    public void resetGame() {
        this.gameBoard.reset();
        Scanner in = new Scanner(System.in);
        String switchT;
        System.out.println("Player "+this.teamList.get(this.turn).getTeamName()+" move first.");
        System.out.println("Do you want to switch playing sequence? (y/n)");
        while (true){
            switchT = in.nextLine();
            if (switchT.equals("y") || switchT.equals("Y")){
                this.turn ^= 1; // switch between team 1 and 0, will not work for multi-team game
                break;
            }else if (switchT.equals("n") || switchT.equals("N")) {
                break;
            }else{
                System.out.println("Invalid input, please enter y/Y if you want to switch playing sequence, or n/N if not.");
            }
        }
    }
    
    /**
     * Function to process single game from beginning to end.
     */
    public void gamePlay(){
        Scanner in = new Scanner(System.in);
        //If no one win yet, continue the game
        while (true) {
            Team curTeam = this.getTeams().get(this.turn);
            this.printBoard();
            System.out.println("Player " + curTeam.getTeamName() + ", you have "+ curTeam.getTurn()+" turns remain, enter your move: ");
            int markPos;
            int numInput;
            
            // Ask user to provide valid position
            while (true){
                if (in.hasNextInt()){
                    markPos = in.nextInt();
                    if (markPos < 0 || markPos > this.numGrids()) System.out.println("Invalid input, please re-enter your move:");
                    else if (this.getGrid(markPos) != '\u0000') System.out.println("Slot already taken, please re-enter your move:");
                    else break;
                }
                else System.out.println("Invalid input, please enter a position on board:");
                in = new Scanner(System.in);
            }
            // place mark on the given position
            this.setGrid(markPos,curTeam.getMark());
            // check if win
            if (this.checkWin()){
                System.out.println("Congratulations player " + curTeam.getTeamName() + ", you win!");
                break;
            }
            // check if draw
            if (this.checkDraw()){
                System.out.println("It's a draw!");
                break;
            }
            this.nextTurn();
        }
    }
}
