
package GameCharacter;
public abstract class GameChar {
    
    private int id;
    private int damage;
    private int health;
    private int money;
    private String name;
     
    
    public GameChar(int newDamage, int newHealth, int newMoney, String newName, int newId){
    
        this.damage = newDamage;
        this.health = newHealth;
        this.money = newMoney;
        this.name = newName;
        this.id = newId;
    }
    
    //getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
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
    
}
