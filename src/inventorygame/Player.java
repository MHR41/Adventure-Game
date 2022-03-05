
package inventorygame;

import GameCharacter.Archer;
import GameCharacter.GameChar;
import GameCharacter.Samurai;
import GameCharacter.knight;
import Inventory.Inventory;
import java.util.Scanner;

public class Player {
    
   private String name;
   private String charName;
   private int damage;
   private int health;
   private int money;
   private Inventory inventory;
   //originalHealth later, is used for filling player's health to max in SafeHouse Location
   private int originalHealth;        
   private Scanner input = new Scanner(System.in);
   
   public Player(String newName){
   
       this.name = newName;
       //inventory plays the role of a package for player
       //it's used for collecting player's prize, weapon, armor,...
       this.inventory = new Inventory();

   }

   //Game characters which one of them will be selected by user
   public void selectChar(){
   
       GameChar[] charList = {new Samurai(),new Archer(),new knight()};
       
       System.out.println("Characters:");
       System.out.println("=========================================================================");
       for(GameChar list:charList){
       
           System.out.println("Id:"+list.getId()+"\t"+
                   "Character: "+list.getName()+"\t"+
                   "Damage: "+list.getDamage()+"\t"+
                   "Health: "+list.getHealth()+"\t"+
                   "Money: "+list.getMoney());
       }
       System.out.println("=========================================================================");
       
       System.out.print("Please Type Your Favorite Character ID: ");
       int selectedChar = input.nextInt();
       
       switch(selectedChar){
       
           case 1:
               initPlayer(new Samurai());
               break;
           case 2:
               initPlayer(new Archer());
               break;
           case 3:
               initPlayer(new knight());
               break;
           default:
               initPlayer(new Samurai());
       }
   }
   
   // shows Game character's info which selected by user
   public  void printPlayerInfo(){
       System.out.println("##########################");
       System.out.println("Your character's properties:");
       System.out.println("Character Type:"+this.getCharName());
       System.out.println("Weapn:"+this.getInventory().getWeapon().getName());
       System.out.println("Weapon's Damage: "+this.getInventory().getWeapon().getDamage());
       System.out.println("Armor:"+this.getInventory().getArmor().getName());
       System.out.println("Damage: "+this.getDamage());
       System.out.println("Health: "+this.getHealth());
       System.out.println("Money: "+this.getMoney());
       System.out.println("##########################");

   }
    //Usage of polymorphism to initialize the Game character
    public void initPlayer(GameChar gameChar){
        
        this.setCharName(gameChar.getName());
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setOriginalHealth(gameChar.getHealth());
    }
   
    
    // getter and setter methos
    
    public int getDamage() {
        return damage;
    }

    public void setDamage(int newDamage) {
        this.damage = newDamage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName) {
        this.charName = charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public int getOriginalHealth() {
        return originalHealth;
    }
     
    public int setOriginalHealth(int originalHealth){
    
        this.originalHealth = originalHealth;
        return this.originalHealth;
    }
}
