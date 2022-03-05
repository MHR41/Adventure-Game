
package Monster;

public abstract class Monster {
    
    private int id;
    private String name;
    private int damage;
    private int health;
    //every moster type has it's own prize
    private String prize;
    //originalHealth won't change because the monster's health will be filled to it's original value when needed
    private int originalHealth;
   
    
    public Monster(int newId, String newName, int newDamage, int newHealth , String newPrize){
    
        this.id = newId;
        this.name = newName;
        this.damage = newDamage;
        this.prize = newPrize;
        this.health = newHealth;
        this.originalHealth = newHealth;
    }
    
    //getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getHealth() {
        // checking if the player's health is less than zero or not 
        // if it is, then it should be equal to zero 
        if(this.health <0){
            
            this.health =0;
        }
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public int getOriginalHealth() {
        return originalHealth;
    }
    
    public String getPrize() {
        return prize;
    }

    public void setPrize(String prize) {
        this.prize = prize;
    }
    
}
