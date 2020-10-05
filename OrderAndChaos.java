import java.util.*;

/**
 * main class for play the Order and Chaos game
 *
 * @author Zhuyun Chen
 * @date 09/29/2020
 * @note Part of Assignment 2
 */

public class OrderAndChaos {
    /** set up user teams */
    public static ArrayList<Team> userTeam(){
        Scanner in;
        ArrayList<Team> teamList = new ArrayList <Team>();
        
        for (int i=0;i<2;i++){
            in = new Scanner(System.in);
            String teamName;
            String teamRole;
            int teamTurn;
            // Assign game role and ask player for names
            if (i == 0){
                System.out.println("Player "+(i+1)+", you will be play as Order, please type your name:");
                teamName = in.nextLine();
            }
            else{
                System.out.println("Player "+(i+1)+", you will be play as Chaos, please type your name:");
                teamName = in.nextLine();
            }
            // set up team turns
            while(true){
                in = new Scanner(System.in);
                System.out.println("Please enter number of turns you take:");
                if (in.hasNextInt()){
                    teamTurn = in.nextInt();
                    if (teamTurn < 1) System.out.println("You number of turn must be larger than 1.");
                    else break;
                }
                else System.out.println("Invalid input, your turns must be a number.");
            }
            teamList.add(new Team(teamName,' ',teamTurn));
        }
        return teamList;
    }

    /**
    * Main function for user to play the Order and chaos game.
    * Player can choose to continue play another game or stop playing after each game.
    * After player stop playing, show the number of game they played and times each player win.
    * @param none.
    * @return   none.
    */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        OnCGame game;
        
        System.out.println("Welcome to Order and Chaos Game.");
        System.out.println("The rule for this game is simple: \n You place 'O' or 'X' for by enter the slot number.\n You win as Order if you have 5 marks in same row/col/diagnal. \n You win as Chaos when there's no empty slot on the board.");

        // Order and Chaos set up
        game = new OnCGame(userTeam());
        
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
