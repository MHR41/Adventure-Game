
package Location;

import Monster.Snake;
import inventorygame.Player;

public class Mine extends BattleLocation{

    //is one type of battale location
    public Mine(Player newPlayer) {
        super(6, newPlayer, "Mine", new Snake(), 5);
    }
}
