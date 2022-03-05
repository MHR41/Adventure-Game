package Location;

import Monster.Monster;
import Weapon.Armor;
import Weapon.Weapon;
import inventorygame.Player;
import java.util.Random;
import javax.sound.sampled.FloatControl;

public abstract class BattleLocation extends Location {

    private Monster monster;
    int maxMonster;
    private Random random = new Random();
    
    public BattleLocation(int newId, Player newPlayer, String newName, Monster newMonster, int newMaxMonster) {

        super(newId, newPlayer, newName);
        this.monster = newMonster;
        //the numbers of monsters in each location is determined randomly bitween specified interval
        this.maxMonster = this.maxMonsterRandom(newMaxMonster);
    }

    @Override
    public boolean onLocation() {

        System.out.println("---------------------------------------------------------");
        System.out.println("Now you are in <" + this.getName() + "> location.");
        System.out.println("Be careful, " + this.getMaxMonster() + " " + this.getMonster().getName() + " live in this neighborhood...");
        System.out.println("Fight(F),Escape(E)?");
        String selectedCase = input.nextLine();
        selectedCase = selectedCase.toUpperCase();
        if (selectedCase.equals("F")) {
            
            //fighting in the area is start from fight funtion
            int result = fight(maxMonster);
            
            // player was killed
            if (result == -1) {
           
                System.out.println(this.getPlayer().getName()+", You were killed...");
                return false;
            }
            //player killed all the monsters in the related area
            else if (result == this.getMaxMonster()) {

                System.out.println("You successfully killed all the " + this.getMonster().getName() + " in this " + this.getName()+" location");
                System.out.println("Your new balance is: " + this.getPlayer().getMoney());
                System.out.println("Your Health is: " + this.getPlayer().getHealth());

            }
            //player did not kill any monster in the related area
            else if (result == 0) {

                System.out.println("You're leaving " + this.getName() + " location without earning any money");
            
            }
            //the player killed a number of monster in the area but not all of monsters
            else {

                System.out.println("You killed " + result + " " + this.getMonster().getName());
                System.out.println("Your new balance is: " + this.getPlayer().getMoney());
                System.out.println("Your Health is: " + this.getPlayer().getHealth());
            }
        }
        return true;
    }

