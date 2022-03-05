
package Location;

import Monster.Monster;
import Monster.Zombi;
import inventorygame.Player;

public class Cave extends BattleLocation{

    //is one type of battle location
    public Cave(Player newPlayer) {
        super(3 , newPlayer, "Cave", new Zombi(), 3);
    }
}
