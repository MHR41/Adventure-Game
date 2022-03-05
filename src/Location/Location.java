
package Location;

import inventorygame.Player;
import java.util.Scanner;

public abstract class Location {
    
    private int id;
    private Player player;
    private String name;
    protected Scanner input = new Scanner(System.in);

    public Location(int newId,Player newPlayer,String newName) {
        
        this.id = newId;
        this.player = newPlayer;
        this.name = newName;
    }
    
    //getter and setter methods
    public Player getPlayer() {
        return player;
    }
    
    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public abstract boolean onLocation();
    
    public String getName(){
    
        return this.name;
    }
    public int getId(){
        
        return this.id;
    }
}