    public int fight(int maxMonster) {

        // result is number of killed monstors
        // if result = -1 means player was killed
        int result = 0;
        // if hit is true means player wants to hit
        // if hit is false means player want to escape 
        boolean hit;
        String selectedCase = "";
        
        for (int i = 1; i <= maxMonster; i++) {
            // we dont create object of monster's sub classes here, that is why after killing one monster
            // we should set monster's health to it's original value manually
            this.getMonster().setHealth(this.getMonster().getOriginalHealth());
           
            // In 'Mine' location, every monster has a special damgage bitween [3,6] randomly
            // since we don't create any object of related monster class, we have to set it's 
            // damage for every monster randomly here
            if(this.getName().equals("Mine")){
            
               this.getMonster().setDamage(random.nextInt(6)+3);
            }
            
            playerInfo();
            monsterInfo(i);
            System.out.println();
            
            //fighting with every monster will continue until both side's health is greater than zero
            while (this.getPlayer().getHealth() > 0 && this.getMonster().getHealth() > 0) {
                
                // who will hit first is determined randomly each time 
                int randomStart = random.nextInt(2) + 1;
                
                // if randomStart is 2, the player will hit first
                if (randomStart == 2) {
                    
                    hit = playerHitFirst();
                    if(hit == false){
                        // E means Escape
                        selectedCase = "E";
                        break;
                    }
                }
                // if the randomStart is 1, the monster will hit first
                else if(randomStart == 1){
                
                    hit = monsterHitFirst();
                    if(hit == false){
                        // E means Escape
                        selectedCase = "E";
                        break;
                    }
                } 
            }
            // checking if the player is alive or not
            if (this.getPlayer().getHealth() == 0) {
                
                return -1;
                
            // checking if player have killed all the monstres or not, 
            // for adding the prize of killing every monster
            } else if (!selectedCase.equals("E")) {
                
                // we have to check the location for adding money to player's balance for killing monsters
                // becuase in 'Mine' location the prize is unexptected and it's format is String and it can not
                // be converted to int
                if(!this.getName().equals("Mine")){
                
                    // we need to convert the format prize from string to integer untill we can add it to player's balance
                    String prize = this.getMonster().getPrize();
                    int prizeInt = this.getPlayer().getMoney()+ Integer.parseInt(prize);
                    this.getPlayer().setMoney(prizeInt);
                    result++;
                }
                // Giving the unexpected prize of Mine location to the player for killing every monster
                else if(this.getName().equals("Mine")){
                
                    // adding the number of killed monsters
                    result++;
                    
                    // Player will recieve randomly Weapon or Archor or Money or Nothing 
                    // weapon -> %15 , Archor -> %15 , Money -> %25 , Nothing -> %45
                    int randomPrize = random.nextInt(100)+1;
                    
                    if(randomPrize <= 15){
                        
                        //recieving randomly one weapon for killing the monster
                        int randomWeapon = random.nextInt(100)+1;
                        
                        if(randomWeapon <= 20){
                        
                           this.getPlayer().getInventory().setWeapon(new Weapon("Pistol", 1, 2, 5));
                           
                        }
                        if(randomWeapon >20 && randomWeapon <= 50){
                        
                            this.getPlayer().getInventory().setWeapon(new Weapon("Sword", 2, 3, 35));
                           
                        }
                        if(randomWeapon >50 && randomWeapon <=100){
                        
                            this.getPlayer().getInventory().setWeapon(new Weapon("Rifle", 3, 7, 45));
                            
                        }
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println(this.getPlayer().getName()+", you got one Weapon '"+this.getPlayer().getInventory().getWeapon().getName()+"' as unexpected prize for killing one snake.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    else if(randomPrize > 15 && randomPrize <= 30){
                        
                        //recieving randomly one Archor for killing the monster
                        int randomArchor = random.nextInt(100)+1;
                        
                        if(randomArchor <= 20){
                        
                            this.getPlayer().getInventory().setArmor(new Armor(3, "heavy", 5, 40));
                            
                        }
                        else if(randomArchor >20 && randomArchor <= 50){
                        
                            this.getPlayer().getInventory().setArmor(new Armor(2, "middle", 3, 25));
                            
                        }
                        else if(randomArchor > 50 && randomArchor <= 100){
                        
                            this.getPlayer().getInventory().setArmor(new Armor(1, "light", 1, 15));
                            
                        }
                        
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println(this.getPlayer().getName()+", you got one Armor '"+this.getPlayer().getInventory().getArmor().getName()+"' as unexpected prize for killing one snake.");
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                    }
                    else if(randomPrize > 30 && randomPrize <= 55){
                        
                        //recieving randomly Money for killing the monster
                        int randomMoney = random.nextInt(100)+1;
                        int balace = this.getPlayer().getMoney();
                        
                        if(randomMoney <= 20){
                        
                            this.getPlayer().setMoney(balace+10);
                        }
                        else if(randomMoney > 20 && randomMoney <= 50){
                        
                            this.getPlayer().setMoney(balace+5);
                        }
                        else if(randomMoney > 50 && randomMoney <= 100){
                        
                            this.getPlayer().setMoney(balace+1);
                        }
                        
                        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                        System.out.printf(this.getPlayer().getName()+", you got "+(this.getPlayer().getMoney()-balace)+"$ as unexpected prize for killing one snake\n");
                        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                    }
                    else if(randomPrize > 55 && randomPrize <=100){
                    
                        // the player wont recieve any thing for kinlling the monster
                        // because the randomPrize is bitween (55,100]
                    }
                }
            }
        }
        
        // checking if the player have killed all the animals or not
        if(result == maxMonster){
            
            //Giving the special prize to player for killing all of the monsters of the related area
            if (this.getName().equals("River")) {

                this.getPlayer().getInventory().setFirewood(true);
                System.out.println("--------------------------------------------------------------------------------");
                System.out.println("|You have recieved your special prize which is 'FireWood' from River location. |");
                System.out.println("--------------------------------------------------------------------------------");

            } else if (this.getName().equals("Cave")) {

                this.getPlayer().getInventory().setFood(true);
                System.out.println("--------------------------------------------------------------------------------");
                System.out.println("|You have recieved your special prize which is 'Wood' from Cave location.       |");
                System.out.println("--------------------------------------------------------------------------------");

            } else if (this.getName().equals("Forest")) {

                this.getPlayer().getInventory().setWater(true);
                System.out.println("-------------------------------------------------------------------------------");
                System.out.println("|You have recieved your special prize which is 'Water' from Forest location.   |");
                System.out.println("--------------------------------------------------------------------------------");
            }
            
        }
        return result;
    }

    // this functions is used to start hitting by player
    // and returns false if the player wants to esacape
    public boolean playerHitFirst() {
        
        String selectedCase = "";
        
        //player hit first
        if (this.getPlayer().getHealth()> 0) {
            
            System.out.println("Hit(H) or Escape(E)");
            // warning if the player's blood is getting low
            if(this.getPlayer().getHealth()<= 5 ){
            
                System.out.println("Your blood is getting low, you can escape and got to safe house for filling your blood!");
            }
            selectedCase = input.nextLine().toUpperCase();

            if (selectedCase.equals("E")) {

                return false;

            } else if (selectedCase.equals("H")) {

                System.out.println("You hitted first the " + this.getMonster().getName());
                // adding the damage of weapon to damage of gamaCharacter while hittin the monster
                int monsterNewHealth = this.getMonster().getHealth() - (this.getPlayer().getDamage()+ this.getPlayer().getInventory().getWeapon().getDamage());
                if(monsterNewHealth < 0){
                
                    monsterNewHealth = 0;
                }
                this.getMonster().setHealth(monsterNewHealth);
            }
        }
        
        // monster hit after player
        if (this.getMonster().getHealth() > 0) {

            System.out.println(this.getMonster().getName() + " hitted you");
            // calculating the armor's effect while it blocks the force of stroke which comes from the monster 
            int armorEfect = this.getPlayer().getInventory().getArmor().getBlock()-this.getMonster().getDamage();
            // controlling if the armor blocks completely the force of stroke 
            // if it does not blok, then subtracting the rest of that force from player's health
            if(armorEfect < 0){
            
                int newHealth = this.getPlayer().getHealth() + armorEfect;
                if (newHealth < 0) {

                    newHealth = 0;
                }
                this.getPlayer().setHealth(newHealth);
            }
        }  
        afterHit();
        return true;
    }

    // this function is used to start hitting by monster
    // and return false if the player wants to esacape
    public boolean monsterHitFirst() {

        String selectedCase = "";
        
        // monster hit first
        if(this.getMonster().getHealth() > 0) {

            System.out.println(this.getMonster().getName() + " hitted you first");
            // calculating the armor's effect while it blocks the force of stroke which comes from the monster 
            int armorEfect = this.getPlayer().getInventory().getArmor().getBlock()-this.getMonster().getDamage();
            // controlling if the armor blocks completely the force of stroke 
            // if it does not blok, then subtracting the rest of that force from player's health
            if(armorEfect < 0){
            
                int newHealth = this.getPlayer().getHealth() + armorEfect;
                if (newHealth < 0) {

                    newHealth = 0;
                }
                this.getPlayer().setHealth(newHealth);
            }
        }
        
        // player hit after monster
        if(this.getPlayer().getHealth()> 0) {
                        
            System.out.println("Hit(H) or Escape(E)");
            // warning if the player's blood is getting low
            if(this.getPlayer().getHealth()<= 5 ){
            
                System.out.println("Your blood is getting low, you can escape and got to safe house for filling your blood!");
            }

            selectedCase = input.nextLine().toUpperCase();
            
            // E means escape
            if (selectedCase.equals("E")) {

                return false;
            
            // H means hit
            } else if(selectedCase.equals("H")) {

                System.out.println("You hitted the " + this.getMonster().getName());
                // adding the damage of weapon to damage of gamaCharacter while hittin the monster
                int monsterNewHealth = this.getMonster().getHealth() - (this.getPlayer().getDamage() + this.getPlayer().getInventory().getWeapon().getDamage());
                if(monsterNewHealth < 0){
                
                    monsterNewHealth = 0;
                }
                this.getMonster().setHealth(monsterNewHealth);
            }
        }
        // showing the health of both sides after each hitting
        afterHit();
        return true;
    }
    
    // showing the health of both sides after each hitting
    public void afterHit() {
        System.out.println("/////////////////");
        System.out.println("Your Health: " + this.getPlayer().getHealth());
        System.out.println(this.getMonster().getName() + "'s Health: " + this.getMonster().getHealth());
        System.out.println("/////////////////");
    }

    // showing player info after fighting each monster
    public void playerInfo() {

        System.out.println("          Player Information         ");
        System.out.println("_____________________________________");
        System.out.println("Health: " + this.getPlayer().getHealth());
        System.out.println("Damage: " + this.getPlayer().getDamage());
        System.out.println("Weapon: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Weapon's Damage: "+ this.getPlayer().getInventory().getWeapon().getDamage());
        System.out.println("Armor: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Block: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Money: " + this.getPlayer().getMoney());

    }

    //showing the monster info after each fight
    public void monsterInfo(int i) {

        System.out.println("         " + i + "." + this.getMonster().getName() + " Information" + "      ");
        System.out.println("_____________________________________");
        System.out.println("Health: " + this.getMonster().getHealth());
        System.out.println("Damage: " + this.getMonster().getDamage());
        System.out.println("Prize: " + this.getMonster().getPrize());
    }

    
    //getter and setter methos
    public int maxMonsterRandom(int maxMonster) {

        return random.nextInt(maxMonster) + 1;
    }

    public int getMaxMonster() {
        return maxMonster;
    }

    public void setMaxMonster(int maxMonster) {
        this.maxMonster = maxMonster;
    }

    public Monster getMonster() {
        return monster;
    }

    public void setMonster(Monster monster) {
        this.monster = monster;
    }
}
