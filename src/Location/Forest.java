
package Location;

import Monster.Vampir;
import inventorygame.Player;

public class Forest extends BattleLocation{
    
    //is one type of battle location
    public Forest(Player player){
    
        super(5, player, "Forest", new Vampir(), 3);    
    }
}
