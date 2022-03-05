
package Location;

import Monster.Bear;
import Monster.Monster;
import inventorygame.Player;

public class River extends BattleLocation{
    
    //is one type of battle location
    public River(Player player) {
        
        super(4, player, "River", new Bear(), 3);
        
    }
   
}
