import java.util.*;
/**
 * Game with specific settings for Order and Chaos
 * Inherited from TicTacGame
 * Game rules:
 * 1. There should be 2 and only 2 teams
 * 2. Each team have 1 player and they can place either O or X on board
 * 3. Board must be 6X6 size
 * 3. Team win if there is 5 tokens from same team in a row/col/diagnal
 *
 * @author Zhuyun Chen
 * @date 09/29/2020
 * @note Part of Assignment 2
 */
public class OnCGame extends TicTacGame{
    
    private int orderIndex; // index of the team that play as order
    /**
    * Default constructor
    * Mark O for order and X for chaos, each takes 1 turn
    * Default game setting
    */
    public OnCGame(){
        super(new ArrayList<Team>(),6,5);
        this.teamList.add(new Team("Order",'O',1));
        this.teamList.add(new Team("Chaos",'X',1));
        this.orderIndex = 0;
    }
    
    /**
     * User define Constructor
     * Allow users to use self-defined team
     */
    public OnCGame(ArrayList<Team> teamList){
        super(teamList,6,5);
        this.orderIndex = 0;
    }
    
    /**
     * @Override
     * Chao win in TicTacToe draw condition (when board is full)
     */
    public boolean checkDraw(){
        if (this.gameBoard.isFull()){
            // chao win
            this.teamList.get(this.orderIndex^1).win();
            return true;
        }
        return false;
    }
    
    /**
     * @Override
     * Order win in any TicTacToe win condition (when have 5 mark in a rol/col/diag)
     */
    public boolean checkWin(){
        // check if 5 in a row
        char winTeam = this.checkRow();
        if (winTeam == '\u0000'){
            // if no 5 in row, check if 5 in a col
            winTeam = this.checkCol();
            if (winTeam == '\u0000'){
                // check if 5 in a diag
                winTeam = this.checkDiag();
                if (winTeam == '\u0000'){
                     return false;
                }
            }
        }
        // Order win
        this.teamList.get(this.orderIndex).win();
        return true;
    }
    
    /* reset the board and allow user to swich sequence */
    public void resetGame() {
        this.gameBoard.reset();
        Scanner in = new Scanner(System.in);
        String switchT;
        System.out.println("Player "+this.teamList.get(this.turn).getTeamName()+" is play as order.");
        System.out.println("Do you want to switch role? (y/n)");
        while (true){
            switchT = in.nextLine();
            if (switchT.equals("y") || switchT.equals("Y")){
                this.turn ^= 1; // switch between team 1 and 0, will not work for multi-team game
                this.orderIndex ^= 1; // swith the order team index
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
            if(this.turn == this.orderIndex){
                System.out.println("Player " + curTeam.getTeamName() + ", you are play as Order, you have "+ curTeam.getTurn()+" turns remain.");
            }else{
                System.out.println("Player " + curTeam.getTeamName() + ", you are play as Chaos, you have "+ curTeam.getTurn()+" turns remain.");
            }

            String mark;
            // Ask user to select a mark O/X
            System.out.println("Please select your mark (O/X): ");
            while (true){
                in = new Scanner(System.in);
                mark = in.nextLine();
                if (mark.equals("O") || mark.equals("X")) break;
                else System.out.println("Invalid input, please enter O or X as your mark):");
                
            }
            
            int markPos;
            // Ask user to place mark on a valid position
            System.out.println("Please enter your move: ");
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
            this.setGrid(markPos,mark.charAt(0));
            // check if Chaos win
            if (this.checkDraw()){
                System.out.println("Congratulations player " + this.getTeams().get(this.orderIndex^1).getTeamName() + ", you win as Chaos!");
                break;
            }
            // check if Order win
            if (this.checkWin()){
                System.out.println("Congratulations player " + this.getTeams().get(this.orderIndex).getTeamName() + ", you win as Order!");
                break;
            }
            this.nextTurn();
        }
    }
}
