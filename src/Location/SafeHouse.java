
package Location;

import inventorygame.Player;

/*
  his calss is used for two aims
  1-checking if the player is alive and finished all his/her tasks in the related locations
  2-filling the blood of player to it's original value

**
*/
public class SafeHouse extends NormalLocation{
    
    public SafeHouse(Player player) {
        super(1,player,"Safe House");
    }
    
    @Override
    public boolean onLocation(){
    
        //Checking if the player have finished all his/her tasks in the related locations or not if finished
        //then the game is finished!!! and return false;
        if(this.getPlayer().getInventory().isFirewood() &&  this.getPlayer().getInventory().isFood() &&  this.getPlayer().getInventory().isWater()){

            System.out.println("-----------------$$$$$$$$$$$$$$$$$$$$$$$$$$$$$---VIKTORY---$$$$$$$$$$$$$$$$$$$$$$$$$$$$$-----------------");
            System.out.println("                            "+this.getPlayer().getName()+" ,You have successfully finished all your tasks.\n"
                             + "                            Now go and rest till your next tasks.)");
            return false;
        }
        
        // filling the player's blood to it's original value
        System.out.println(" "+this.getPlayer().getName()+", You are in safe.");
        System.out.println(" Your amount of blood is completed...");
        this.getPlayer().setHealth(this.getPlayer().getOriginalHealth());
        System.out.println(" Your health right now is :"+this.getPlayer().getHealth());
        
        // return true if the player have not finished all his/her tasks and if he/she have not died yet
        return true;
    }
}
