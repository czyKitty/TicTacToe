import java.util.ArrayList;

/**
 * Class represent team who play the game
 * Each team contains a list of players
 * Each team has a name (set to "anonymous" if no team name provided)
 * Track team stats (time of win/lose/draw)
 *
 * @author Zhuyun Chen
 * @date 09/29/2020
 * @note Part of Assignment 2
 */
public class Team {
    private ArrayList<Player> playerList; // name of the players in team
    private String teamName; // Name of the team
    private char mark; // Mark represent the team
    private int numTurn; // Number of turn the team would play
    private int remainTurn; // Number of turn the team still have
    private int winCount; // Count how many time the team win
    private int loseCount; // Count how many time the team lose
    private int drawCount; // Count how many time the team draw

    /**
     * Default Constructor
     * Empty mark
     */
    public Team(){
        this(" ",' ',1);
    }
    
    /**
     * Constructor
     * @param tn, team name
     * @param mark, mark used by team
     * @param numTurn, how many time the team would play for each round
     */
    public Team(String tn, char mark, int numTurn){
        this.teamName = tn;
        this.playerList = new ArrayList<Player>();
        this.winCount = this.loseCount = this.drawCount = 0;
        this.mark = mark;
        this.numTurn = numTurn;
        this.remainTurn = numTurn;
    }

    /** return name of the team */
    public String getTeamName(){
        return this.teamName;
    }
    
    /** return mark used by team */
    public char getMark(){
        return this.mark;
    }
    
    /** set mark used by the team */
    public void setMark(char mark){
        this.mark = mark;
    }
    
    /** print who's in team */
    public void showTeam(){
        if (this.playerList.size() == 0) System.out.println("No player assigned to team");
        else{
            for (int i=0; i<this.playerList.size();i++){
                System.out.println("PlayerID "+i+" "+playerList.get(i).getName()+"/n");
            }
        }
    }
    
    /** get player in team */
    public ArrayList<Player> getPlayer(){
        return this.playerList;
    }
    
    /** get turns remain */
    public int getTurn(){
        return this.remainTurn;
    }

    /** subtract turns when the team take 1 turn */
    public void subTurn(){
        this.remainTurn -= 1;
    }
    
    /** reset turns */
    public void resetTurn(){
        this.remainTurn = this.numTurn;
    }
    
    /** get how many time the team win */
    public int getWin(){
        return this.winCount;
    }
    
    /** add win count */
    public void win(){
        this.winCount += 1;
    }
    
    /** get how many time the team lose */
    public int getLose(){
        return this.loseCount;
    }
    
    /** add lose count */
    public void lose(){
        this.loseCount += 1;
    }
    
    /** get how many time the team draw */
    public int getDraw(){
        return this.drawCount;
    }
    
    /** add draw count */
    public void draw(){
        this.drawCount += 1;
    }
}
