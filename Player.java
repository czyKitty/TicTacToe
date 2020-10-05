/**
 * Class represent player who play the game
 * Each player has a name or set to "anonymous" by default
 *
 * @author Zhuyun Chen
 * @date 09/29/2020
 * @note Part of Assignment 2
 */
public class Player {
    private String name; // name of the player

    /**
     * Default Constructor
     * named as "anonymous".
     */
    public Player(){
        this.name = "anonymous";
    }
    /**
     * Constructor
     * @param symbol, sign represent the player.
     */
    public Player(String name){
        this.name = name;
    }
    
    /** Return name of the player */
    public String getName(){
        return this.name;
    }
    
}
