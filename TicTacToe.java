import java.util.*;

/**
 * main class for play the Tic tac toe game
 *
 * @author Zhuyun Chen
 * @date 09/29/2020
 * @note Part of Assignment 2
 */

public class TicTacToe {
    /** set up board size */
    public static int userBoard(){
        Scanner in;
        int boardSize = 3;
        while(true){
            System.out.println("Please enter size of your board:");
            in = new Scanner(System.in);
            if (in.hasNextInt()){
                boardSize = in.nextInt();
                if (boardSize < 3 || boardSize > 10) System.out.println("Your board should between 3x3 and 10x10.");
                else break;
            }
            else System.out.println("You must input a numeric size for board.");
        }
        return boardSize;
    }
    
    /** set up number of mark in a row/col/diag to win */
    public static int winCond(){
        Scanner in = new Scanner(System.in);
        int numWin = 3;
        while(true){
            System.out.println("Please enter number of marks to win:");
            if (in.hasNextInt()){
                numWin = in.nextInt();
                if (numWin<3 || numWin > 10) System.out.println("Your win condition should between 3 and 10.");
                else break;
            }
            else System.out.println("You must input a numeric number of mark to win.");
        }
        return numWin;
    }
    
    /** set up user teams */
    public static ArrayList<Team> userTeam(){
        Scanner in;
        
        ArrayList<Team> teamList = new ArrayList <Team>();
        for (int i=0;i<2;i++){
            in = new Scanner(System.in);
            String teamName;
            String teamMark;
            int teamTurn;
            // set up team names
            System.out.println("Player "+(i+1)+", please type your name:");
            teamName = in.nextLine();

            // set up team marks
            while(true){
                System.out.println("Player "+teamName+", please type your mark:");
                teamMark = in.nextLine();
                if (teamMark.length() != 1) System.out.println("Your mark can only be 1 character long.");
                else break;
            }
            // set up team turns
            while(true){
                in = new Scanner(System.in);
                System.out.println("Player "+teamName+", please enter number of turns you take:");
                if (in.hasNextInt()){
                    teamTurn = in.nextInt();
                    if (teamTurn < 1) System.out.println("You number of turn must be larger than 1.");
                    else break;
                }
                else System.out.println("Please select a number for turns to take");
            }
            teamList.add(new Team(teamName,teamMark.charAt(0),teamTurn));
        }
        return teamList;
    }

    /**
    * Main function for user to play the Tic tac toe game.
    * Player can choose to continue play another game or stop playing after each game.
    * After player stop playing, show the number of game they played and times each player win.
    * @param none.
    * @return   none.
    */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        TicTacGame game;
        
        System.out.println("Welcome to Tic Tac Toe Game.");
        System.out.println("The rule for this game is simple: \n When it's your turn, mark on the board by enter the slot number.\n You win if you have certain number of marks in same row/col/diagnal. \n The game draw when there is no empty slot on the board.");
        System.out.println("Please select:");
        System.out.println("1 Play with classic mode (3x3 board, 3 marks to win).");
        System.out.println("2 Play with self defined game mode.");
        
        int gameMode = 1;
        while(true){
            if (in.hasNextInt()){
                gameMode = in.nextInt();
                if (gameMode == 1 || gameMode == 2) break;
                else System.out.println("Please type 1 for classic mode, or 2 for self-defined mode:");
            }
            else System.out.println("Please type 1 for classic mode, or 2 for self-defined mode:");
        }
        // class game setup
        if (gameMode == 1) game = new TicTacGame();
        // user defined game setup
        else game = new TicTacGame(userTeam(), userBoard(), winCond());
        String endGameInput;
        boolean continueGame = true;
        
        //When player choose not to end the game, continue
        while (continueGame){
            // game in progress
            game.resetGame();
            game.gamePlay();
            
            //End of the game, show static
            game.printGameStat();
            
            System.out.println("Do you want to play another game? Please enter y/Y for Yes, n/N for No");
            while (true){
                endGameInput = in.nextLine();
                if (endGameInput.equals("y") || endGameInput.equals("Y")){
                    break;
                }else if (endGameInput.equals("n") || endGameInput.equals("N")) {
                    continueGame = false;
                    break;
                }else{
                    System.out.println("Invalid input, please enter y/Y if you want to continue playing, or n/N to end the game.");
                }
            }
        }
    }
}
