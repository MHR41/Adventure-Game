
package Location;

import inventorygame.Player;
/*
  the classes which exnteds Normall location class
  are normal also as it's clear from it's name
  which means no war wil be in those locations

**
*/
public abstract class NormalLocation extends Location{

    public NormalLocation(int newId,Player newPlayer, String newName) {
        super(newId,newPlayer, newName);
    }

    @Override
    public boolean onLocation() {
        return true;
    }
    
}
