
package Inventory;

import Weapon.Armor;
import Weapon.Weapon;

public class Inventory {
    
    private Weapon weapon;
    private Armor armor;
    boolean water;
    boolean firewood;
    boolean food;
 
    
    public Inventory(){
        //When Inventory's object is made for the first time in player class
        //the player doesn't have anything 
        this.weapon = new Weapon("Punch", 0, 0, 0);
        this.armor = new Armor(0, "Normal", 0, 0);
        // water, firewood and food is used later for giving the special prize of successfully finished tasks in the related locations.
        this.water = false;
        this.firewood = false;
        this.food = false;
    }

    // getter and setter methods
    public boolean isWater() {
        return water;
    }

    public void setWater(boolean water) {
        this.water = water;
    }

    public boolean isFirewood() {
        return firewood;
    }

    public void setFirewood(boolean firewood) {
        this.firewood = firewood;
    }

    public boolean isFood() {
        return food;
    }

    public void setFood(boolean food) {
        this.food = food;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public Armor getArmor() {
        return armor;
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }
    
    
}
